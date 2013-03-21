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

import java.util.List;

import org.pshow.repo.datamodel.content.definition.ConstraintModel;
import org.pshow.repo.datamodel.content.definition.ContentFacet;
import org.pshow.repo.datamodel.content.definition.ContentType;
import org.pshow.repo.datamodel.content.definition.PSModel;
import org.pshow.repo.datamodel.namespace.PSNamespace;
import org.pshow.repo.datamodel.namespace.QName;


/**
 * @author roy
 *
 */
public interface ContentSchemaHolder {

    void registContentSchemas(List<PSModel> schemas);
    
    void registContentSchema(PSModel schema);
    
    boolean hasRegisteredObject();
    
    <E> E getRegisteredObject(QName name, E e);
    
    boolean hasNamespace(QName name);
    
    PSNamespace getNamespace(QName name);
    
    List<PSNamespace> getAllNamespace();
    
    boolean hasContentType(QName name);
    
    ContentType getContentType(QName name);
    
    List<ContentType> getAllContentType();
    
    boolean hasFacet(QName name);
    
    ContentFacet getFacet(QName name);
    
    List<ContentFacet> getAllFacet();
    
    boolean hasConstraint(QName name);
    
    ConstraintModel getConstraint(QName name);
    
    List<ConstraintModel> getAllConstraint();

}
