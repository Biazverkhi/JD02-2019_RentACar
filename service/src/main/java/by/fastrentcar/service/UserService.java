package by.fastrentcar.service;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;

import java.util.List;

public interface UserService {
    Long addCustomer(AuthUser authUser, User user);

    boolean updateCustomer(AuthUser authUser, User user);

    boolean deleteCutomer(Long id);

    List<AuthUserUserDTO> getListAuthUserUserDTO();

    AuthUserUserDTO getAuthUserUserDTO(String login);
}
