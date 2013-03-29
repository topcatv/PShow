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
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pshow.repo.datamodel.content.ContentRef;
import org.pshow.repo.datamodel.content.WorkspaceRef;
import org.pshow.repo.datamodel.content.definition.DataTypeUnSupportExeception;
import org.pshow.repo.datamodel.namespace.QName;
import org.pshow.repo.service.ContentService;
import org.pshow.repo.service.DuplicateWorkspaceException;
import org.pshow.repo.service.TypeNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;

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
        ContentRef createContent = null;
        try {
            createContent = cs.createContent(root, QName.createQName("fdsafsdfsa", "test"));
            fail("not to here");
        } catch (TypeNotExistException e) {
            assertEquals("Create content error: type[QName [prefix=null, namespaceURI=fdsafsdfsa, localName=test]] not exist.", e.getMessage());
        }
        try {
            createContent = cs.createContent(root, QName.createQName("http://www.pshow.org/model/system/0.1", "base"));
        } catch (TypeNotExistException e) {
            e.printStackTrace();
            fail("not to here");
        }
        assertNotNull(createContent);
        Map<QName, Serializable> properties = Maps.newHashMap();
        properties.put(QName.createQName("http://www.pshow.org/model/system/0.1", "node-uuid"), "this is a test");
        try {
            createContent = cs.createContent(root, QName.createQName("http://www.pshow.org/model/system/0.1", "base"), properties);
        } catch (TypeNotExistException e) {
            e.printStackTrace();
            fail("not to here");
        } catch (DataTypeUnSupportExeception e) {
            e.printStackTrace();
            fail("not to here");
        }
        assertNotNull(createContent);
    }

}
