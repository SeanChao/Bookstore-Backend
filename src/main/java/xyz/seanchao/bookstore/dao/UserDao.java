package xyz.seanchao.bookstore.dao;

import xyz.seanchao.bookstore.entity.User;

public interface UserDao {
    User findOne(Integer id);

    User findByUsername(String username);

    void addOne(User user);

    User checkUser(String username, String password);
}
