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

	private Boolean	              isOverride	         = false;
	private String	              name	             = null;
	private String	              title	             = null;
	private String	              description	     = null;
	private String	              propertyType	     = null;
	private Boolean	              mandatory	         = Boolean.FALSE;
	private Boolean	              multiValued	     = Boolean.FALSE;
	private String	              defaultValue	     = null;
	private Boolean	              indexed	         = Boolean.TRUE;
	private Boolean	              indexedAtomically	 = null;
	private Boolean	              storedInIndex	     = null;
	private List<ConstraintModel>	constraintModels	= new ArrayList<ConstraintModel>();

	public List<ConstraintModel> getConstraintModels() {
		if (constraintModels == null) {
			return Collections.emptyList();
		} else {
			return constraintModels;
		}
	}

	public boolean hasConstraintModels() {
		return ((this.constraintModels != null) && (constraintModels.size() > 0));
	}

	public ConstraintModel addConstraintModelRef(String refName) {
		ConstraintModel ConstraintModel = new ConstraintModel();
		ConstraintModel.setRef(refName);
		constraintModels.add(ConstraintModel);
		return ConstraintModel;
	}

	public void removeConstraintModelRef(String refName) {
		List<ConstraintModel> cons = new ArrayList<ConstraintModel>(
		        getConstraintModels());
		for (ConstraintModel con : cons) {
			if (con.getRef().equals(refName)) {
				constraintModels.remove(con);
			}
		}
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Boolean isMultiValued() {
		return multiValued;
	}

	public void setMultiValued(Boolean multiValued) {
		this.multiValued = multiValued;
	}

	public Boolean isIndexed() {
		return indexed;
	}

	public void setIndexed(Boolean indexed) {
		this.indexed = indexed;
	}

	public Boolean isIndexedAtomically() {
		return indexedAtomically;
	}

	public void setIndexedAtomically(Boolean indexedAtomically) {
		this.indexedAtomically = indexedAtomically;
	}

	public Boolean isStoredInIndex() {
		return storedInIndex;
	}

	public void setStoredInIndex(Boolean storedInIndex) {
		this.storedInIndex = storedInIndex;
	}

	public Boolean isOverride() {
    	return isOverride;
    }

	public void setOverride(Boolean isOverride) {
    	this.isOverride = isOverride;
    }

}
