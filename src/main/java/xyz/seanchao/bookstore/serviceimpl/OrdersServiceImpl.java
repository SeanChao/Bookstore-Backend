package xyz.seanchao.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.seanchao.bookstore.dao.OrdersDao;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.service.OrdersService;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer userId) {
        return ordersDao.findAll(userId);
    }

    @Override
    public int addOrder(Orders order) {
        ordersDao.addOrder(order);
        return 0;
    }
}
