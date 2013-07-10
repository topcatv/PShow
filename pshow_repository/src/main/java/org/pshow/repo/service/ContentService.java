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

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.pshow.repo.audit.Auditable;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.content.WorkspaceRef;
import org.pshow.repo.datamodel.content.definition.DataTypeUnSupportExeception;
import org.pshow.repo.datamodel.namespace.NamespaceService;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.datamodel.namespace.QNamePattern;
import org.pshow.repo.service.data.DataStoreException;
import org.springframework.validation.annotation.Validated;

/**
 * @author roy
 * 
 */
@Auditable
@Validated
public interface ContentService {

    public static final QName DELETE_Q_NAME       = QName.createQName(
                                                          NamespaceService.SYSTEM_NAMESAPCE_URI,
                                                          "deleted");
    public static final QName CONTENT_NAME_Q_NAME = QName.createQName(
                                                          NamespaceService.SYSTEM_NAMESAPCE_URI,
                                                          "name");

    WorkspaceRef createWorkspace(String name)
            throws DuplicateWorkspaceException;

    WorkspaceRef findWorkspace(String name);

    ContentRef getRoot(WorkspaceRef workspace);

    @Auditable(parameters = { "id" }, recordable = { true })
    ContentRef getContent(@NotNull Long id);

    ContentRef createContent(ContentRef parentRef, QName typeQName)
            throws TypeException;

    ContentRef createContent(ContentRef parentRef, QName typeQName,
            Map<QName, Serializable> properties) throws TypeException, DataStoreException;

    void moveContent(ContentRef moveToContentRef, ContentRef newParentRef);

    QName getType(ContentRef contentRef);

    void addFacet(ContentRef contentRef, QName facetQName,
            Map<QName, Serializable> properties) throws TypeException, DataStoreException;

    Set<QName> getFacets(ContentRef contentRef);

    boolean hasFacet(ContentRef contentRef, QName facetQName);

    void deleteContent(ContentRef contentRef) throws DataTypeUnSupportExeception;

    Map<QName, Serializable> getProperties(ContentRef contentRef) throws DataStoreException;

    Serializable getProperty(ContentRef contentRef, QName qname) throws DataStoreException;

    void setProperties(ContentRef contentRef,
            Map<QName, Serializable> properties) throws TypeException, DataStoreException;

    void setProperty(ContentRef contentRef, QName qname, Serializable value)
            throws DataTypeUnSupportExeception, DataStoreException;

    void removeProperty(ContentRef contentRef, QName qname);

    ContentRef getParent(ContentRef contentRef);

    List<ContentRef> getChild(ContentRef contentRef);

    List<ContentRef> getChild(ContentRef contentRef,
            QNamePattern typeQNamePattern);

    List<ContentRef> getChild(ContentRef contentRef, Set<QName> typeQNames);

    void removeFacet(ContentRef contentRef, QName facetTypeQName);

}
