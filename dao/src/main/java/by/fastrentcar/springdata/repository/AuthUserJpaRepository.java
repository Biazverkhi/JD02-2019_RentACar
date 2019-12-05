package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserJpaRepository extends JpaRepository<AuthUserEntity, Long> {
    AuthUserEntity findByLogin(String login);
}
