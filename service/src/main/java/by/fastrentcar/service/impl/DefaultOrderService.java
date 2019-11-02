package by.fastrentcar.service.impl;

import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.dao.impl.DefaultOrderDAO;
import by.fastrentcar.model.order.Order;
import by.fastrentcar.service.OrderService;

import java.util.List;

public class DefaultOrderService implements OrderService {
    private DefaultOrderService() {
    }

    private static class SingletonHolder {
        static final OrderService HOLDER_INSTANCE = new DefaultOrderService();

    }

    public static OrderService getInstance() {
        return DefaultOrderService.SingletonHolder.HOLDER_INSTANCE;
    }

    private OrderDAO defaultOrderDAO = DefaultOrderDAO.getInstance();

    @Override
    public Long addOrder(Order order) {
        return defaultOrderDAO.addOrderT(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return defaultOrderDAO.updateOrderT(order);
    }

    @Override
    public boolean deleteOrder(Long id) {
        return defaultOrderDAO.deleteOrderT(id);
    }

    @Override
    public List<Order> getListOrder() {
        return defaultOrderDAO.getListOrderT();
    }

    @Override
    public List<Order> getListOrderByIdUser(Long authuser_id) {
        return defaultOrderDAO.getListOrderByIdUserT( authuser_id);
    }

    @Override
    public Order getOrder(Long id) {
        return defaultOrderDAO.getOrderByIdT(id);
    }
}
