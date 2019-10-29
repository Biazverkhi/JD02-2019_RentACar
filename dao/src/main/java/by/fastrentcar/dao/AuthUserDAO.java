package by.fastrentcar.dao;

import by.fastrentcar.model.AuthUser;
import by.fastrentcar.model.AuthUserUserDTO;
import by.fastrentcar.model.User;

import java.util.List;

public interface AuthUserDAO {

    AuthUser getByLoginT(String login);
    AuthUser getByIdT(Long id);

    List<AuthUserUserDTO> getListAuthUserUserDTO();

    Long addAuthUserUserT(AuthUser authuser, User user);

    AuthUserUserDTO getAuthUserUserDTO(String login);

    boolean updateAuthUserUserT(AuthUser authUser, User user);

    boolean deleteAuthUserT(Long id);
}
