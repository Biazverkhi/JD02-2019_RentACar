package by.fastrentcar.springdata;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import by.fastrentcar.springdata.config.DAOSpringConfig;
import by.fastrentcar.springdata.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DAOSpringConfig.class})

public class AuthUserJpaRepositoryTest {
    @Autowired
    AuthUserDAO dao;

    //
//    @BeforeEach
//    void beforeTest() {
//        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DAOConfig.class);
//        dao = context.getBean(AuthUserDAO.class);
//    }
//
//    @Test
//    void getInstance() {
//        assertNotNull(dao);
//    }
//
//    @Test
//    void getUserAdmin() {
//        AuthUser wertual = dao.getByLoginT("wertual");
//        assertEquals("wertual", wertual.getLogin());
//        assertEquals("assembler", wertual.getPassword());
//        assertEquals(Role.ADMIN, wertual.getRole());
//        assertNotNull(dao.getByLoginT("wertual"));
//    }
//
//    @Test
//    void getByLoginTest() {
//        assertNull(dao.getByLoginT("25"));
//    }
//
//    @Test
//    void getUsersList() {
//        assertNotNull(dao.getListAuthUserUserDTO());
//    }
//
//    @Test
//    void getAuthUserUserDTO() {
//        assertNotNull(dao.getAuthUserUserDTO("11"));
//    }
//
    @Test
    void addUpateDeleteUser() {
        AuthUser au = new AuthUser(null, "test", "test", Role.USER, null);
        User u = new User(null, "Lexa", "Bez", "5752", "wer", "279", "100910", "test");
        Long auId = dao.addAuthUserUserT(au, u);
        assertNotNull(auId);
        AuthUser auFromDb = dao.getByIdT(auId);
        auFromDb.setLogin("testupdate1");
        User u2 = new User(auFromDb.getUserId(), "testupdate1", "testupdate1", "testupdate1", "testupdate1", "testupdate1", "testupdate1", "testupdate1");
        assertNotNull(dao.updateAuthUserUserT(auFromDb, u2));
        assertEquals("testupdate1", dao.getAuthUserUserDTO("testupdate1").getFirstName());
        assertEquals("testupdate1", dao.getByIdT(auId).getLogin());
        dao.deleteAuthUserT(auId);
    }
}