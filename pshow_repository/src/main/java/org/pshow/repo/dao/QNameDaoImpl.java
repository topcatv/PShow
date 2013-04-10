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

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.repo.dao.model.QNameModel;
import org.pshow.repo.datamodel.namespace.QName;


/**
 * @author roy
 *
 */
public class QNameDaoImpl extends SqlSessionDaoSupport implements QNameDao {

    /* (non-Javadoc)
     * @see org.pshow.repo.dao.QNameDao#insertQName(org.pshow.repo.dao.model.QNameModel)
     */
    @Override
    public void insertQName(QNameModel qNameModel) {
        getSqlSession().insert("org.pshow.repo.dao.model.QNameModel.insertQName", qNameModel);
    }

    @Override
    public int count(QNameModel qName) {
        return getSqlSession().selectOne("org.pshow.repo.dao.model.QNameModel.count", qName);
    }

    @Override
    public QNameModel findQName(QName qName) {
        return getSqlSession().selectOne("org.pshow.repo.dao.model.QNameModel.findQName", qName);
    }

    @Override
    public QNameModel findQNameById(long id) {
        return getSqlSession().selectOne("org.pshow.repo.dao.model.QNameModel.findQNameById", id);
    }

}
