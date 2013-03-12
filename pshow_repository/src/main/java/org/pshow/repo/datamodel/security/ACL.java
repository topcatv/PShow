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
package org.pshow.repo.datamodel.security;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author roy
 * 
 */
public class ACL implements Serializable {

	private static final long	serialVersionUID	= 8415086739225097610L;
	private long	          id;
	private List<ACE>	      aces;
	private Boolean	          inherits;
	private long	          parentAcl;

	public Boolean isInherits() {
		return inherits;
	}

	public void setInherits(Boolean inherits) {
		this.inherits = inherits;
	}

	public List<ACE> getAces() {
		return Collections.unmodifiableList(aces);
	}

	public void addAces(List<ACE> aces) {
		this.aces.addAll(aces);
	}

	public void addAce(ACE ace) {
		this.aces.add(ace);
	}

	public void removeAce(ACE ace) {
		this.aces.remove(ace);
	}

	public long getParentAcl() {
		return parentAcl;
	}

	public void setParentAcl(long parentAcl) {
		this.parentAcl = parentAcl;
	}

	public long getId() {
		return id;
	}
}
