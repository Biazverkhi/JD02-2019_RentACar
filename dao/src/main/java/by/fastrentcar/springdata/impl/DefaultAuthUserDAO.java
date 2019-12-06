package by.fastrentcar.springdata.impl;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;
import by.fastrentcar.springdata.AuthUserDAO;
import by.fastrentcar.springdata.entities.AuthUserEntity;
import by.fastrentcar.springdata.entities.UserEntity;
import by.fastrentcar.springdata.repository.AuthUserJpaRepository;
import by.fastrentcar.springdata.repository.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultAuthUserDAO implements AuthUserDAO {
    private AuthUserJpaRepository authUserJpaRepository;
    private UserJpaRepository userJpaRepository;

    public DefaultAuthUserDAO(AuthUserJpaRepository authUserJpaRepository, UserJpaRepository userJpaRepository) {
        this.authUserJpaRepository = authUserJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public AuthUser getByLoginT(String login) {
        AuthUserEntity authUserEntity = authUserJpaRepository.findByLogin(login);
        return authUserEntity == null ? null : authUserEntity.convertAuthUserByAuthUserEntity();
    }

    @Override
    public AuthUser getByIdT(Long id) {
        Optional<AuthUserEntity> optionalAuthUserEntity = authUserJpaRepository.findById(id);
        return optionalAuthUserEntity.map(AuthUserEntity::convertAuthUserByAuthUserEntity).orElse(null);
    }

    @Override
    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        List<AuthUserEntity> authUserEntityList = authUserJpaRepository.findAll();
//        List<AuthUserUserDTO> authUserUserDTOList = new ArrayList<>();
//        Function<AuthUserEntity,AuthUserUserDTO> func=s->new AuthUserUserDTO(s.convertAuthUserByAuthUserEntity(),s.getUserEntity().convertUserbyUserEntity());
//        for (AuthUserEntity aue : authUserEntityList) {
//            authUserUserDTOList.add(func.apply(aue));        }
        return authUserEntityList.isEmpty()
                ? new ArrayList<>()
                : authUserEntityList.stream()
                .map(s -> new AuthUserUserDTO(s.convertAuthUserByAuthUserEntity(), s.getUserEntity().convertUserbyUserEntity()))
                .collect(Collectors.toList());
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
        return authUserEntity == null ? null : new AuthUserUserDTO(authUserEntity.convertAuthUserByAuthUserEntity(), authUserEntity.getUserEntity().convertUserbyUserEntity());
    }

    @Override
    public boolean updateAuthUserUserT(AuthUser authUser, User user) {
        addAuthUserUserT(authUser, user);
        return true;
    }

    @Override
    public boolean deleteAuthUserT(Long id) {
        userJpaRepository.deleteById(getByIdT(id).getUserId());
        return true;
    }
}
