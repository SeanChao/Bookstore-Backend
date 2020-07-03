package xyz.seanchao.bookstore.dao;

import xyz.seanchao.bookstore.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> findAll();

    User findOne(Integer id);

    User findByUsername(String username);

    void addOne(User user);

    User checkUser(String username, String password);

    User updateUser(User user);

    List<Map<String, Object>> userSalesStat(Date from, Date to);

    List<Map<String, Object>> userBookSalesStat(Integer id, Date from, Date to);
}
