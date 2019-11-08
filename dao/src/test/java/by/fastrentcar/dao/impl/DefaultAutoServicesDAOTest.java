package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoServicesDAO;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.auto.Services;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultAutoServicesDAOTest {
    static Long ID;
    AutoServicesDAO dao = DefaultAutoServicesDAO.getInstance();

    @Test
    void getInstance() {
        assertNotNull(dao);
        assertEquals(dao, DefaultAutoServicesDAO.getInstance());
    }

    @Test
    void getAutoServicesByIdTest() {
        assertNotNull(dao.getAutoServicesByIdT(23l));
        assertEquals(Services.MECHANIC, dao.getAutoServicesByIdT(23l).getServices());
    }

    @Test
    void addAutoServicesToAutoTest() {
        assertEquals(true, dao.addAutoServicesToAuto(5841l, 24l));
        assertEquals(false, dao.addAutoServicesToAuto(676l, 24l));


    }


    @Test
    void getAutoServicesListTest() {
        assertNotNull(dao.getListAutoServicesT());
        assertEquals(false, dao.getListAutoServicesT().isEmpty());
    }


    @Test
    void addUpdateDeleteAuto() {
        AutoServices autoServices = new AutoServices(null, Services.ELECTRICIAN);
        ID = dao.addAutoServicesT(autoServices);
        assertNotNull(ID);
        AutoServices autoServices2 = new AutoServices(ID, Services.MECHANIC);
        assertEquals(true, dao.updateAutoServicesT(autoServices2));
        assertEquals(true, dao.deleteAutoServicesT(ID));
    }

}
