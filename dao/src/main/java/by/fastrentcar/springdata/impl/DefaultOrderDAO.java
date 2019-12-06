package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.springdata.OrderDAO;
import by.fastrentcar.springdata.entities.AuthUserEntity;
import by.fastrentcar.springdata.entities.OrderEntity;
import by.fastrentcar.springdata.repository.AuthUserJpaRepository;
import by.fastrentcar.springdata.repository.OrderJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultOrderDAO implements OrderDAO {

    private OrderJpaRepository orderJpaRepository;
    private AuthUserJpaRepository authUserJpaRepository;

    public DefaultOrderDAO(OrderJpaRepository orderJpaRepository, AuthUserJpaRepository authUserJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.authUserJpaRepository = authUserJpaRepository;
    }

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
        return optional.map(OrderEntity::convertOrderByOrderEntity).orElse(null);
    }

    @Override
    public Long addOrderT(Order order) {
        OrderEntity oe = new OrderEntity(order);
        AuthUserEntity aue = authUserJpaRepository.findById(oe.getAuthuserId()).orElse(null);
        oe.setAuthUserEntity(aue);
        return orderJpaRepository.save(oe).getId();

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
