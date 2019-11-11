package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAuthUserDAOTest {
    AuthUserDAO dao = DefaultAuthUserDAO.getInstance();

    @Test
    void getInstance() {

        assertNotNull(dao);
        assertEquals(dao, DefaultAuthUserDAO.getInstance());


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