package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.ConfigSpringDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;
import by.fastrentcar.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class DefaultUserService implements UserService {
    private DefaultUserService() {
    }

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    AuthUserDAO authUserDAO = new AnnotationConfigApplicationContext(ConfigSpringDAO.class).getBean(AuthUserDAO.class);

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
