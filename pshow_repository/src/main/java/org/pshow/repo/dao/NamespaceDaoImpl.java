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
package org.pshow.repo.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.repo.dao.model.NamespaceModel;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

/**
 * @author roy
 * 
 */
public class NamespaceDaoImpl extends SqlSessionDaoSupport implements NamespaceDao {

    private static BiMap<String, String> namespaces = Maps.synchronizedBiMap(HashBiMap.<String, String> create());

    @Override
    public void insertNamespace(NamespaceModel namespace) {
        getSqlSession().insert("org.pshow.repo.datamodel.namespace.PSNamespace.insert", namespace);
    }

    @Override
    public List<NamespaceModel> findAllNamespaces() {
        return getSqlSession().selectList("org.pshow.repo.datamodel.namespace.PSNamespace.getAll");
    }

    @Override
    public String getNamespaceURI(String prefix) {
        String uri = namespaces.inverse().get(prefix);
        if (uri == null) {
            uri = getSqlSession().selectOne("org.pshow.repo.datamodel.namespace.PSNamespace.findNamespaceByPrefix", prefix);
            namespaces.put(uri, prefix);
        }
        return uri;
    }

    @Override
    public String getPrefix(String namespaceURI) {
        String prefix = namespaces.get(namespaceURI);
        if (prefix == null) {
            NamespaceModel model =  getSqlSession().selectOne("org.pshow.repo.datamodel.namespace.PSNamespace.findNamespaceByUri", namespaceURI);
            prefix = model.getPrefix();
            namespaces.put(namespaceURI, prefix);
        }
        return prefix;
    }

    @Override
    public NamespaceModel findNamespaceByUri(String uri) {
        return getSqlSession().selectOne("org.pshow.repo.datamodel.namespace.PSNamespace.findNamespaceByUri", uri);
    }

}
