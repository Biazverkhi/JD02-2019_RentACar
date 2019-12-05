package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.springdata.OrderDAO;
import by.fastrentcar.springdata.entities.OrderEntity;
import by.fastrentcar.springdata.repository.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDAO implements OrderDAO {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Override
    public Long addOrderT(Order order) {
        return orderJpaRepository.save(new OrderEntity(order)).getId();
    }

    @Override
    public boolean updateOrderT(Order order) {
        orderJpaRepository.save(new OrderEntity(order)).getId();
        return true;
    }

    @Override
    public boolean deleteOrderT(Long id) {

        orderJpaRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Order> getListOrderT() {
        List<OrderEntity> orderEntityList = orderJpaRepository.findAll();
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity oe : orderEntityList
        ) {
            orderList.add(oe.convertOrderByOrderEntity());
        }
        return orderList;
    }

    @Override
    public List<Order> getListOrderByIdUserT(Long authuser_id) {
        List<OrderEntity> orderEntityList = orderJpaRepository.findByAuthUserEntityId(authuser_id);
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity oe : orderEntityList
        ) {
            orderList.add(oe.convertOrderByOrderEntity());
        }

        return orderList;
    }

    @Override
    public Order getOrderByIdT(Long id) {
        return orderJpaRepository.getOne(id).convertOrderByOrderEntity();
    }
}
