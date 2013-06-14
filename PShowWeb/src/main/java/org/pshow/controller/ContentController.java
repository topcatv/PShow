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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.pshow.repo.dao.NamespaceDao;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.content.WorkspaceRef;
import org.pshow.repo.datamodel.namespace.InvalidQNameException;
import org.pshow.repo.datamodel.namespace.NamespaceException;
import org.pshow.repo.datamodel.namespace.NamespaceService;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.service.ContentService;
import org.pshow.repo.service.TypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author roy
 * 
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private NamespaceDao   namespaceDao;

    private static Logger  LOGGER = Logger.getLogger(ContentController.class);

    @RequestMapping(
        value = "/content/child/{parentId}",
        method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Serializable>> findChild(
            @PathVariable("parentId") String parentId) {
        List<Map<String, Serializable>> result = new ArrayList<Map<String, Serializable>>();
        if (StringUtils.equalsIgnoreCase("root", parentId)) {
            ContentRef root = contentService.getRoot(contentService
                    .findWorkspace("default"));
            parentId = root.getId();
        }
        List<ContentRef> child = contentService.getChild(new ContentRef(
                parentId));
        for (ContentRef contentRef : child) {
            Serializable property = contentService.getProperty(contentRef,
                    QName.createQName("http://www.pshow.org/model/system/0.1",
                            "name"));
            property = property == null ? "ad" : property;
            Map<String, Serializable> e = new HashMap<String, Serializable>(1);
            e.put("id", contentRef.getId());
            e.put("text", property);
            e.put("pid", parentId);
            result.add(e);
        }
        return result;
    }

    @RequestMapping(value = "/content", method = RequestMethod.POST)
    @ResponseBody
    public ContentRef createContent(
            @RequestParam("name") String contentName,
            @RequestParam(value = "workspace", required = false) String workspace,
            @RequestParam("parentId") String parentId,
            @RequestParam("type") String type) throws InvalidQNameException,
            TypeException, NamespaceException {
        LOGGER.debug(contentName);
        String[] split = StringUtils.split(type, ":");
        String prefix = split[0];
        String localName = split[1];
        ContentRef parentContent = new ContentRef(parentId);
        if ("root".equalsIgnoreCase(parentId)) {
            String ws = StringUtils.isBlank(workspace) ? "default" : workspace;
            WorkspaceRef workspaceRef = contentService.findWorkspace(ws);
            parentContent = contentService.getRoot(workspaceRef);
        }
        ContentRef content = contentService.createContent(parentContent,
                QName.createQName(prefix, localName, namespaceDao));
        contentService.setProperty(content, QName.createQName(
                NamespaceService.SYSTEM_NAMESAPCE_URI, "name"), contentName);
        return content;
    }
}
