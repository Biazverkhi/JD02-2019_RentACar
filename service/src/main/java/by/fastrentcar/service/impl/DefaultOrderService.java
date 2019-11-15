package by.fastrentcar.service.impl;

import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.model.order.Order;
import by.fastrentcar.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class DefaultOrderService implements OrderService {

    private static class SingletonHolder {
        static final OrderService HOLDER_INSTANCE = new DefaultOrderService();

    }

    public static OrderService getInstance() {
        return DefaultOrderService.SingletonHolder.HOLDER_INSTANCE;
    }

    private OrderDAO orderDAO = new AnnotationConfigApplicationContext("by.fastrentcar.dao").getBean(OrderDAO.class);

    @Override
    public Long addOrder(Order order) {
        return orderDAO.addOrderT(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.updateOrderT(order);
    }

    @Override
    public boolean deleteOrder(Long id) {
        return orderDAO.deleteOrderT(id);
    }

    @Override
    public List<Order> getListOrder() {
        return orderDAO.getListOrderT();
    }

    @Override
    public List<Order> getListOrderByIdUser(Long authuser_id) {
        return orderDAO.getListOrderByIdUserT(authuser_id);
    }

    @Override
    public Order getOrder(Long id) {
        return orderDAO.getOrderByIdT(id);
    }
}
