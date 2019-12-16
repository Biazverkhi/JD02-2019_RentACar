package by.fastrentcar.springdata.repository;

import by.fastrentcar.springdata.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserJpaRepository extends JpaRepository<AuthUserEntity, Long> {
    AuthUserEntity findByLogin(String login);
}
//    @Modifying(clearAutomatically = true)
//    @Query("update AuthUserEntity set password= :password where id = :authUserId")
//    void updatePassword(@Param("authUserId") Long authUserId, @Param("password") String password);
