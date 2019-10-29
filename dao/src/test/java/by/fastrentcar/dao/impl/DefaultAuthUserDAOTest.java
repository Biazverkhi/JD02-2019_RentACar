package by.fastrentcar.dao.impl;

        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultAuthUserDAOTest {
    @Test
    void getUserAdmin(){
        assertEquals("wertual", DefaultAuthUserDAO.getInstance().getByLoginT("wertual").getLogin());
        assertEquals("assembler", DefaultAuthUserDAO.getInstance().getByLoginT("wertual").getPassword());
        assertEquals("ADMIN", DefaultAuthUserDAO.getInstance().getByLoginT("wertual").getRole().name());
        assertNotNull(DefaultAuthUserDAO.getInstance().getByLoginT("wertual"));
        assertNotNull(DefaultAuthUserDAO.getInstance().getByIdT(25l));
    }

    @Test
    void getUsersList(){
        assertNotNull(DefaultAuthUserDAO.getInstance().getListAuthUserUserDTO());
    }

    @Test
    void getUser(){
        String login = DefaultAuthUserDAO.getInstance().getByLoginT("11").getLogin();

        assertEquals("11", login);


    }
}
