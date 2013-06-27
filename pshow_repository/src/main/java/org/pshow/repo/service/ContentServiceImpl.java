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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.pshow.repo.dao.ContentDao;
import org.pshow.repo.dao.NamespaceDao;
import org.pshow.repo.dao.PropertyDao;
import org.pshow.repo.dao.QNameDao;
import org.pshow.repo.dao.WorkspaceDao;
import org.pshow.repo.dao.model.PropertyModel;
import org.pshow.repo.dao.model.QNameModel;
import org.pshow.repo.datamodel.content.ContentData;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.content.PropertyValue;
import org.pshow.repo.datamodel.content.Workspace;
import org.pshow.repo.datamodel.content.WorkspaceRef;
import org.pshow.repo.datamodel.content.definition.ContentFacet;
import org.pshow.repo.datamodel.content.definition.ContentType;
import org.pshow.repo.datamodel.content.definition.DataType;
import org.pshow.repo.datamodel.content.definition.DataType.Type;
import org.pshow.repo.datamodel.content.definition.DataTypeUnSupportExeception;
import org.pshow.repo.datamodel.content.definition.Property;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.datamodel.namespace.QNamePattern;
import org.pshow.repo.schema.ContentSchemaHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author roy
 * 
 */
@Transactional
public class ContentServiceImpl implements ContentService {

    private NamespaceDao        namespaceDao;
    private ContentDao          contentDao;
    private WorkspaceDao        workspaceDao;
    private PropertyDao         propertyDao;
    private ContentSchemaHolder schemaHolder;
    private QNameDao            qnameDao;

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.service.ContentService#getRoot()
     */
    @Override
    @Transactional(readOnly = true)
    public ContentRef getRoot(WorkspaceRef workspace) {
        Workspace ws = workspaceDao.findWorkspaceByUUID(workspace.getId());
        String uuid = contentDao.getUuidById(ws.getRootId());
        return new ContentRef(uuid);
    }

    /*
     * (non-Javadoc)
     * @see org.pshow.repo.service.ContentService#getContent(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public ContentRef getContent(Long id) {
        String uuid = contentDao.getUuidById(id);
        return new ContentRef(uuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#createContent(org.pshow.repo.datamodel
     * .content.ContentRef, java.lang.String,
     * org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ContentRef createContent(ContentRef parentRef, QName typeQName) throws TypeException {
        ContentRef createContent = null;
        createContent = createContent(parentRef, typeQName, null);
        return createContent;
    }

    private long getDefaultVersion() {
        return 1;
    }

    private long getRootParentId() {
        return -1;
    }

    private String currentUser() {
        // TODO Auto-generated method stub
        return null;
    }

    private long getDefaultAcl() {
        return -1;
    }

    private long findTypeId(QName typeQName) {
        QNameModel findQName = qnameDao.findQName(typeQName);
        return findQName == null ? 0L : findQName.getId();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#createContent(org.pshow.repo.datamodel
     * .content.ContentRef, java.lang.String,
     * org.pshow.repo.datamodel.namespace.QName, java.util.Map)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ContentRef createContent(ContentRef parentRef, QName typeQName, Map<QName, Serializable> properties) throws TypeException {
        if (!schemaHolder.hasContentType(typeQName)) {
            throw new TypeNotExistException(String.format("Create content error: type[%s] not exist.", typeQName.toString()));
        }
        ContentData cdata = newContent(parentRef, typeQName);
        if (properties != null) {
            saveProperties(cdata.getId(), typeQName, properties);
        }
        return new ContentRef(cdata.getUuid());
    }

    private ContentData newContent(ContentRef parentRef, QName typeQName) {
        ContentData parentContent = contentDao.getContentByUUID(parentRef.getId());
        ContentData cdata = new ContentData();
        cdata.setTypeId(findTypeId(typeQName));
        cdata.setAclId(getDefaultAcl());
        cdata.setModifier(currentUser());
        cdata.setCreator(currentUser());
        cdata.setParentId(parentContent.getId());
        cdata.setVersions(getDefaultVersion());
        cdata.setWorkspaceId(parentContent.getWorkspaceId());
        contentDao.insertContent(cdata);
        return cdata;
    }

    private void saveProperties(long id, QName typeQName, Map<QName, Serializable> properties) throws TypeException {
        ContentType contentType = schemaHolder.getContentType(typeQName);
        saveProperties(properties, contentType.getProperties(), id);
        List<String> mandatoryFacets = contentType.getMandatoryFacets();
        for (String facetName : mandatoryFacets) {
            ContentFacet facet = schemaHolder.getFacet(createKey(facetName));
            saveProperties(properties, facet.getProperties(), id);
        }
    }

    private void saveProperties(Map<QName, Serializable> properties, List<Property> properties_definition, long contentId) throws TypeException {
        for (Property property : properties_definition) {
            Serializable value = properties.get(createKey(property.getName()));
            if (value == null) {
                continue;
            }
            DataType dataType = schemaHolder.getRegisteredObject(createKey(property.getPropertyType()), new DataType());
            checkValueType(dataType, value);
            PropertyValue propertyValue = new PropertyValue(value);
            QNameModel property_type_qname = qnameDao.findQName(createKey(property.getName()));
            long propertyQnameId = property_type_qname.getId();
            savePropertyValue(propertyValue, contentId, propertyQnameId);
        }
    }

    private void savePropertyValue(PropertyValue propertyValue, long contentId, long propertyQnameId) {
        PropertyModel propertyModel = new PropertyModel();
        Type type = propertyValue.getType();
        switch (type) {
            case ANY:
            case CONTENT:
            case DATE:
            case DATETIME:
            case BOOLEAN:
                propertyModel.setSerializableValue(propertyValue.getValue());
                break;
            case TEXT:
                propertyModel.setStringValue(propertyValue.getTextValue());
                break;
            case INT:
                propertyModel.setLongValue(Long.valueOf(propertyValue.getIntValue()));
                break;
            case LONG:
                propertyModel.setLongValue(propertyValue.getLongValue());
                break;
            case FLOAT:
                propertyModel.setFloatValue(propertyValue.getFloatValue());
                break;
            case DOUBLE:
                propertyModel.setDoubleValue(propertyValue.getDoubleValue());
                break;
        }
        propertyModel.setContentId(contentId);
        propertyModel.setPropertyQName(propertyQnameId);
        propertyModel.setActualType(propertyValue.getActualType());
        
        int count = propertyDao.countProperty(contentId, propertyQnameId);
        if (count == 0) {
            propertyDao.insertProperty(propertyModel);
        } else {
            propertyDao.updataProperty(propertyModel);
        }
    }

    private void checkValueType(DataType dataType, Serializable value) throws TypeException {
        Type valueType = Type.getObjectType(value);
        if (!valueType.equals(dataType.getType())) {
            throw new TypeException(String.format("value type [%s] not consist definition type [%s]", value.getClass().getName(), dataType.getJavaClassName()));
        }
    }

    private QName createKey(String property_name) {
        return QName.createQName(QName.resolvePrefix(property_name), QName.resolveLocalName(property_name), namespaceDao);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#moveContent(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.content.ContentRef)
     */
    @Override
    public void moveContent(ContentRef moveToContentRef, ContentRef newParentRef) {
        ContentData moveToContent = contentDao.getContentByUUID(moveToContentRef.getId());
        ContentData newParent = contentDao.getContentByUUID(newParentRef.getId());
        moveToContent.setParentId(newParent.getId());
        contentDao.updateContent(moveToContent);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getType(org.pshow.repo.datamodel
     * .content.ContentRef)
     */
    @Override
    public QName getType(ContentRef contentRef) {
        ContentData self = contentDao.getContentByUUID(contentRef.getId());
        long typeId = self.getTypeId();
        QNameModel qNameModel = qnameDao.findQNameById(typeId);
        return QName.createQName(qNameModel.getNamespaceURI(), qNameModel.getLocalName());
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getFacets(org.pshow.repo.datamodel
     * .content.ContentRef)
     */
    @Override
    public Set<QName> getFacets(ContentRef contentRef) {
        List<QNameModel> qnameModels = contentDao.getFacetsByContent(contentRef.getId());
        HashSet<QName> hashSet = new HashSet<QName>();
        if (qnameModels == null) {
            return hashSet;
        }
        for (QNameModel qNameModel : qnameModels) {
            hashSet.add(QName.createQName(qNameModel.getNamespaceURI(), qNameModel.getLocalName()));
        }
        return hashSet;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#hasFacet(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    public boolean hasFacet(ContentRef contentRef, QName facetQName) {
        if (facetQName == null) {
            return false;
        }
        List<QNameModel> qnameModels = contentDao.getFacetsByContent(contentRef.getId());
        if (qnameModels == null) {
            return false;
        }
        QNameModel facetQnameModel = new QNameModel(facetQName.getNamespaceURI(), facetQName.getLocalName());
        return qnameModels.contains(facetQnameModel);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#removeFacet(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    public void removeFacet(ContentRef contentRef, QName facetTypeQName) {
        QNameModel qNameModel = qnameDao.findQName(facetTypeQName);
        contentDao.removeFacetByContent(contentRef.getId(), qNameModel.getId());
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#addFacet(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QName,
     * java.util.Map)
     */
    @Override
    public void addFacet(ContentRef contentRef, QName facetQName, Map<QName, Serializable> properties) throws TypeException {
        QNameModel qNameModel = qnameDao.findQName(facetQName);
        ContentData contentData = contentDao.getContentByUUID(contentRef.getId());
        contentDao.insertContentFacet(contentData.getId(), qNameModel.getId());

        saveProperties(properties, schemaHolder.getFacet(facetQName).getProperties(), contentData.getId());
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#deleteContent(org.pshow.repo.datamodel
     * .content.ContentRef)
     */
    @Override
    public void deleteContent(ContentRef contentRef) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getProperties(org.pshow.repo.datamodel
     * .content.ContentRef)
     */
    @Override
    public Map<QName, Serializable> getProperties(ContentRef contentRef) {
        List<PropertyModel> propertyModels = propertyDao.findProperties(contentRef.getId());
        if (propertyModels == null) {
            return null;
        }
        HashMap<QName, Serializable> result = new HashMap<QName, Serializable>();
        for (PropertyModel propertyModel : propertyModels) {
            PropertyValue propertyValue = new PropertyValue(propertyModel);
            long qnameId = propertyModel.getPropertyQName();
            QNameModel qNameModel = qnameDao.findQNameById(qnameId);
            result.put(QName.createQName(qNameModel.getNamespaceURI(), qNameModel.getLocalName()), propertyValue.getValue());
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getProperty(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    public Serializable getProperty(ContentRef contentRef, QName qname) {
        PropertyModel propertyModel = propertyDao.findProperty(contentRef.getId(), qname);
        if (propertyModel == null) {
            return null;
        }
        PropertyValue propertyValue = new PropertyValue(propertyModel);
        return propertyValue.getValue();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#setProperties(org.pshow.repo.datamodel
     * .content.ContentRef, java.util.Map)
     */
    @Override
    public void setProperties(ContentRef contentRef, Map<QName, Serializable> properties) throws TypeException {
        ContentData self = contentDao.getContentByUUID(contentRef.getId());
        long typeId = self.getTypeId();
        QNameModel qNameModel = qnameDao.findQNameById(typeId);
        ContentType contentType = schemaHolder.getContentType(QName.createQName(qNameModel.getNamespaceURI(), qNameModel.getLocalName()));
        saveProperties(properties, contentType.getProperties(), self.getId());
        List<QNameModel> facetsByContent = contentDao.getFacetsByContent(contentRef.getId());
        if (CollectionUtils.isNotEmpty(facetsByContent)) {
            for (QNameModel facet_qNameModel : facetsByContent) {
                QName facetQName = QName.createQName(facet_qNameModel.getNamespaceURI(), facet_qNameModel.getLocalName());
                ContentFacet facet = schemaHolder.getFacet(facetQName);
                saveProperties(properties, facet.getProperties(), self.getId());
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#setProperty(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QName,
     * java.io.Serializable)
     */
    @Override
    public void setProperty(ContentRef contentRef, QName qname, Serializable value) throws DataTypeUnSupportExeception {
        PropertyValue propertyValue = new PropertyValue(value);
        long contentId = contentDao.getContentByUUID(contentRef.getId()).getId();
        long propertyQnameId = qnameDao.findQName(qname).getId();
        savePropertyValue(propertyValue, contentId, propertyQnameId);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#removeProperty(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    public void removeProperty(ContentRef contentRef, QName qname) {
        propertyDao.removeProperty(contentRef.getId(), qname);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getParent(org.pshow.repo.datamodel
     * .content.ContentRef)
     */
    @Override
    @Transactional(readOnly = true)
    public ContentRef getParent(ContentRef contentRef) {
        ContentData self = contentDao.getContentByUUID(contentRef.getId());
        String parentUuid = contentDao.getUuidById(self.getParentId());
        return new ContentRef(parentUuid);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getChild(org.pshow.repo.datamodel
     * .content.ContentRef)
     */
    @Override
    public List<ContentRef> getChild(ContentRef contentRef) {
        List<ContentData> result = contentDao.getContentByParentUUID(contentRef.getId());
        List<ContentRef> child = new ArrayList<ContentRef>();
        for (ContentData contentData : result) {
            child.add(new ContentRef(contentData.getUuid()));
        }
        return child;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getChild(org.pshow.repo.datamodel
     * .content.ContentRef, org.pshow.repo.datamodel.namespace.QNamePattern)
     */
    @Override
    public List<ContentRef> getChild(ContentRef contentRef, QNamePattern typeQNamePattern) {
        List<ContentData> result = contentDao.getContentByParentUUID(contentRef.getId());
        List<ContentRef> child = new ArrayList<ContentRef>();
        for (ContentData contentData : result) {
            QNameModel typeQNameModel = qnameDao.findQNameById(contentData.getTypeId());
            QName typeQName = QName.createQName(typeQNameModel.getNamespaceURI(), typeQNameModel.getLocalName());
            if (typeQNamePattern.isMatch(typeQName)) {
                child.add(new ContentRef(contentData.getUuid()));
            }
        }
        return child;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.pshow.repo.service.ContentService#getChild(org.pshow.repo.datamodel
     * .content.ContentRef, java.util.Set)
     */
    @Override
    public List<ContentRef> getChild(ContentRef contentRef, Set<QName> typeQNames) {
        List<ContentData> result = contentDao.getContentByParentUUID(contentRef.getId());
        List<ContentRef> child = new ArrayList<ContentRef>();
        for (ContentData contentData : result) {
            QNameModel typeQNameModel = qnameDao.findQNameById(contentData.getTypeId());
            QName typeQName = QName.createQName(typeQNameModel.getNamespaceURI(), typeQNameModel.getLocalName());
            if (typeQNames.contains(typeQName)) {
                child.add(new ContentRef(contentData.getUuid()));
            }
        }
        return child;
    }

    public void setNamespaceDao(NamespaceDao namespaceDao) {
        this.namespaceDao = namespaceDao;
    }

    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public WorkspaceRef createWorkspace(String name) throws DuplicateWorkspaceException {
        if (workspaceDao.findWorkspaceByName(name) != null) {
            throw new DuplicateWorkspaceException(String.format("workspace[%s] already exist.", name));
        }
        ContentData cdata = createRoot();
        Workspace workspace = createWorksapce(name, cdata);
        cdata.setWorkspaceId(workspace.getId());
        contentDao.updateContent(cdata);
        Workspace result = workspaceDao.findWorkspaceByName(name);
        return new WorkspaceRef(result.getUuid());
    }

    private Workspace createWorksapce(String name, ContentData cdata) {
        Workspace workspace = new Workspace();
        workspace.setName(name);
        workspace.setRootId(cdata.getId());
        workspaceDao.insertWorkspace(workspace);
        return workspace;
    }

    private ContentData createRoot() {
        ContentData cdata = new ContentData();
        cdata.setCreator("admin");
        cdata.setModifier("admin");
        cdata.setVersions(getDefaultVersion());
        cdata.setParentId(getRootParentId());
        cdata.setTypeId(getNoneType());
        cdata.setAclId(getDefaultAcl());
        contentDao.insertContent(cdata);
        return cdata;
    }

    private int getNoneType() {
        return -1;
    }

    @Override
    @Transactional(readOnly = true)
    public WorkspaceRef findWorkspace(String name) {
        Workspace workspace = workspaceDao.findWorkspaceByName(name);
        if(workspace == null){
        	return null;
        }
        return new WorkspaceRef(workspace.getUuid());
    }

    public void setWorkspaceDao(WorkspaceDao workspaceDao) {
        this.workspaceDao = workspaceDao;
    }

    public void setPropertyDao(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public void setSchemaHolder(ContentSchemaHolder schemaHolder) {
        this.schemaHolder = schemaHolder;
    }

    public void setQnameDao(QNameDao qnameDao) {
        this.qnameDao = qnameDao;
    }

}
