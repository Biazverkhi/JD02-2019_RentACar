package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.AutoServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoServicesJpaRepository extends JpaRepository<AutoServicesEntity, Long> {
}
