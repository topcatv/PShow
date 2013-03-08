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
public class Property {
	
	private boolean isOverride = false;
    private String name = null;
    private String title = null;
    private String description = null;
    private String propertyType = null;
    private boolean isMandatory = false;
    private boolean isMultiValued = false;
    private String defaultValue = null;
    private Boolean isIndexed = Boolean.TRUE;
    private Boolean isIndexedAtomically = null;
    private Boolean isStoredInIndex = null;
    private List<ConstraintModel> ConstraintModels = new ArrayList<ConstraintModel>();
    
    public List<ConstraintModel> getConstraintModels()
    {
        if (ConstraintModels == null)
        {
            return Collections.emptyList();
        }
        else
        {
            return ConstraintModels;
        }
    }
    
    public boolean hasConstraintModels()
    {
        return ((this.ConstraintModels != null) && (ConstraintModels.size() > 0));
    }
    
    public ConstraintModel addConstraintModelRef(String refName)
    {
        ConstraintModel ConstraintModel = new ConstraintModel();
        ConstraintModel.setRef(refName);
        ConstraintModels.add(ConstraintModel);
        return ConstraintModel;
    }
    
    
    public void removeConstraintModelRef(String refName)
    {
        List<ConstraintModel> cons = new ArrayList<ConstraintModel>(getConstraintModels());
        for (ConstraintModel con : cons)
        {
            if (con.getRef().equals(refName))
            {
                ConstraintModels.remove(con);
            }
        }
    }

	public boolean isOverride() {
    	return isOverride;
    }

	public void setOverride(boolean isOverride) {
    	this.isOverride = isOverride;
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

	public String getPropertyType() {
    	return propertyType;
    }

	public void setPropertyType(String propertyType) {
    	this.propertyType = propertyType;
    }

	public boolean isMandatory() {
    	return isMandatory;
    }

	public void setMandatory(boolean isMandatory) {
    	this.isMandatory = isMandatory;
    }

	public boolean isMultiValued() {
    	return isMultiValued;
    }

	public void setMultiValued(boolean isMultiValued) {
    	this.isMultiValued = isMultiValued;
    }

	public String getDefaultValue() {
    	return defaultValue;
    }

	public void setDefaultValue(String defaultValue) {
    	this.defaultValue = defaultValue;
    }

	public Boolean getIsIndexed() {
    	return isIndexed;
    }

	public void setIsIndexed(Boolean isIndexed) {
    	this.isIndexed = isIndexed;
    }

	public Boolean getIsIndexedAtomically() {
    	return isIndexedAtomically;
    }

	public void setIsIndexedAtomically(Boolean isIndexedAtomically) {
    	this.isIndexedAtomically = isIndexedAtomically;
    }

	public Boolean getIsStoredInIndex() {
    	return isStoredInIndex;
    }

	public void setIsStoredInIndex(Boolean isStoredInIndex) {
    	this.isStoredInIndex = isStoredInIndex;
    }

}
