package by.fastrentcar.service;

import by.fastrentcar.model.order.Order;

import java.util.List;

public interface OrderService {
    Long addOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(Long id);

    List<Order> getListOrder();
    List<Order> getListOrderByIdUser(Long authuser_id);


    Order getOrder(Long id);

}
