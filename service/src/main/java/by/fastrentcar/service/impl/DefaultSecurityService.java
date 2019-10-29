package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.impl.DefaultAuthUserDAO;
import by.fastrentcar.model.AuthUser;
import by.fastrentcar.service.SecurityService;

public class DefaultSecurityService implements SecurityService {
    public DefaultSecurityService() {
    }

    private static class SingletonHolder {
        static final SecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }

    public static SecurityService getInstance() {
        return DefaultSecurityService.SingletonHolder.HOLDER_INSTANCE;
    }

    private AuthUserDAO defaultAuthUserDAO = DefaultAuthUserDAO.getInstance();

    public AuthUser login(String login, String password) {
        AuthUser authuser = defaultAuthUserDAO.getByLoginT(login);
        return authuser != null && authuser.getPassword().equals(password) ? authuser : null;
    }
}
