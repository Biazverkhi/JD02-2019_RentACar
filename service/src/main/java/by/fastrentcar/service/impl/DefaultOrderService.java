package by.fastrentcar.service.impl;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.service.OrderService;
import by.fastrentcar.springdata.OrderDAO;

import java.util.List;

public class DefaultOrderService implements OrderService {

    public DefaultOrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    private OrderDAO orderDAO;

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
