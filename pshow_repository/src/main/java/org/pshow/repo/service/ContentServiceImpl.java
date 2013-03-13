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
public class ContentServiceImpl implements ContentService {

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getRoot()
	 */
	@Override
	public ContentRef getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getContent(java.lang.Long)
	 */
	@Override
	public ContentRef getContent(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#createContent(org.pshow.repo.datamodel.content.ContentRef, java.lang.String, org.pshow.repo.datamodel.namespace.QName)
	 */
	@Override
	public ContentRef createContent(ContentRef parentRef, String name, QName typeQName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#createContent(org.pshow.repo.datamodel.content.ContentRef, java.lang.String, org.pshow.repo.datamodel.namespace.QName, java.util.Map)
	 */
	@Override
	public ContentRef createContent(ContentRef parentRef, String name, QName typeQName, Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#moveContent(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public void moveContent(ContentRef moveToContentRef, ContentRef newParentRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getType(org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public QName getType(ContentRef contentRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getFacets(org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public Set<QName> getFacets(ContentRef contentRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#hasFacet(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
	 */
	@Override
	public boolean hasFacet(ContentRef contentRef, QName facetQName) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#deleteContent(org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public void deleteContent(ContentRef contentRef) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getProperties(org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public Map<QName, Serializable> getProperties(ContentRef contentRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getProperty(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
	 */
	@Override
	public Serializable getProperty(ContentRef contentRef, QName qname) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#setProperties(org.pshow.repo.datamodel.content.ContentRef, java.util.Map)
	 */
	@Override
	public void setProperties(ContentRef contentRef, Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#addProperties(org.pshow.repo.datamodel.content.ContentRef, java.util.Map)
	 */
	@Override
	public void addProperties(ContentRef contentRef, Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#setProperty(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QName, java.io.Serializable)
	 */
	@Override
	public void setProperty(ContentRef contentRef, QName qname, Serializable value) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#removeProperty(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
	 */
	@Override
	public void removeProperty(ContentRef contentRef, QName qname) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getParent(org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public ContentRef getParent(ContentRef contentRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getChild(org.pshow.repo.datamodel.content.ContentRef)
	 */
	@Override
	public List<ContentRef> getChild(ContentRef contentRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getChild(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QNamePattern)
	 */
	@Override
	public List<ContentRef> getChild(ContentRef contentRef, QNamePattern typeQNamePattern) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getChild(org.pshow.repo.datamodel.content.ContentRef, java.util.Set)
	 */
	@Override
	public List<ContentRef> getChild(ContentRef contentRef, Set<QName> typeQNames) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#getChildByName(org.pshow.repo.datamodel.content.ContentRef, java.lang.String)
	 */
	@Override
	public List<ContentRef> getChildByName(ContentRef contentRef, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#addFacet(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QName, java.util.Map)
	 */
	@Override
	public void addFacet(ContentRef contentRef, QName facetTypeQName, Map<QName, Serializable> facetProperties) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pshow.repo.service.ContentService#removeAspect(org.pshow.repo.datamodel.content.ContentRef, org.pshow.repo.datamodel.namespace.QName)
	 */
	@Override
	public void removeAspect(ContentRef contentRef, QName facetTypeQName) {
		// TODO Auto-generated method stub

	}

}
