package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.AuthUserUserDTO;
import by.fastrentcar.dao.UserDAO;
import by.fastrentcar.dao.impl.DefaultAuthUserDAO;
import by.fastrentcar.dao.impl.DefaultUserDAO;
import by.fastrentcar.model.AuthUser;
import by.fastrentcar.model.User;
import by.fastrentcar.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {
    public DefaultUserService() {
    }

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    private AuthUserDAO defaultAuthUserDAO = DefaultAuthUserDAO.getInstance();

    @Override
    public Long addCustomer(AuthUser authUser, User user) {
        return defaultAuthUserDAO.addAuthUserUserT(authUser, user);
    }

    @Override
    public void updateCustomer(AuthUser authUser, User user) {
       defaultAuthUserDAO.updateAuthUserUserT(authUser, user);
    }

    @Override
    public void deleteCutomer(Long id) {
        defaultAuthUserDAO.deleteAuthUserT(id);
    }

    @Override
    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        return defaultAuthUserDAO.getListAuthUserUserDTO();
    }

    public AuthUserUserDTO getAuthUserUserDTO(String login) {
        return defaultAuthUserDAO.getAuthUserUserDTO(login);
    }
}
