package xyz.seanchao.bookstore.service;

import xyz.seanchao.bookstore.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findOne(Integer id);

    int addUser(User user);

    User checkUser(String username, String password);

    boolean blockUser(Integer id, Integer blocked);
}
