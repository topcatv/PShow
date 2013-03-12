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

/**
 * @author roy
 * 
 */
public class ACE implements Serializable {

	private static final long	serialVersionUID	= -2348031188157601506L;
	private Permission	      permission;
	private Authority	      authority;
	private Boolean	          allowed;

	@Override
	public String toString() {
		return "ACE [permission=" + permission + ", authority=" + authority + ", allowed=" + allowed + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allowed == null) ? 0 : allowed.hashCode());
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ACE other = (ACE) obj;
		if (allowed == null) {
			if (other.allowed != null) return false;
		} else if (!allowed.equals(other.allowed)) return false;
		if (authority == null) {
			if (other.authority != null) return false;
		} else if (!authority.equals(other.authority)) return false;
		if (permission == null) {
			if (other.permission != null) return false;
		} else if (!permission.equals(other.permission)) return false;
		return true;
	}

}
