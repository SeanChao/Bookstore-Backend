package xyz.seanchao.bookstore.dao;

import xyz.seanchao.bookstore.entity.User;

public interface UserDao {
    User findOne(Integer id);

    void addOne(User user);
}
