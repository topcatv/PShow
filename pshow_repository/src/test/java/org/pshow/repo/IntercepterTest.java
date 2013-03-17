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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.validator.method.MethodConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pshow.repo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author roy
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
public class IntercepterTest extends BaseIntegrationTest {
	@Autowired
	private ContentService cs;
	
	@Test
	public void test(){
		try {
	        cs.getContent(null);
        } catch (MethodConstraintViolationException e) {
			assertEquals("{javax.validation.constraints.NotNull.message}", e.getConstraintViolations().iterator().next().getMessageTemplate());
			return;
		}
		
		fail("Was expecting a ConstraintViolationException.");
	}

}
