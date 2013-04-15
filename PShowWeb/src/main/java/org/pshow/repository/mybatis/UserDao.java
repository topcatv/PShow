package org.pshow.repository.mybatis;

import java.util.List;

import org.pshow.entity.User;

public interface UserDao {

	User findByName(String name);

	User findByLoginName(String loginName);

    void save(User user);

    List<User> findAll();

    User findOne(Long id);

    Long count();
}
