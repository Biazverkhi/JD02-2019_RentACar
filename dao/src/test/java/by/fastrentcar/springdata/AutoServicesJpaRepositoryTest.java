package by.fastrentcar.springdata;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.auto.Services;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import by.fastrentcar.springdata.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DAOConfigSpring.class})

public class AutoServicesJpaRepositoryTest {
    static Long ID;
    @Autowired
    AutoServicesDAO dao;
    @Autowired
    AutoDAO adao;

    @Test
    void getAutoServicesByIdTTest() {
        assertNotNull(dao.getAutoServicesByIdT(9l));
        assertNull(dao.getAutoServicesByIdT(1l));
    }

    @Transactional
    @Test
    void addAutoServicesToAutoTest() {
        dao.addAutoServicesToAuto(119l, 11l);
        List<AutoServices> a = adao.getAutoServicesByAutoIdT(119l).stream().filter(s -> s.getId().equals(11l)).collect(Collectors.toList());
        assertFalse(a.isEmpty());
    }

    @Transactional

    @Test
    void getListAutoServicesTTest() {
        assertFalse(dao.getListAutoServicesT().isEmpty());
        List<AutoServices> autoServicesList = dao.getListAutoServicesT();
        for (AutoServices as : autoServicesList
        ) {
            dao.deleteAutoServicesT(as.getId());
        }
        assertTrue(dao.getListAutoServicesT().isEmpty());
    }


    @Test
    void addUpdateDeleteAutoTest() {
        AutoServices autoServices = new AutoServices(null, Services.ELECTRICIAN);
        ID = dao.addAutoServicesT(autoServices);
        assertNotNull(ID);
        AutoServices aFromDb = dao.getAutoServicesByIdT(ID);
        aFromDb.setServices(Services.MECHANIC);
        dao.updateAutoServicesT(aFromDb);
        assertEquals(Services.MECHANIC, dao.getAutoServicesByIdT(ID).getServices());
        dao.deleteAutoServicesT(ID);
        assertNull(dao.getAutoServicesByIdT(ID));
    }

}
