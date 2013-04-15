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

import org.pshow.repo.datamodel.content.ContentData;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author roy
 *
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    
    @RequestMapping(value="/content/{parentId}/child",method=RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Serializable>> findChild(@PathVariable("parentId") String parentId) {
        List<ContentRef> child = contentService.getChild(new ContentRef(parentId));
        List<Map<String, Serializable>> result = new ArrayList<Map<String, Serializable>>(child.size());
        for (ContentRef contentRef : child) {
            Serializable property = contentService.getProperty(contentRef, QName.createQName("http://www.pshow.org/model/system/0.1", "name"));
            Map<String, Serializable> e = new HashMap<String, Serializable>(1);
            e.put(contentRef.getId(), property);
            result.add(e);
        }
        return result;
    }
    
    @RequestMapping(value="/content/root/child",method=RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Serializable>> findRootChild() {
        ContentRef root = contentService.getRoot(contentService.findWorkspace("default"));
        List<ContentRef> child = contentService.getChild(root );
        List<Map<String, Serializable>> result = new ArrayList<Map<String, Serializable>>(child.size());
        for (ContentRef contentRef : child) {
            Serializable property = contentService.getProperty(contentRef, QName.createQName("http://www.pshow.org/model/system/0.1", "name"));
            Map<String, Serializable> e = new HashMap<String, Serializable>(1);
            e.put(contentRef.getId(), property);
            result.add(e);
        }
        return result;
    }
}
