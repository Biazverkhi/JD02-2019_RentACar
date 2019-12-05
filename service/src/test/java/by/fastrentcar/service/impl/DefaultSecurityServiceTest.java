package by.fastrentcar.service.impl;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.service.SecurityService;
import by.fastrentcar.springdata.AuthUserDAO;
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
    SecurityService service=DefaultSecurityService.getInstance();
    @Test
    void testLoginCorrect() {
        when(dao.getByLoginT("admin")).thenReturn(new AuthUser(null, "admin", "pass", Role.USER, null));
        AuthUserDTO user = service.login("admin", "pass");
        assertNotNull(user);
        assertEquals(user.getLogin(),"admin");
        assertNotNull(user.getRole());
    }

    @Test
    void testLoginWrongPass() {
        when(dao.getByLoginT("admin")).thenReturn(new AuthUser(null, "admin", "pass", null, null));
        AuthUserDTO login = service.login("admin", "pass2");
    assertNull(login);
}
    @Test
    void testLoginNotExist() {
        when(dao.getByLoginT("admin")).thenReturn(null);
        AuthUserDTO login = service.login("admin", "pass");
        assertNull(login);
    }
}