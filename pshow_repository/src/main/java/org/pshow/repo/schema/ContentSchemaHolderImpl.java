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
package org.pshow.repo.schema;

import java.util.ArrayList;
import java.util.List;

import org.pshow.repo.dao.NamespaceDao;
import org.pshow.repo.datamodel.content.definition.ConstraintModel;
import org.pshow.repo.datamodel.content.definition.ContentFacet;
import org.pshow.repo.datamodel.content.definition.ContentType;
import org.pshow.repo.datamodel.content.definition.DataType;
import org.pshow.repo.datamodel.content.definition.PSModel;
import org.pshow.repo.datamodel.namespace.PSNamespace;
import org.pshow.repo.datamodel.namespace.QName;


/**
 * @author roy
 *
 */
public class ContentSchemaHolderImpl implements ContentSchemaHolder {
    
    private NamespaceDao namespaceDao;

    /* (non-Javadoc)
     * @see org.pshow.repo.schema.ContentSchemaHolder#registContentSchemas(java.util.List)
     */
    @Override
    public void registContentSchemas(List<PSModel> schemas) {
        for (PSModel psModel : schemas) {
            registNamespaces(psModel.getNamespaces());
            registDataTypes(psModel.getPropertyTypes());
            registTypes(psModel.getTypes());
            registFacets(psModel.getFacets());
            registConstraints(psModel.getConstraints());
        }
        //效验注册的schema是否正确
        checkAllSchema();

    }

    private void registConstraints(List<ConstraintModel> constraints) {
        // TODO Auto-generated method stub
        
    }

    private void registDataTypes(List<DataType> propertyTypes) {
        // TODO Auto-generated method stub
        
    }

    private void registFacets(List<ContentFacet> facets) {
        // TODO Auto-generated method stub
        
    }

    private void registTypes(List<ContentType> types) {
        // TODO Auto-generated method stub
        
    }

    private void registNamespaces(List<PSNamespace> list) {
        //加载已注册的namespace
        List<PSNamespace> exist_namespaces = loadNamespacesFromDB();
        if (list != null) {
            for (PSNamespace psNamespace : list) {
                if (!exist_namespaces.contains(psNamespace)) {
                    //如果从配置文件中加载的namespace不存在数据库中，写入数据库存并加入已存在namespace列表
                    namespaceDao.insertNamespace(psNamespace);
                    exist_namespaces.add(psNamespace);
                }
            }
        }
    }

    private List<PSNamespace> loadNamespacesFromDB() {
        ArrayList<PSNamespace> namespaces = new ArrayList<PSNamespace>();
        namespaces.addAll(namespaceDao.findAllNamespaces());
        return namespaces;
    }

    private void checkAllSchema() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.schema.ContentSchemaHolder#registContentSchema(org.pshow.repo.datamodel.content.definition.PSModel)
     */
    @Override
    public void registContentSchema(PSModel schema) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.pshow.repo.schema.ContentSchemaHolder#getNamespace(org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    public PSNamespace getNamespace(QName name) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.schema.ContentSchemaHolder#getAllNamespace()
     */
    @Override
    public List<PSNamespace> getAllNamespace() {
        return getRegisteredObject(QName.createQName("", "", null), new ArrayList<PSNamespace>());
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.schema.ContentSchemaHolder#getContentType(org.pshow.repo.datamodel.namespace.QName)
     */
    @Override
    public ContentType getContentType(QName name) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.pshow.repo.schema.ContentSchemaHolder#getAllContentType()
     */
    @Override
    public List<ContentType> getAllContentType() {
        // TODO Auto-generated method stub
        return null;
    }

    
    public void setNamespaceDao(NamespaceDao namespaceDao) {
        this.namespaceDao = namespaceDao;
    }

    @Override
    public boolean hasRegisteredObject() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <E> E getRegisteredObject(QName name, E e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ContentFacet getFacet(QName name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ContentFacet> getAllFacet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConstraintModel getConstraint(QName name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ConstraintModel> getAllConstraint() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasNamespace(QName name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasContentType(QName name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasFacet(QName name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasConstraint(QName name) {
        // TODO Auto-generated method stub
        return false;
    }

}
