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

import java.util.UUID;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.repo.datamodel.content.ContentData;


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
        return getSqlSession().selectOne("org.pshow.repo.datamodel.content.ContentData.getUuidById", id);
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.dao.ContentDao#insertContent(org.pshow.repo.datamodel.content.ContentData)
     */
    @Override
    public void insertContent(ContentData cdata) {
        cdata.setUuid(UUID.randomUUID().toString());
        getSqlSession().insert("org.pshow.repo.datamodel.content.ContentData.insertContent",cdata);
    }

    @Override
    public void updateContent(ContentData cdata) {
        getSqlSession().update("org.pshow.repo.datamodel.content.ContentData.updateContent", cdata);
    }

}
