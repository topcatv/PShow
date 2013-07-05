/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pshow.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.pshow.repo.dao.NamespaceDao;
import org.pshow.repo.datamodel.content.ContentData;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.content.WorkspaceRef;
import org.pshow.repo.datamodel.content.definition.ContentFacet;
import org.pshow.repo.datamodel.content.definition.ContentType;
import org.pshow.repo.datamodel.content.definition.DataTypeUnSupportExeception;
import org.pshow.repo.datamodel.content.definition.Property;
import org.pshow.repo.datamodel.namespace.InvalidQNameException;
import org.pshow.repo.datamodel.namespace.NamespaceException;
import org.pshow.repo.datamodel.namespace.NamespaceService;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.schema.ContentSchemaHolder;
import org.pshow.repo.service.ContentService;
import org.pshow.repo.service.DuplicateWorkspaceException;
import org.pshow.repo.service.TypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author roy
 * 
 */
@Controller
public class ContentController {

    private static final QName  DELETE_Q_NAME       = QName.createQName(
                                                            NamespaceService.SYSTEM_NAMESAPCE_URI,
                                                            "deleted");
    private static final QName  CONTENT_NAME_Q_NAME = QName.createQName(
                                                            NamespaceService.SYSTEM_NAMESAPCE_URI,
                                                            "name");
    @Autowired
    private ContentService      contentService;
    @Autowired
    private ContentSchemaHolder csh;
    @Autowired
    private NamespaceDao        namespaceDao;

    private static Logger       LOGGER              = Logger.getLogger(ContentController.class);

    @RequestMapping(
        value = "/content/child/{parentId}",
        method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Serializable>> findChild(
            @PathVariable("parentId") String parentId)
            throws DuplicateWorkspaceException {
        List<Map<String, Serializable>> result = new ArrayList<Map<String, Serializable>>();
        if (StringUtils.equalsIgnoreCase("root", parentId)) {
            // TODO 也许可以将所有的workspace的root都缓存起来，不必每次都查询数据库
            ContentRef root = contentService.getRoot(getOrCreateWorkSpace());
            parentId = root.getId();
        }
        List<ContentRef> child = contentService.getChild(new ContentRef(
                parentId));
        for (ContentRef contentRef : child) {
            Serializable name = contentService.getProperty(contentRef,
                    CONTENT_NAME_Q_NAME);
            Serializable deleted = contentService.getProperty(contentRef,
                    DELETE_Q_NAME);
            if(deleted != null && ((Boolean) deleted)){
                continue;
            }
            QName type = contentService.getType(contentRef);
            name = (name == null ? "" : name);
            Map<String, Serializable> e = new HashMap<String, Serializable>(1);
            e.put("id", contentRef.getId());
            e.put("text", name);
            e.put("pid", parentId);
            e.put("type", type.getNamespaceURI() + ":" + type.getLocalName());
            result.add(e);
        }
        return result;
    }

    private WorkspaceRef getOrCreateWorkSpace()
            throws DuplicateWorkspaceException {

        String name = "default";
        WorkspaceRef defaultWS = contentService.findWorkspace(name);
        if (defaultWS == null) {
            defaultWS = contentService.createWorkspace(name);
        }

        return defaultWS;
    }

    @RequestMapping(value = "/content", method = RequestMethod.POST)
    @ResponseBody
    public ContentRef createContent(@RequestParam(
        value = "name",
        required = false) String contentName, @RequestParam(
        value = "workspace",
        required = false) String workspace,
            @RequestParam("parentId") String parentId,
            @RequestParam("type") String type, HttpServletRequest request)
            throws InvalidQNameException, TypeException, NamespaceException,
            IOException {
        LOGGER.debug(contentName);
        String name = (StringUtils.isBlank(contentName) ? request
                .getParameter("sys:name") : contentName);

        ContentRef parentContent = new ContentRef(parentId);
        if ("root".equalsIgnoreCase(parentId)) {
            String ws = StringUtils.isBlank(workspace) ? "default" : workspace;
            WorkspaceRef workspaceRef = contentService.findWorkspace(ws);
            parentContent = contentService.getRoot(workspaceRef);
        }
        ContentRef content = contentService.createContent(parentContent,
                parseQName(type));
        contentService.setProperty(content, CONTENT_NAME_Q_NAME, name);
        Map<QName, Serializable> properties = getProperties(request);
        properties.remove("sys:name");
        contentService.setProperties(content, properties);
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();
            Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
            for (Entry<String, MultipartFile> entry : entrySet) {
                contentService.setProperty(content, parseQName(entry.getKey()),
                        convertContentData(entry.getValue()));
            }
        }
        return content;
    }

    private Serializable convertContentData(MultipartFile value) throws IOException {
        return new ContentData(null, value.getContentType(), value.getSize(), value.getOriginalFilename(), value.getInputStream());
    }

    private Map<QName, Serializable> getProperties(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<QName, Serializable> map = new HashMap<QName, Serializable>();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            if (StringUtils.contains(name, ":")) {
                map.put(parseQName(name), request.getParameter(name));
            }
        }
        return map;
    }

    @RequestMapping(value = "/content/type", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> findAllSchema() {
        List<ContentType> cts = csh.getAllContentType();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>(
                cts.size());
        for (ContentType ct : cts) {
            HashMap<String, String> map = new HashMap<String, String>(
                    cts.size());
            map.put("name", ct.getName());
            map.put("title", ct.getTitle());
            list.add(map);
        }
        return list;
    }

    @RequestMapping(
        value = "/content/type/properties/{schemaName}",
        method = RequestMethod.GET)
    @ResponseBody
    public List<Property> findPropertiesBySchema(
            @PathVariable("schemaName") String schemaName) {
        ContentType contentType = csh.getContentType(parseQName(schemaName));
        return contentType.getProperties();
    }

    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Serializable> getContent(
            @PathVariable("contentId") String contentId) {
        ContentRef contentRef = new ContentRef(contentId);
        Map<QName, Serializable> properties = contentService
                .getProperties(contentRef);
        HashMap<String, Serializable> reMap = new HashMap<String, Serializable>(
                properties.size());

        QName type = contentService.getType(contentRef);
        ContentType contentType = csh.getContentType(type);
        convertProperties(properties, contentType.getProperties(), reMap);

        Set<QName> facets = contentService.getFacets(contentRef);
        for (QName qName : facets) {
            ContentFacet facet = csh.getFacet(qName);
            convertProperties(properties, facet.getProperties(), reMap);
        }

        return reMap;
    }

    @RequestMapping(
        value = "/content/{contentId}",
        method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delContent(@PathVariable("contentId") String contentId)
            throws DataTypeUnSupportExeception {
        try {
            contentService.setProperty(new ContentRef(contentId),
                    DELETE_Q_NAME, true);
            return true;
        } catch (DataTypeUnSupportExeception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/content", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateContent(String id, String name, @RequestParam(
        required = false,
        defaultValue = "true") boolean isFolder, HttpServletRequest request)
            throws TypeException {
        if (isFolder) {
            return rename(id, name);
        } else {
            return update(id, request);
        }
    }

    private boolean update(String id, HttpServletRequest request)
            throws TypeException {
        Map<QName, Serializable> properties = getProperties(request);
        contentService.setProperties(new ContentRef(id), properties);
        return true;
    }

    private boolean rename(String contentId, String name)
            throws DataTypeUnSupportExeception {
        contentService.setProperty(new ContentRef(contentId),
                CONTENT_NAME_Q_NAME, name);
        return true;
    }

    private void convertProperties(Map<QName, Serializable> properties,
            List<Property> filter_properties,
            HashMap<String, Serializable> reMap) {

        Iterator<Entry<QName, Serializable>> it_property = properties
                .entrySet().iterator();

        for (Property filter_property : filter_properties) {
            HashMap<String, Serializable> hashMap = new HashMap<String, Serializable>(
                    3);
            hashMap.put("title", filter_property.getTitle());
            hashMap.put("value", null);
            hashMap.put("definition", filter_property);
            reMap.put(filter_property.getName(), hashMap);
        }

        while (it_property.hasNext()) {
            Map.Entry<QName, Serializable> property = it_property.next();
            QName propertyName = property.getKey();
            for (Property filter_property : filter_properties) {
                if (parseQName(filter_property.getName()).equals(propertyName)) {
                    HashMap<String, Serializable> hashMap = new HashMap<String, Serializable>(
                            3);
                    hashMap.put("title", filter_property.getTitle());
                    hashMap.put("value", properties.get(propertyName));
                    hashMap.put("definition", filter_property);
                    reMap.put(filter_property.getName(), hashMap);
                    it_property.remove();
                }
            }
        }
    }

    private QName parseQName(String type) {
        String[] split = StringUtils.split(type, ":");
        String prefix = split[0];
        String localName = split[1];
        return QName.createQName(prefix, localName, namespaceDao);
    }
}
