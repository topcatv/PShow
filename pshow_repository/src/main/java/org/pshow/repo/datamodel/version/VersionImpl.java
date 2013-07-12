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
package org.pshow.repo.datamodel.version;

import java.util.Date;

import org.pshow.repo.dao.ContentDao;
import org.pshow.repo.datamodel.content.Content;
import org.pshow.repo.datamodel.content.ContentRef;

/**
 * @author roy
 * 
 */
public class VersionImpl implements Version {

    private long              id;
    private long              contentId;
    private long              versionContentId;
    private String            versionLabel;
    private long              preVersion;
    private String            versionDesc;
    private boolean           current;
    private int               versionType;
    private Content           version_content;

    private static final long serialVersionUID = -2981615069624942182L;

    private ContentDao        contentDao;

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.Version#getContentRef()
     */
    @Override
    public ContentRef getContentRef() {
        initVersionContent();
        
        if (version_content == null) {
            return null;
        }
        return new ContentRef(version_content.getUuid());
    }

    private void initVersionContent() {
        if(version_content == null){
            version_content = contentDao.getContentByID(this.versionContentId);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.Version#getVersionLabel()
     */
    @Override
    public String getVersionLabel() {
        return this.versionLabel;
    }

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.Version#getCreator()
     */
    @Override
    public String getCreator() {
        initVersionContent();
        return version_content.getCreator();
    }

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.Version#getCreated()
     */
    @Override
    public Date getCreated() {
        initVersionContent();
        return version_content.getCreated();
    }

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.Version#getVersionType()
     */
    @Override
    public VersionType getVersionType() {
        return VersionType.valueOf(this.versionType);
    }

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.Version#getDescription()
     */
    @Override
    public String getDescription() {
        return this.versionDesc;
    }

    public long getId() {
        return id;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getVersionContentId() {
        return versionContentId;
    }

    public void setVersionContentId(long versionContentId) {
        this.versionContentId = versionContentId;
    }

    public long getPreVersion() {
        return preVersion;
    }

    public void setPreVersion(long preVersion) {
        this.preVersion = preVersion;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public void setVersionLabel(String versionLabel) {
        this.versionLabel = versionLabel;
    }

    public void setVersionType(int versionType) {
        this.versionType = versionType;
    }

    
    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    
    public void setId(long id) {
        this.id = id;
    }

}
