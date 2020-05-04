package xyz.seanchao.bookstore.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.seanchao.bookstore.dao.OrdersDao;
import xyz.seanchao.bookstore.entity.Orders;
import xyz.seanchao.bookstore.repository.OrdersRepository;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> findAll(Integer userId) {
        return ordersRepository.findAllByUserId(userId);
    }

    @Override
    public int addOrder(Orders order) {
        ordersRepository.save(order);
        return 0;
    }
}
