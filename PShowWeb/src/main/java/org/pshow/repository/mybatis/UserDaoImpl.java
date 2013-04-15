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
package org.pshow.repository.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.entity.User;


/**
 * @author roy
 *
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    /* (non-Javadoc)
     * @see org.pshow.repository.mybatis.UserDao#findByName(java.lang.String)
     */
    @Override
    public User findByName(String name) {
        return getSqlSession().selectOne("org.pshow.entity.Security.user_findByName", name);
    }

    /* (non-Javadoc)
     * @see org.pshow.repository.mybatis.UserDao#findByLoginName(java.lang.String)
     */
    @Override
    public User findByLoginName(String loginName) {
        return getSqlSession().selectOne("org.pshow.entity.Security.user_findByLoginName", loginName);
    }

    /* (non-Javadoc)
     * @see org.pshow.repository.mybatis.UserDao#save(org.pshow.entity.User)
     */
    @Override
    public void save(User user) {
        getSqlSession().insert("org.pshow.entity.Security.user_save", user);
    }

    /* (non-Javadoc)
     * @see org.pshow.repository.mybatis.UserDao#findAll()
     */
    @Override
    public List<User> findAll() {
        return getSqlSession().selectList("org.pshow.entity.Security.user_findAll");
    }

    /* (non-Javadoc)
     * @see org.pshow.repository.mybatis.UserDao#findOne(java.lang.Long)
     */
    @Override
    public User findOne(Long id) {
        return getSqlSession().selectOne("org.pshow.entity.Security.user_findOne", id);
    }

    /* (non-Javadoc)
     * @see org.pshow.repository.mybatis.UserDao#count()
     */
    @Override
    public Long count() {
        return getSqlSession().selectOne("org.pshow.entity.Security.user_count");
    }

}
