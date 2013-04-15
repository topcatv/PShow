package org.pshow.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.ImmutableList;

/**
 * 角色.
 * 
 * @author calvin
 */
public class Role extends IdEntity {

	private String name;

	private String permissions;

	public Role() {
	}

	public Role(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<String> getPermissionList() {
		return ImmutableList.copyOf(StringUtils.split(permissions, ","));
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
