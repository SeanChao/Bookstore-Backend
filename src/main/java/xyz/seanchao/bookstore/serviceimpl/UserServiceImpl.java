package xyz.seanchao.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.UserDao;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public int addUser(User user) {
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
}
