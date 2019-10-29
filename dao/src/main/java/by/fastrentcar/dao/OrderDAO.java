package by.fastrentcar.dao;

import by.fastrentcar.model.order.Order;
import java.util.List;

public interface OrderDAO {
    Long addOrderT(Order order);

    boolean updateOrderT(Order order);

    boolean deleteOrderT(Long id);

    List<Order> getListOrderT();
    List<Order> getListOrderByIdUserT(Long authuser_id);

    Order getOrderByIdT(Long id);
}
