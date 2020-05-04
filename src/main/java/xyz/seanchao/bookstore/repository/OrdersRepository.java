package xyz.seanchao.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.seanchao.bookstore.entity.Orders;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByUserId(Integer userId);
}
