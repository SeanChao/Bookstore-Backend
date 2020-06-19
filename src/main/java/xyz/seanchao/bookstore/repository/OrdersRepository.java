package xyz.seanchao.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.seanchao.bookstore.entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByUserId(Integer userId);

    List<Orders> findAll();

    @Query(value = "SELECT orders FROM Orders orders WHERE orders.time >= " +
            ":from AND orders.time <= :to AND orders.userId = :uid")
    List<Orders> findUserOrdersInTime(@Param("uid") Integer userId,
                                      @Param("from") Date from,
                                      @Param("to") Date to);
}
