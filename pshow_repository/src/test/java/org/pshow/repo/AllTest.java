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
package org.pshow.repo;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.content.WorkspaceRef;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.datamodel.namespace.QNamePattern;
import org.pshow.repo.service.ContentService;
import org.pshow.repo.service.DuplicateWorkspaceException;
import org.pshow.repo.service.TypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @author roy
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
public class AllTest extends BaseIntegrationTest {
	@Autowired
	private ContentService cs;
	@Test
	public void testIntercepter(){
		try {
            cs.getContent(null);
        } catch (MethodConstraintViolationException e) {
			assertEquals("{javax.validation.constraints.NotNull.message}", e.getConstraintViolations().iterator().next().getMessageTemplate());
			return;
		}
		
		fail("Was expecting a ConstraintViolationException.");
	}
	
	@Test
	public void testCreateWorksapce(){
	    try {
            WorkspaceRef workspace = cs.createWorkspace("default");
            fail("not to here");
            cs.getRoot(workspace);
        } catch (DuplicateWorkspaceException e) {
            assertEquals("workspace[default] already exist.", e.getMessage());
        }
	}
	
	@Test
	public void testGetroot(){
	    WorkspaceRef workspace = cs.findWorkspace("default");
	    ContentRef root = cs.getRoot(workspace);
	    assertNotNull(root);
	    assertNotNull(root.getId());
	    assertTrue(StringUtils.isNotEmpty(root.getId()));
	}
	
	@Test
    public void testCreateContent(){
        WorkspaceRef workspace = cs.findWorkspace("default");
        ContentRef root = cs.getRoot(workspace);
        
        testCreateFail(root);
        
        ContentRef first = testCreateSuccess(root);
        
        ContentRef second = testCreateWithPropertiesSuccess(root);
        
        testGetType(second);
        
        testMoveContent(second,first);
        
        testGetChildByFilter(first);
    }

    private void testGetChildByFilter(ContentRef first) {
        HashSet<QName> filterSet = Sets.newHashSet(QName.createQName("http://www.pshow.org/model/system/0.1", "descriptor"));
        List<ContentRef> child = cs.getChild(first, filterSet);
        assertNotNull(child);
        assertEquals(1, child.size());
        
        filterSet = Sets.newHashSet(QName.createQName("http://www.pshow.org/model/system/0.1", "base"));
        child = cs.getChild(first, filterSet);
        assertNotNull(child);
        assertEquals(0, child.size());
        
        child = cs.getChild(first, new QNamePattern() {
            
            @Override
            public boolean isMatch(QName qname) {
                return qname.equals(QName.createQName("http://www.pshow.org/model/system/0.1", "descriptor"));
            }
        });
        assertNotNull(child);
        assertEquals(1, child.size());
        
        child = cs.getChild(first, new QNamePattern() {
            
            @Override
            public boolean isMatch(QName qname) {
                return qname.equals(QName.createQName("http://www.pshow.org/model/system/0.1", "base"));
            }
        });
        assertNotNull(child);
        assertEquals(0, child.size());
    }

    private void testMoveContent(ContentRef second, ContentRef first) {
        cs.moveContent(second, first);
        List<ContentRef> child = cs.getChild(first);
        assertNotNull(child);
        assertEquals(1, child.size());
        assertEquals(child.get(0), second);
    }

    private ContentRef testCreateWithPropertiesSuccess(ContentRef root) {
        ContentRef createContent = null;
        Map<QName, Serializable> properties = Maps.newHashMap();
        properties.put(QName.createQName("http://www.pshow.org/model/system/0.1", "name"), RandomStringUtils.randomAlphanumeric(5));
        try {
            createContent = cs.createContent(root, QName.createQName("http://www.pshow.org/model/system/0.1", "descriptor"), properties);
        } catch (TypeException e) {
            e.printStackTrace();
            fail("not to here");
        }
        return createContent;
    }

    private ContentRef testCreateSuccess(ContentRef root) {
        ContentRef createContent = null;
        try {
            createContent = cs.createContent(root, QName.createQName("http://www.pshow.org/model/system/0.1", "base"));
        } catch (TypeException e) {
            e.printStackTrace();
            fail("not to here");
        }
        assertNotNull(createContent);
        return createContent;
    }

    private void testCreateFail(ContentRef root) {
        try {
            cs.createContent(root, QName.createQName("fdsafsdfsa", "test"));
            fail("not to here");
        } catch (TypeException e) {
            assertEquals("Create content error: type[QName [prefix=null, namespaceURI=fdsafsdfsa, localName=test]] not exist.", e.getMessage());
        }
    }

    private void testGetType(ContentRef createContent) {
        assertNotNull(createContent);
        QName type = cs.getType(createContent);
        assertEquals(QName.createQName("http://www.pshow.org/model/system/0.1", "descriptor"), type);
    }

}
