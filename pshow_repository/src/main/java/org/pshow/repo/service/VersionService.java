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

import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.namespace.NamespaceService;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.datamodel.version.Version;
import org.pshow.repo.datamodel.version.VersionHistory;

/**
 * @author roy
 * 
 */
public interface VersionService {

    public static final QName VERSIONABLE_Q_NAME = QName.createQName(
                                                         NamespaceService.SYSTEM_NAMESAPCE_URI,
                                                         "versionable");

    boolean isAVersion(ContentRef contentRef);

    boolean isVersioned(ContentRef contentRef);

    void checkOut(ContentRef contentRef);
    
    boolean isCheckOut(ContentRef contentRef);
    
    boolean cancelCheckOut(ContentRef contentRef);
    
    Version checkIn(ContentRef contentRef);

    VersionHistory getVersionHistory(ContentRef contentRef);

    Version getCurrentVersion(ContentRef contentRef);

    void revert(ContentRef contentRef, Version version);

    void deleteVersionHistory(ContentRef contentRef);

    void deleteVersion(ContentRef contentRef, Version version);

}
