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

import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.datamodel.namespace.QNamePattern;

/**
 * @author roy
 * 
 */
public interface ContentService {

	ContentRef getRoot();

	ContentRef getContent(Long id);

	ContentRef createContent(ContentRef parentRef, String name, QName typeQName);

	ContentRef createContent(ContentRef parentRef, String name, QName typeQName, Map<QName, Serializable> properties);

	void moveContent(ContentRef moveToContentRef, ContentRef newParentRef);

	QName getType(ContentRef contentRef);

	Set<QName> getFacets(ContentRef contentRef);

	boolean hasFacet(ContentRef contentRef, QName facetQName);

	void deleteContent(ContentRef contentRef);

	Map<QName, Serializable> getProperties(ContentRef contentRef);

	Serializable getProperty(ContentRef contentRef, QName qname);

	void setProperties(ContentRef contentRef, Map<QName, Serializable> properties);

	void addProperties(ContentRef contentRef, Map<QName, Serializable> properties);

	void setProperty(ContentRef contentRef, QName qname, Serializable value);

	void removeProperty(ContentRef contentRef, QName qname);

	ContentRef getParent(ContentRef contentRef);

	List<ContentRef> getChild(ContentRef contentRef);

	List<ContentRef> getChild(ContentRef contentRef, QNamePattern typeQNamePattern);

	List<ContentRef> getChild(ContentRef contentRef, Set<QName> typeQNames);

	List<ContentRef> getChildByName(ContentRef contentRef, String name);

	void addFacet(ContentRef contentRef, QName facetTypeQName, Map<QName, Serializable> facetProperties);
	
	void removeAspect(ContentRef contentRef, QName facetTypeQName);

}