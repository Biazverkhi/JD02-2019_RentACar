package by.fastrentcar.service;

import by.fastrentcar.model.user.AuthUser;

public interface SecurityService {
    AuthUser login(String login, String password);
}
