package xyz.seanchao.bookstore.service;

import xyz.seanchao.bookstore.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll(Integer userId);

    int addOrder(Orders order);
}
