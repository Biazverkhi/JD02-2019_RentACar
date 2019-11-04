package by.fastrentcar.service;

import by.fastrentcar.model.user.AuthUserDTO;

public interface SecurityService {
    AuthUserDTO login(String login, String password);
}
