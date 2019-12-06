package by.fastrentcar.springdata;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import by.fastrentcar.springdata.config.DAOSpringConfig;
import by.fastrentcar.springdata.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DAOSpringConfig.class})

public class AuthUserJpaRepositoryTest {
    @Autowired
    AuthUserDAO dao;

    @Test
    void getInstance() {
        assertNotNull(dao);
    }

    @Test
    void getByLoginTTest() {
        assertNotNull(dao.getByLoginT("wertual"));
        assertNull(dao.getByLoginT("25"));
    }

    @Test
    void getByIdTTest() {
        assertNotNull(dao.getByIdT(41l));
        assertNull(dao.getByIdT(25l));
    }

    @Transactional
    @Test
    void getListAuthUserUserDTOTest() {
        List<AuthUserUserDTO> list = dao.getListAuthUserUserDTO();
        assertFalse(list.isEmpty());
        for (AuthUserUserDTO a : list
        ) {
            dao.deleteAuthUserT(a.getid());
        }
        assertTrue(dao.getListAuthUserUserDTO().isEmpty());
    }

    @Test
    void getAuthUserUserDTO() {
        assertNotNull(dao.getAuthUserUserDTO("wertual"));
        assertNull(dao.getAuthUserUserDTO("w"));
    }

    @Test
    void addUpateDeleteUser() {
        AuthUser au = new AuthUser(null, "test", "test", Role.USER, null);
        User u = new User(null, "Lexa", "Bez", "5752", "wer", "279", "100910", "test");
        Long auId = dao.addAuthUserUserT(au, u);
        assertNotNull(auId);
        AuthUser auFromDb = dao.getByIdT(auId);
        auFromDb.setLogin("testupdate1");
        User u2 = new User(auFromDb.getUserId(), "testupdate1", "testupdate1", "testupdate1", "testupdate1", "testupdate1", "testupdate1", "testupdate1");
        dao.updateAuthUserUserT(auFromDb, u2);
        assertEquals("testupdate1", dao.getAuthUserUserDTO("testupdate1").getFirstName());
        assertEquals("testupdate1", dao.getByIdT(auId).getLogin());
        dao.deleteAuthUserT(auId);
        assertNull(dao.getByIdT(auId));
    }
}