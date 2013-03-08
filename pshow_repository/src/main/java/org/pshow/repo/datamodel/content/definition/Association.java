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

/**
 * @author roy
 * 
 */
public class Association {
	private String	name	          = null;
	private String	title	          = null;
	private String	description	      = null;
	private Boolean	isSourceMandatory	= null;
	private Boolean	isSourceMany	  = null;
	private String	targetClassName	  = null;
	private Boolean	isTargetMandatory	= null;
	private Boolean	isTargetMany	  = null;

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

	public boolean isSourceMandatory() {
		return isSourceMandatory;
	}

	public void setIsSourceMandatory(Boolean isSourceMandatory) {
		this.isSourceMandatory = isSourceMandatory;
	}

	public boolean isSourceMany() {
		return isSourceMany;
	}

	public void setIsSourceMany(Boolean isSourceMany) {
		this.isSourceMany = isSourceMany;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public boolean isTargetMandatory() {
		return isTargetMandatory;
	}

	public void setIsTargetMandatory(Boolean isTargetMandatory) {
		this.isTargetMandatory = isTargetMandatory;
	}

	public boolean isTargetMany() {
		return isTargetMany;
	}

	public void setIsTargetMany(Boolean isTargetMany) {
		this.isTargetMany = isTargetMany;
	}
}
