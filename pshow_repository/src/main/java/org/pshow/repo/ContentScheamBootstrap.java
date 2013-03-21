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
package org.pshow.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.pshow.repo.datamodel.content.definition.PSModel;
import org.pshow.repo.schema.ContentSchemaHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author roy
 * 
 */
public class ContentScheamBootstrap {

    private Resource[]   definitions;
    private Logger       logger = LoggerFactory.getLogger(ContentScheamBootstrap.class);
    private ContentSchemaHolder csh;

    @Transactional(propagation = Propagation.REQUIRED)
    public void onBootstrap() throws IOException, JiBXException {
        // 锁定服务器在内容类型未加载完成前不提交服务
        lockServer();
        // 加载内容定义文件
        List<PSModel> schemas = loadContentSchemas();
        // 注册内容定义
        csh.registContentSchemas(schemas);
        // 解锁服务
        unlockServer();
    }

    private void unlockServer() {
        ServerStauts.unlockServer();
    }

    private List<PSModel> loadContentSchemas() throws IOException, JiBXException {
        ArrayList<PSModel> schames = new ArrayList<PSModel>();
        for (Resource def : definitions) {
            logger.info(def.getURI().toString());
            IBindingFactory bfact = BindingDirectory.getFactory(PSModel.class);
            // unmarshal customer information from file
            IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
            PSModel model = (PSModel) uctx.unmarshalDocument(def.getInputStream(), null);
            logger.info(model.getPublished().toLocaleString());
            schames.add(model);
        }
        return schames;

    }

    private void lockServer() {
        ServerStauts.lockServer();
    }

    public void setDefinitions(Resource[] definitions) {
        this.definitions = definitions;
    }

    
    public void setCsh(ContentSchemaHolder csh) {
        this.csh = csh;
    }
}
