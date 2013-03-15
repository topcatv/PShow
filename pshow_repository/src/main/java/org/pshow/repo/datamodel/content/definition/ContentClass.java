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
import java.util.List;

/**
 * @author roy
 * 
 */
public abstract class ContentClass {

    private String            name         = null;
    private String            title        = null;
    private String            description  = null;
    private String            parentName   = null;

    private List<Property>    properties   = new ArrayList<Property>();
    private List<Association> associations = new ArrayList<Association>();

    ContentClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public boolean isFacet() {
        return this instanceof ContentFacet;
    }

    public Property createProperty(String name) {
        Property property = new Property();
        property.setName(name);
        properties.add(property);
        return property;
    }

    public void removeProperty(String name) {
        Property property = getProperty(name);
        if (property != null) {
            properties.remove(property);
        }
    }

    public List<Property> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    public Property getProperty(String name) {
        for (Property candidate : properties) {
            if (candidate.getName().equals(name)) { return candidate; }
        }
        return null;
    }

    public Association createAssociation(String name) {
        Association association = new Association();
        association.setName(name);
        associations.add(association);
        return association;
    }

    public void removeAssociation(String name) {
        Association association = getAssociation(name);
        if (association != null) {
            associations.remove(association);
        }
    }

    public List<Association> getAssociations() {
        return Collections.unmodifiableList(associations);
    }

    public Association getAssociation(String name) {
        for (Association candidate : associations) {
            if (candidate.getName().equals(name)) { return candidate; }
        }
        return null;
    }

}
