package xyz.seanchao.bookstore.service;

import xyz.seanchao.bookstore.entity.User;

public interface UserService {
    void addUser(User user);

    User checkUser(String username, String password);
}
