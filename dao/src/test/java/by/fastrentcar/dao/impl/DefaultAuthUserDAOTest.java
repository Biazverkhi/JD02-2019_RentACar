package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.ConfigSpringDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAuthUserDAOTest {
    private static AuthUserDAO dao;

    @BeforeEach
    void beforeTest() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpringDAO.class);
        dao = context.getBean(AuthUserDAO.class);
    }

    @Test
    void getInstance() {
        assertNotNull(dao);
    }

    @Test
    void getUserAdmin() {
        AuthUser wertual = dao.getByLoginT("wertual");
        assertEquals("wertual", wertual.getLogin());
        assertEquals("assembler", wertual.getPassword());
        assertEquals(Role.ADMIN, wertual.getRole());
        assertNotNull(dao.getByLoginT("wertual"));
    }

    @Test
    void getByLoginTest() {
        assertNull(dao.getByLoginT("25"));
    }

    @Test
    void getUsersList() {
        assertNotNull(dao.getListAuthUserUserDTO());
    }

    @Test
    void getAuthUserUserDTO() {
        assertNotNull(dao.getAuthUserUserDTO("11"));
    }

    @Test
    void addUpateDeleteUser() {
        AuthUser au = new AuthUser(null, "test", "test", Role.USER, null);
        User u = new User(null, "Lexa", "ttt", "ttt", "ttt", "tttt", "ttt", "ttttt");
        assertNotNull(dao.addAuthUserUserT(au, u));
        AuthUser au2 = new AuthUser(dao.getAuthUserUserDTO("test").getid(), "testupdate", "testupdate", Role.USER, dao.getAuthUserUserDTO("test").getUserId());
        User u2 = new User(dao.getAuthUserUserDTO("test").getUserId(), "Lexa", "ttt", "ttt", "ttt", "tttt", "ttt", "ttttt");
        assertNotNull(dao.updateAuthUserUserT(au2, u2));
        assertEquals("testupdate", dao.getAuthUserUserDTO("testupdate").getLogin());
        dao.deleteAuthUserT(dao.getByLoginT("testupdate").getId());
    }
}