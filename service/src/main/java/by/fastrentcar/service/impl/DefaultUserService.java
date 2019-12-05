package by.fastrentcar.service.impl;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;
import by.fastrentcar.service.UserService;
import by.fastrentcar.springdata.AuthUserDAO;

import java.util.List;

public class DefaultUserService implements UserService {

    AuthUserDAO authUserDAO;

    public DefaultUserService(AuthUserDAO authUserDAO) {

    }

    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        return authUserDAO.getListAuthUserUserDTO();
    }

    public AuthUserUserDTO getAuthUserUserDTO(String login) {
        return authUserDAO.getAuthUserUserDTO(login);
    }

    public Long addCustomer(AuthUser authUser, User user) {
        return authUserDAO.addAuthUserUserT(authUser, user);
    }

    public boolean updateCustomer(AuthUser authUser, User user) {
        return authUserDAO.updateAuthUserUserT(authUser, user);
    }

    public boolean deleteCutomer(Long id) {
        return authUserDAO.deleteAuthUserT(id);
    }
}
