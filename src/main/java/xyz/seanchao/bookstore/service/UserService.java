package xyz.seanchao.bookstore.service;

import xyz.seanchao.bookstore.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    User findOne(Integer id);

    int addUser(User user);

    User checkUser(String username, String password);

    boolean blockUser(Integer id, Integer blocked);

    List<Map<String, Object>> userSalesStat(Date from, Date to);

    List<Map<String, Object>> userBookSalesStat(Integer id, Date from, Date to);
}
