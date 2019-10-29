package by.fastrentcar.dao;

import by.fastrentcar.model.AuthUser;
import by.fastrentcar.model.AuthUserUserDTO;

import java.util.List;

public interface AuthUserDAO {

    AuthUser getByLoginT(String login);
    AuthUser getByIdT(Long id);

    List<AuthUserUserDTO> getListAuthUserUserDTO();

    Long addAuthUserT(AuthUser authuser);

    AuthUserUserDTO getAuthUserUserDTO(String login);

    boolean updateAuthUserT(AuthUser authUser);

    boolean deleteAuthUserT(Long id);
}
