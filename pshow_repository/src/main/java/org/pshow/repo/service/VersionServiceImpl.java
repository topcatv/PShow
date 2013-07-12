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
package org.pshow.repo.service;

import org.pshow.repo.dao.VersionDao;
import org.pshow.repo.dao.model.DefaultVersionHistory;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.version.Version;
import org.pshow.repo.datamodel.version.VersionHistory;


/**
 * @author roy
 *
 */
public class VersionServiceImpl implements VersionService {
    private ContentService cs;
    private VersionDao versionDao;

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#isAVersion(org.pshow.repo.datamodel.content.ContentRef)
     */
    @Override
    public boolean isAVersion(ContentRef contentRef) {
        return cs.hasFacet(contentRef, VERSIONABLE_Q_NAME);
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#isVersioned(org.pshow.repo.datamodel.content.ContentRef)
     */
    @Override
    public boolean isVersioned(ContentRef contentRef) {
        int count = versionDao.count(contentRef.getId());
        return count > 0;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#getVersionHistory(org.pshow.repo.datamodel.content.ContentRef)
     */
    @Override
    public VersionHistory getVersionHistory(ContentRef contentRef) {
        DefaultVersionHistory vh = versionDao.getHistory(contentRef.getId());
        return vh;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#getCurrentVersion(org.pshow.repo.datamodel.content.ContentRef)
     */
    @Override
    public Version getCurrentVersion(ContentRef contentRef) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#revert(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.version.Version)
     */
    @Override
    public void revert(ContentRef contentRef, Version version) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#deleteVersionHistory(org.pshow.repo.datamodel.content.ContentRef)
     */
    @Override
    public void deleteVersionHistory(ContentRef contentRef) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.pshow.repo.service.VersionService#deleteVersion(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.version.Version)
     */
    @Override
    public void deleteVersion(ContentRef contentRef, Version version) {
        // TODO Auto-generated method stub

    }

    
    public void setCs(ContentService cs) {
        this.cs = cs;
    }

    @Override
    public void checkOut(ContentRef contentRef) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isCheckOut(ContentRef contentRef) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean cancelCheckOut(ContentRef contentRef) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Version checkIn(ContentRef contentRef) {
        // TODO Auto-generated method stub
        return null;
    }

}
