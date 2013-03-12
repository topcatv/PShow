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
public class Permission implements Serializable {

	private static final long	serialVersionUID	= 4293099603975810524L;

	private String	          name;
	private String	          permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
	    result = prime * result + ((permission == null) ? 0 : permission.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    Permission other = (Permission) obj;
	    if (name == null) {
		    if (other.name != null) return false;
	    } else if (!name.equals(other.name)) return false;
	    if (permission == null) {
		    if (other.permission != null) return false;
	    } else if (!permission.equals(other.permission)) return false;
	    return true;
    }

	@Override
    public String toString() {
	    return "Permission [name=" + name + ", permission=" + permission + "]";
    }

}
