package xyz.seanchao.bookstore.dao;

import xyz.seanchao.bookstore.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findOne(Integer id);

    User findByUsername(String username);

    void addOne(User user);

    User checkUser(String username, String password);

    User updateUser(User user);
}
