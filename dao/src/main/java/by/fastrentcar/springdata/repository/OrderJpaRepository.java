package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByAuthUserEntityId(Long id);
}
