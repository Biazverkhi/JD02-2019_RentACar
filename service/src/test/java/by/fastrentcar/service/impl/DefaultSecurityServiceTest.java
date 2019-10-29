package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.AuthUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DefaultSecurityServiceTest {

    @Mock
    AuthUserDAO dao;

    @InjectMocks
    DefaultSecurityService service;
    @Test
    void testLoginCorrect() {
        when(dao.getByLoginT("admin")).thenReturn(new AuthUser(null, "admin", "pass", null, null));
        AuthUser user = service.login("admin", "pass");
        assertNotNull(user);
        assertEquals(user.getLogin(),"admin");
        assertNotNull(user.getPassword(),"pass");
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getByLoginT("admin")).thenReturn(new AuthUser(null, "admin", "pass", null, null));
    AuthUser login = service.login("admin", "pass2");
    assertNull(login);
}
    @Test
    void testLoginNotExist() {
        when(dao.getByLoginT("admin")).thenReturn(null);
        AuthUser login = service.login("admin", "admin");
        assertNull(login);
    }
}