package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.model.auto.Auto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultAutoDAOTest {
    static Long ID;
    AutoDAO dao = DefaultAutoDAO.getInstance();

    @Test
    void getInstance() {
        assertNotNull(dao);
        assertEquals(dao, DefaultAutoDAO.getInstance());
    }

    @Test
    void getAutoByIdTest() {
        assertNotNull(dao.getAutoByIdT(675l));
        assertEquals("BMV", dao.getAutoByIdT(675l).getBrand());


    }

    @Test
    void getAutoListTest() {
        assertNotNull(dao.getListAutoT());
        assertEquals(false, dao.getListAutoT().isEmpty());
    }


    @Test
    void addUpdateDeleteAuto() {
        Auto auto = new Auto(null, "11", "22", "33", "44", 245d, "ww");
        ID = dao.addAutoT(auto);
        assertNotNull(ID);
        Auto auto2 = new Auto(ID, "11test", "22test", "33test", "44test", 245d, "wwtest");
        assertEquals(true, dao.updateAutoT(auto2));
        assertEquals(true, dao.deleteAutoT(ID));
    }

}