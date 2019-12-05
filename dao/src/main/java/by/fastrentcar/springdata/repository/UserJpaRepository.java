package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
