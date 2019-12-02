package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.service.SecurityService;

public class DefaultSecurityService implements SecurityService {

    public DefaultSecurityService(AuthUserDAO authUserDAO) {
        this.authUserDAO = authUserDAO;
    }

    AuthUserDAO authUserDAO;

    public AuthUserDTO login(String login, String password) {
        AuthUser authuser = authUserDAO.getByLoginT(login);

        return authuser != null && authuser.getPassword().equals(password)
                ? new AuthUserDTO(authuser.getId(), authuser.getLogin(), authuser.getRole(), authuser.getUserId())
                : null;
    }

}
