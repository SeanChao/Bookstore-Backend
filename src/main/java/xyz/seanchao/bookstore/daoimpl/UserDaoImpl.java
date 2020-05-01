package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.seanchao.bookstore.dao.UserDao;
import xyz.seanchao.bookstore.entity.User;
import xyz.seanchao.bookstore.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public void addOne(User user) {
        userRepository.save(user);
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.checkUser(username, password);
    }
}
