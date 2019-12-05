package by.fastrentcar.springdata;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;

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
