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
import java.util.concurrent.ConcurrentMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.pshow.repo.datamodel.content.Workspace;

import com.google.common.collect.Maps;


/**
 * @author roy
 *
 */
public class WorkspaceDaoImpl extends SqlSessionDaoSupport implements WorkspaceDao {
    private static ConcurrentMap<String,Workspace> workspaceCache = Maps.<String,Workspace>newConcurrentMap();

    /* (non-Javadoc)
     * @see org.pshow.repo.dao.WorkspaceDao#findWorkspaceByUUID(java.lang.String)
     */
    @Override
    public Workspace findWorkspaceByUUID(String uuid) {
        return getSqlSession().selectOne("org.pshow.repo.datamodel.content.Workspace.findWorkspaceByUUID", uuid);
    }

    @Override
    public Workspace findWorkspaceByName(String name) {
        if(workspaceCache.containsKey(name)){
            return workspaceCache.get(name);
        }
        Workspace worksapce = getSqlSession().selectOne("org.pshow.repo.datamodel.content.Workspace.findWorkspaceByName", name);
        workspaceCache.putIfAbsent(name, worksapce);
        return worksapce;
    }

    @Override
    public void insertWorkspace(Workspace workspace) {
        workspace.setUuid(UUID.randomUUID().toString());
        getSqlSession().insert("org.pshow.repo.datamodel.content.Workspace.insertWorkspace", workspace);
        workspaceCache.putIfAbsent(workspace.getName(), workspace);
    }

}
