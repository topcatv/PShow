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
package org.pshow.repo.dao.model;

import java.util.Collection;

import org.pshow.repo.datamodel.version.Version;
import org.pshow.repo.datamodel.version.VersionHistory;


/**
 * @author roy
 *
 */
public class DefaultVersionHistory implements VersionHistory {

    private static final long serialVersionUID = 7415322440863120203L;

    /* (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.VersionHistory#getRootVersion()
     */
    @Override
    public Version getRootVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.VersionHistory#getHeadVersion()
     */
    @Override
    public Version getHeadVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.VersionHistory#getAllVersions()
     */
    @Override
    public Collection<Version> getAllVersions() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.VersionHistory#getPredecessor(org.pshow.repo.datamodel.version.Version)
     */
    @Override
    public Version getPredecessor(Version version) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.datamodel.version.VersionHistory#getVersion(java.lang.String)
     */
    @Override
    public Version getVersion(String versionLabel) {
        // TODO Auto-generated method stub
        return null;
    }

}
