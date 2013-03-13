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
package org.pshow.repo.audit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodAuditInterceptor implements MethodInterceptor {

	private final ThreadLocal<Boolean>	inAudit	= new ThreadLocal<Boolean>();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Boolean wasInAudit = inAudit.get();
		try {
			if (wasInAudit != null) {
				return invocation.proceed();
			}
			Auditable auditableDef = invocation.getMethod().getAnnotation(Auditable.class);
			if (auditableDef == null) {
				return invocation.proceed();
			}

			Object[] arguments = invocation.getArguments();
			Map<String, Serializable> auditArguments = getAuditArguments(arguments, auditableDef);
			inAudit.set(Boolean.TRUE);
			return auditMethod(invocation, auditableDef, auditArguments);
		} finally {
			inAudit.set(wasInAudit);
		}
	}

	private Object auditMethod(MethodInvocation invocation, Auditable auditableDef, Map<String, Serializable> auditArguments) throws Throwable {
		Iterator<Entry<String, Serializable>> iterator = auditArguments.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.io.Serializable> entry = (Map.Entry<java.lang.String, java.io.Serializable>) iterator.next();
			System.out.println("argument_name:" + entry.getKey() + ",argument_value:" + entry.getValue());
		}
		Object ret = null;
		Throwable thrown = null;
		try {
			ret = invocation.proceed();
		} catch (Throwable e) {
			thrown = e;
		}
		if (thrown != null) {
			throw thrown;
		} else {
			return ret;
		}
	}

	private Map<String, Serializable> getAuditArguments(Object[] arguments, Auditable auditableDef) {
		String[] params = auditableDef.parameters();
		boolean[] recordable = auditableDef.recordable();

		Map<String, Serializable> namedArgs = new HashMap<String, Serializable>();
		for (int i = 0; i < arguments.length; i++) {
			if (i >= params.length) {
				break;
			}
			if (i < recordable.length && !recordable[i]) {
				continue;
			}
			Serializable value = getRecordableValue(arguments[i]);

			namedArgs.put(params[i], value);
		}
		return namedArgs;
	}

	private Serializable getRecordableValue(Object object) {
		if ((object instanceof Serializable) || (object instanceof String)) {
			return (Serializable) object;
		}
		return String.valueOf(object);
	}

}
