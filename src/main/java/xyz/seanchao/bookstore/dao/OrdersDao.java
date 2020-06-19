package xyz.seanchao.bookstore.dao;

import xyz.seanchao.bookstore.entity.Orders;

import java.util.Date;
import java.util.List;

public interface OrdersDao {

    List<Orders> findAll(Integer userId);

    List<Orders> findUserOrdersByDate(Integer userId, Date from, Date to);

    List<Orders> findAllUserTimeOrders(Date from, Date to);

    int addOrder(Orders order);

}
