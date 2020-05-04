package xyz.seanchao.bookstore.dao;

import xyz.seanchao.bookstore.entity.Orders;

import java.util.List;

public interface OrdersDao {

    List<Orders> findAll(Integer userId);

    int addOrder(Orders order);

}
