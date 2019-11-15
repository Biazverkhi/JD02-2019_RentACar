package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.ConfigSpringDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.service.SecurityService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DefaultSecurityService implements SecurityService {
    private DefaultSecurityService() {
    }

    private static class SingletonHolder {
        static final SecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }

    public static SecurityService getInstance() {
        return DefaultSecurityService.SingletonHolder.HOLDER_INSTANCE;
    }

    AuthUserDAO authUserDAO = new AnnotationConfigApplicationContext(ConfigSpringDAO.class).getBean(AuthUserDAO.class);

    public AuthUserDTO login(String login, String password) {
        AuthUser authuser = authUserDAO.getByLoginT(login);

        return authuser != null && authuser.getPassword().equals(password)
                ? new AuthUserDTO(authuser.getId(), authuser.getLogin(), authuser.getRole(), authuser.getUserId())
                : null;
    }

}
