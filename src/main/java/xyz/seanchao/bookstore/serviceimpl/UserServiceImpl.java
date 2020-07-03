package xyz.seanchao.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.UserDao;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(Integer id) {
        return userDao.findOne(id);
    }

    @Override
    public int addUser(User user) {
        user.setBlocked(0);
        // email address backend validation
        String emailRegexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegexp);
        java.util.regex.Matcher m = p.matcher(user.getEmail());
        if (!m.matches()) {
            return 4;
        }
        // username duplication validation
        User tmp = userDao.findByUsername(user.getUsername());
        System.out.println("try find: " + tmp);
        if (tmp == null) {
            // add a new user
            user.setRole(1);
            userDao.addOne(user);
            return 0;
        } else {
            System.out.println("User exists");
            return 3;
        }
    }

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, password);
    }

    @Override
    public boolean blockUser(Integer id, Integer blocked) {
        if (id == null || blocked == null) return false;
        User user = userDao.findOne(id);
        user.setBlocked(blocked);
        userDao.updateUser(user);
        return true;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Map<String, Object>> userSalesStat(Date from, Date to) {
        return userDao.userSalesStat(from, to);
    }

    @Override
    public List<Map<String, Object>> userBookSalesStat(Integer id, Date from,
                                                       Date to) {
        System.out.println("service: " + id);
        return userDao.userBookSalesStat(id, from, to);
    }

}
