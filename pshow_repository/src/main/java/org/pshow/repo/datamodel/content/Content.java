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
package org.pshow.repo.datamodel.content;

import java.util.Date;

/**
 * @author wangl
 * 
 */
public class Content {

    private long   id;
    private String uuid;
    private long   typeId;
    private String creator;
    private Date   created;
    private String modifier;
    private Date   modified;
    private long   versions;
    private long   parentId;
    private long   workspaceId;
    private long   aclId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public long getVersions() {
        return versions;
    }

    public void setVersions(long versions) {
        this.versions = versions;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    
    public long getWorkspaceId() {
        return workspaceId;
    }

    
    public void setWorkspaceId(long workspaceId) {
        this.workspaceId = workspaceId;
    }

    
    public long getAclId() {
        return aclId;
    }

    
    public void setAclId(long aclId) {
        this.aclId = aclId;
    }

}
