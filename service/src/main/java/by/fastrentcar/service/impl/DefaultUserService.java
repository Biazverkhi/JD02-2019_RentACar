package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.impl.DefaultAuthUserDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;
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

    private AuthUserDAO defaultAuthUserDAO = DefaultAuthUserDAO.getInstance();

    @Override
    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        return defaultAuthUserDAO.getListAuthUserUserDTO();
    }

    public AuthUserUserDTO getAuthUserUserDTO(String login) {
        return defaultAuthUserDAO.getAuthUserUserDTO(login);
    }

    @Override
    public Long addCustomer(AuthUser authUser, User user) {
        return defaultAuthUserDAO.addAuthUserUserT(authUser, user);
    }

    @Override
    public boolean updateCustomer(AuthUser authUser, User user) {
        return defaultAuthUserDAO.updateAuthUserUserT(authUser, user);
    }

    @Override
    public boolean deleteCutomer(Long id) {
        return defaultAuthUserDAO.deleteAuthUserT(id);
    }


}
