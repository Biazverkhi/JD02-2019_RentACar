package by.fastrentcar.service.impl;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.service.SecurityService;
import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.springdata.AuthUserDAO;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import by.fastrentcar.springdata.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {HibernateConfig.class, ServiceConfigSpring.class, DAOConfigSpring.class})

public class DefaultSecurityServiceTest {

    @Mock
    AuthUserDAO dao;
    @Autowired
    @InjectMocks
    SecurityService service;
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