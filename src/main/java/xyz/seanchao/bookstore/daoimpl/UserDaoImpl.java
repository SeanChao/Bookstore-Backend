package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.seanchao.bookstore.dao.UserDao;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.repository.UserRepository;

import java.util.List;

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
}
