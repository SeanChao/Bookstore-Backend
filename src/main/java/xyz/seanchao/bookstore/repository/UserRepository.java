package xyz.seanchao.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.seanchao.bookstore.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "from User where username = :username and password = :password")
    User checkUser(@Param("username") String username, @Param("password") String password);

    User findUserByUsername(String username);

    @Query(value = "call userSalesStat(:start, :end)", nativeQuery = true)
    List<Map<String, Object>> userSalesStat(@Param("start") Date start,
                                            @Param("end") Date end);
}
