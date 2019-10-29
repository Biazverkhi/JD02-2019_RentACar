package by.fastrentcar.service;

import by.fastrentcar.model.AuthUser;

public interface SecurityService {
    AuthUser login(String login, String password);
}
