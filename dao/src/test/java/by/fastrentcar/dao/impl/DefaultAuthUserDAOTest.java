package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultAuthUserDAOTest {
    AuthUserDAO dao = DefaultAuthUserDAO.getInstance();

    @Test
    void getInstance() {

        assertNotNull(dao);
        assertEquals(dao, DefaultAuthUserDAO.getInstance());


    }

    @Test
    void getUserAdmin() {
        assertEquals("wertual", dao.getByLoginT("wertual").getLogin());
        assertEquals("assembler", dao.getByLoginT("wertual").getPassword());
        assertEquals("ADMIN", dao.getByLoginT("wertual").getRole().name());
        assertNotNull(dao.getByLoginT("wertual"));

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