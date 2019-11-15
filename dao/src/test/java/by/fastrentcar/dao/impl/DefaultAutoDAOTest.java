package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.model.auto.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAutoDAOTest {
    static Long ID;
    final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-by-AutoDao.xml");
    final AutoDAO dao = context.getBean(AutoDAO.class);

    @Test
    void getInstance() {
        assertNotNull(dao);
    }


    @Test
    void getAutoListTest() {
        assertNotNull(dao.getListAutoT());
        assertEquals(false, dao.getListAutoT().isEmpty());
    }

    @Test
    void getAutoListTest2() {
        assertNotNull(dao.getListAutoT(0, 10));
    }


    @Test
    void getCountAuto() {
        assertNotNull(dao.getCountAuto());


    }

    @Test
    void addUpdateDeleteAuto() {
        Auto auto = new Auto(null, "1123", "22", "33", "44", 245d, "ww");
        ID = dao.addAutoT(auto);
        assertNotNull(dao.getAutoByIdT(ID));

        assertNotNull(ID);
        Auto auto2 = new Auto(ID, "11113test", "22test", "33test", "44test", 245d, "wwtest");
        assertTrue(dao.updateAutoT(auto2));
        assertEquals(true, dao.deleteAutoT(ID));
    }

}
