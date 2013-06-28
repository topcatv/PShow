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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.repo.dao.model.PropertyModel;
import org.pshow.repo.datamodel.namespace.QName;

/**
 * @author roy
 * 
 */
public class PropertyDaoImpl extends SqlSessionDaoSupport implements PropertyDao {

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.dao.PropertyDao#insertProperty(org.pshow.repo.dao.model
     * .PropertyModel)
     */
    @Override
    public void insertProperty(PropertyModel propertyModel) {
        getSqlSession().insert("org.pshow.repo.dao.model.PropertyModel.insertProperty", propertyModel);
    }

    @Override
    public PropertyModel findProperty(String contentId, QName qname) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("contentId", contentId);
        params.put("qname", qname);
        return getSqlSession().selectOne("org.pshow.repo.dao.model.PropertyModel.findProperty", params);
    }

    @Override
    public List<PropertyModel> findProperties(String contentId) {
        return getSqlSession().selectList("org.pshow.repo.dao.model.PropertyModel.findProperties", contentId);
    }

    @Override
    public void removeProperty(String contentId, QName qname) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("contentId", contentId);
        params.put("qname", qname);
        getSqlSession().delete("org.pshow.repo.dao.model.PropertyModel.removeProperty", params);
        
    }

    @Override
    public void updataProperty(PropertyModel propertyModel) {
        getSqlSession().update("org.pshow.repo.dao.model.PropertyModel.updateProperty", propertyModel);
    }

    @Override
    public int countProperty(long contentId, long propertyQnameId) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("contentId", contentId);
        params.put("pqnameId", propertyQnameId);
        return getSqlSession().selectOne("org.pshow.repo.dao.model.PropertyModel.count", params);
    }

}
