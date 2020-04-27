package xyz.seanchao.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.seanchao.bookstore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
