package org.pshow.repository.mybatis;

import java.util.List;

import org.pshow.entity.Role;

public interface RoleDao {

    List<Role> findAll();
    
    List<Role> getRolesByUser(long userid);

}
