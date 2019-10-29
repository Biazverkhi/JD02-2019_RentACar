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
    private DefaultUserService() {
    }

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    private UserDAO defaultUserDAO = DefaultUserDAO.getInstance();

    private AuthUserDAO defaultAuthUserDAO = DefaultAuthUserDAO.getInstance();

    @Override
    public Long addCustomer(AuthUser authUser, User user) {
        Long userID = defaultUserDAO.addUserT(user);
        authUser.setUserId(userID);
        return defaultAuthUserDAO.addAuthUserT(authUser);
    }

    @Override
    public void updateCustomer(AuthUser authUser, User user) {
        defaultAuthUserDAO.updateAuthUserT(authUser);
        defaultUserDAO.updateUserT(user);
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
