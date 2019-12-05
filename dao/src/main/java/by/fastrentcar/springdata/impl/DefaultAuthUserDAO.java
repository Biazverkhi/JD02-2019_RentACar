package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;
import by.fastrentcar.springdata.AuthUserDAO;
import by.fastrentcar.springdata.entities.AuthUserEntity;
import by.fastrentcar.springdata.entities.UserEntity;
import by.fastrentcar.springdata.repository.AuthUserJpaRepository;
import by.fastrentcar.springdata.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserDAO implements AuthUserDAO {
    @Autowired
    AuthUserJpaRepository authUserJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public AuthUser getByLoginT(String login) {
        return authUserJpaRepository.findByLogin(login).convertAuthUserByAuthUserEntity();
    }

    @Override
    public AuthUser getByIdT(Long id) {
        return authUserJpaRepository.getOne(id).convertAuthUserByAuthUserEntity();
    }

    @Override
    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        List<AuthUserEntity> authUserEntityList = authUserJpaRepository.findAll();
        List<AuthUserUserDTO> authUserUserDTOList = new ArrayList<>();
        for (AuthUserEntity aue : authUserEntityList) {
            authUserUserDTOList.add(new AuthUserUserDTO(aue.convertAuthUserByAuthUserEntity(), aue.getUserEntity().convertUserbyUserEntity()));
        }
        return authUserUserDTOList;
    }

    @Override
    public Long addAuthUserUserT(AuthUser authuser, User user) {
        UserEntity userEntity = new UserEntity(user);
        AuthUserEntity authUserEntity = new AuthUserEntity(authuser, userEntity);
        userEntity.setAuthUserEntity(authUserEntity);
        return userJpaRepository.save(userEntity).getAuthUserEntity().getId();


    }

    @Override
    public AuthUserUserDTO getAuthUserUserDTO(String login) {
        AuthUserEntity authUserEntity = authUserJpaRepository.findByLogin(login);
        UserEntity userEntity = authUserEntity.getUserEntity();
        return new AuthUserUserDTO(authUserEntity.convertAuthUserByAuthUserEntity(), userEntity.convertUserbyUserEntity());
    }

    @Override
    public boolean updateAuthUserUserT(AuthUser authUser, User user) {
        addAuthUserUserT(authUser, user);
        return true;
    }

    @Override
    public boolean deleteAuthUserT(Long id) {
        authUserJpaRepository.deleteById(id);
        return true;
    }
}
