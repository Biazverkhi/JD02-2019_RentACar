package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoJpaRepository extends JpaRepository<AutoEntity, Long> {
    // List<AutoEntity> findDistinct;
}
