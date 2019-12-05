package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.springdata.OrderDAO;
import by.fastrentcar.springdata.entities.OrderEntity;
import by.fastrentcar.springdata.repository.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultOrderDAO implements OrderDAO {

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Override
    public List<Order> getListOrderT() {
        List<OrderEntity> orderEntityList = orderJpaRepository.findAll();
        return orderEntityList.stream().map(OrderEntity::convertOrderByOrderEntity).collect(Collectors.toList());
    }

    @Override
    public List<Order> getListOrderByIdUserT(Long authuser_id) {
        List<OrderEntity> orderEntityList = orderJpaRepository.findByAuthUserEntityId(authuser_id);
        return orderEntityList.stream().map(OrderEntity::convertOrderByOrderEntity).collect(Collectors.toList());
    }

    @Override
    public Order getOrderByIdT(Long id) {
        Optional<OrderEntity> optional = orderJpaRepository.findById(id);
        return optional.isPresent() ? optional.get().convertOrderByOrderEntity() : null;
    }

    @Override
    public Long addOrderT(Order order) {
        return orderJpaRepository.save(new OrderEntity(order)).getId();
    }

    @Override
    public boolean updateOrderT(Order order) {
        addOrderT(order);
        return true;
    }

    @Override
    public boolean deleteOrderT(Long id) {
        orderJpaRepository.deleteById(id);
        return true;
    }


}
