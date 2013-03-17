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
package org.pshow.repo.datamodel.content.definition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pshow.repo.datamodel.namespace.PSNamespace;

/**
 * @author roy
 * 
 */
public class PSModel {

    private String                name;
    private String                description;
    private String                author;
    private Date                  published;
    private String                version;

    private List<PSNamespace>     namespaces  = new ArrayList<PSNamespace>();
    private List<PSNamespace>     imports     = new ArrayList<PSNamespace>();
    private List<DataType>        dataTypes   = new ArrayList<DataType>();
    private List<ContentType>     types       = new ArrayList<ContentType>();
    private List<ContentFacet>    facets     = new ArrayList<ContentFacet>();
    private List<ConstraintModel> constraints = new ArrayList<ConstraintModel>();

    private PSModel() {

    }

    @SuppressWarnings({ "unused", "rawtypes" })
    private static List createList() {
        return new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public PSNamespace createNamespace(String uri, String prefix) {
        PSNamespace namespace = new PSNamespace();
        namespace.setUri(uri);
        namespace.setPrefix(prefix);
        namespaces.add(namespace);
        return namespace;
    }

    public void removeNamespace(String uri) {
        PSNamespace namespace = getNamespace(uri);
        if (namespace != null) {
            namespaces.remove(namespace);
        }
    }

    public List<PSNamespace> getNamespaces() {
        return Collections.unmodifiableList(namespaces);
    }

    public PSNamespace getNamespace(String uri) {
        for (PSNamespace candidate : namespaces) {
            if (candidate.getUri().equals(uri)) { return candidate; }
        }
        return null;
    }

    public PSNamespace createImport(String uri, String prefix) {
        PSNamespace namespace = new PSNamespace();
        namespace.setUri(uri);
        namespace.setPrefix(prefix);
        imports.add(namespace);
        return namespace;
    }

    public void removeImport(String uri) {
        PSNamespace namespace = getImport(uri);
        if (namespace != null) {
            imports.remove(namespace);
        }
    }

    public List<PSNamespace> getImports() {
        return Collections.unmodifiableList(imports);
    }

    public PSNamespace getImport(String uri) {
        for (PSNamespace candidate : imports) {
            if (candidate.getUri().equals(uri)) { return candidate; }
        }
        return null;
    }

    public List<ConstraintModel> getConstraints() {
        return Collections.unmodifiableList(constraints);
    }

    public ConstraintModel getConstraint(String name) {
        for (ConstraintModel candidate : constraints) {
            if (candidate.getName().equals(name)) { return candidate; }
        }
        return null;
    }

    public ConstraintModel createConstraint(String name, String type) {
        ConstraintModel constraint = new ConstraintModel();
        constraint.setName(name);
        constraint.setType(type);
        constraints.add(constraint);
        return constraint;
    }

    public void removeConstraint(String name) {
        ConstraintModel constraint = getConstraint(name);
        if (constraint != null) {
            constraints.remove(constraint);
        }
    }

    public DataType createPropertyType(String name) {
        DataType type = new DataType();
        type.setName(name);
        dataTypes.add(type);
        return type;
    }

    public void removePropertyType(String name) {
        DataType type = getPropertyType(name);
        if (type != null) {
            dataTypes.remove(name);
        }
    }

    public List<DataType> getPropertyTypes() {
        return Collections.unmodifiableList(dataTypes);
    }

    public DataType getPropertyType(String name) {
        for (DataType candidate : dataTypes) {
            if (candidate.getName().equals(name)) { return candidate; }
        }
        return null;
    }

    public ContentType createType(String name) {
        ContentType type = new ContentType();
        type.setName(name);
        types.add(type);
        return type;
    }

    public void removeType(String name) {
        ContentType type = getType(name);
        if (type != null) {
            types.remove(type);
        }
    }

    public List<ContentType> getTypes() {
        return Collections.unmodifiableList(types);
    }

    public ContentType getType(String name) {
        for (ContentType candidate : types) {
            if (candidate.getName().equals(name)) { return candidate; }
        }
        return null;
    }

    public ContentFacet createFacet(String name) {
        ContentFacet facet = new ContentFacet();
        facet.setName(name);
        facets.add(facet);
        return facet;
    }

    public void removeFacet(String name) {
        ContentFacet facet = getFacet(name);
        if (facet != null) {
            facets.remove(facet);
        }
    }

    public List<ContentFacet> getFacets() {
        return Collections.unmodifiableList(facets);
    }

    public ContentFacet getFacet(String name) {
        for (ContentFacet candidate : facets) {
            if (candidate.getName().equals(name)) { return candidate; }
        }
        return null;
    }

}
