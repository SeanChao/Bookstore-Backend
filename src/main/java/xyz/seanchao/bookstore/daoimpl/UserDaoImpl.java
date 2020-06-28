package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.seanchao.bookstore.dao.UserDao;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.repository.UserRepository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void addOne(User user) {
        userRepository.save(user);
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.checkUser(username, password);
    }

    @Override
    public User updateUser(User user) {
        System.out.println("update user: " + user);
        return userRepository.findById(user.getId()).map((b) -> {
            if (user.getBlocked() != null) b.setBlocked(user.getBlocked());
            return userRepository.save(user);
        }).orElseGet(() -> userRepository.save(user));
    }

    @Override
    public List<Map<String, Object>> userSalesStat(Date from, Date to) {
        List<Map<String, Object>> queryResult = userRepository.userSalesStat(from, to);
        List<Map<String, Object>> statList = new ArrayList<>();
        for (Map<String, Object> item : queryResult) {
            Integer id = (Integer) item.get("user_id");
            BigDecimal sum = (BigDecimal) item.get("sum");
            User user = findOne(id);
            Map<String, Object> m = new HashMap<>();
            m.put("user", user);
            m.put("amount", sum);
            statList.add(m);
        }
        return statList;
    }
}
