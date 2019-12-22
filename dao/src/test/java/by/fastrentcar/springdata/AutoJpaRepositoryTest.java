package by.fastrentcar.springdata;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.springdata.config.DAOConfigSpring;
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
@ContextConfiguration(classes = {HibernateConfig.class, DAOConfigSpring.class})

public class AutoJpaRepositoryTest {
    @Autowired
    AutoDAO dao;

    static Long ID;

    @Test
    void getInstance() {
        assertNotNull(dao);
    }

    @Transactional
    @Test
    void getCountAuto() {
        assertTrue(dao.getCountAuto() > 0);
        List<Auto> d = dao.getListAutoT();
        for (Auto auto : d) {
            dao.deleteAutoT(auto.getId());
        }
        assertEquals(0, dao.getCountAuto());
    }

    @Transactional
    @Test
    void getAutoListTest() {
        assertFalse(dao.getListAutoT().isEmpty());
        List<Auto> d = dao.getListAutoT();
        for (Auto auto : d) {
            dao.deleteAutoT(auto.getId());
        }
        assertTrue(dao.getListAutoT().isEmpty());

    }

    @Transactional
    @Test
    void getAutoListTest2() {
        PageAuto list0 = dao.getListAutoT(new PageAuto());
        assertEquals(10, dao.getListAutoT(new PageAuto()).getSize());
        List<Auto> d = dao.getListAutoT();
        for (Auto auto : d) {
            dao.deleteAutoT(auto.getId());
        }
        assertTrue(dao.getListAutoT(new PageAuto()).getAutoList().isEmpty());
    }

    @Test
    void getAutoByIdTTest() {
        assertNull(dao.getAutoByIdT(1l));
        assertNotNull(dao.getAutoByIdT(85l));
    }

    @Test
    void getDistinctBrendAuto() {
        assertFalse(dao.getDistinctBrendAuto().isEmpty());

    }

    @Test
    void getDistinctModelAuto() {
        assertFalse(dao.getDistinctModelAuto().isEmpty());

    }

    @Test
    void getAutoServicesByAutoIdTTest() {
        assertTrue(dao.getAutoServicesByAutoIdT(85l).isEmpty());
        assertFalse(dao.getAutoServicesByAutoIdT(120l).isEmpty());
        assertEquals(0, dao.getAutoServicesByAutoIdT(85l).size());
        assertEquals(1, dao.getAutoServicesByAutoIdT(120l).size());

    }

    @Test
    void addUpdateDeleteAutoTest() {
        Auto auto = new Auto(null, "1123", "22", "33", "44", 245d, "ww");
        ID = dao.addAutoT(auto);
        assertNotNull(ID);
        Auto aFromDb = dao.getAutoByIdT(ID);
        aFromDb.setBrand("TEST");
        dao.updateAutoT(aFromDb);
        assertEquals("TEST", dao.getAutoByIdT(ID).getBrand());
        dao.deleteAutoT(ID);
        assertNull(dao.getAutoByIdT(ID));
    }

}
