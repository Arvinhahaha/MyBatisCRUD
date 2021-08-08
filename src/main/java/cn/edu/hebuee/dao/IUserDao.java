package cn.edu.hebuee.dao;

import cn.edu.hebuee.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    Boolean saveUser(User user);
    Boolean updateUser(User user);
    Boolean deleteUser(String id);
}
