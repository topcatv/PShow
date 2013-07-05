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
import java.util.UUID;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.repo.dao.model.QNameModel;
import org.pshow.repo.datamodel.content.Content;


/**
 * @author roy
 *
 */
public class ContentDaoImpl extends SqlSessionDaoSupport implements ContentDao {

    /* (non-Javadoc)
     * @see org.pshow.repo.dao.ContentDao#getUuidById(long)
     */
    @Override
    public String getUuidById(long id) {
        return getSqlSession().selectOne("org.pshow.repo.datamodel.content.Content.getUuidById", id);
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.dao.ContentDao#insertContent(org.pshow.repo.datamodel.content.Content)
     */
    @Override
    public void insertContent(Content cdata) {
        cdata.setUuid(UUID.randomUUID().toString());
        getSqlSession().insert("org.pshow.repo.datamodel.content.Content.insertContent",cdata);
    }

    @Override
    public void updateContent(Content cdata) {
        getSqlSession().update("org.pshow.repo.datamodel.content.Content.updateContent", cdata);
    }

    @Override
    public Content getContentByUUID(String uuid) {
        return getSqlSession().selectOne("org.pshow.repo.datamodel.content.Content.getContentByUUID", uuid);
    }

    @Override
    public List<Content> getContentByParentUUID(String uuid) {
        return getSqlSession().selectList("org.pshow.repo.datamodel.content.Content.getContentByParentUUID", uuid);
    }

    @Override
    public List<QNameModel> getFacetsByContent(String uuid) {
        return getSqlSession().selectList("org.pshow.repo.datamodel.content.Content.getFacetsByContent", uuid);
    }

    @Override
    public void removeFacetByContent(String uuid, long qname_id) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("contentId", uuid);
        parameter.put("qnameId", qname_id);
        getSqlSession().delete("org.pshow.repo.datamodel.content.Content.removeFacetByContent", parameter);
    }

    @Override
    public void insertContentFacet(long contentId, long qnameId) {
        Map<String, Long> parameter = new HashMap<String, Long>();
        parameter.put("contentId", contentId);
        parameter.put("qnameId", qnameId);
        getSqlSession().insert("org.pshow.repo.datamodel.content.Content.insertContentFacet", parameter); 
    }

}
