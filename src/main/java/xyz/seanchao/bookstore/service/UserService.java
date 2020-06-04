package xyz.seanchao.bookstore.service;

import xyz.seanchao.bookstore.entity.User;

public interface UserService {
    int addUser(User user);

    User checkUser(String username, String password);
}
