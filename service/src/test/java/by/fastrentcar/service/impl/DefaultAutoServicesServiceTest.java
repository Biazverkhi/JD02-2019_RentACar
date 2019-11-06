package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AutoServicesDAO;
import by.fastrentcar.dao.impl.DefaultAutoServicesDAO;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.auto.Services;
import by.fastrentcar.service.AutoServicesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DefaultAutoServicesServiceTest {
    @Mock
    AutoServicesDAO dao = DefaultAutoServicesDAO.getInstance();

    @InjectMocks
    AutoServicesService service = DefaultAutoServicesService.getInstance();

    @Test
    void getInstance() {
        assertNotNull(DefaultAutoServicesDAO.getInstance());
        assertNotNull(DefaultAutoServicesService.getInstance());
        assertEquals(service, DefaultAutoServicesService.getInstance());
    }

    @Test
    void addAutoServicesToAutoTest() {
        when(dao.addAutoServicesToAuto(1l, 2l)).thenReturn(true);
        assertEquals(true, service.addAutoServicesToAuto(1l, 2l));
        verify(dao).addAutoServicesToAuto(1l, 2l);
    }

    @Test
    void getListAutoServicesTest() {
        when(dao.getListAutoServicesT()).thenReturn(new ArrayList<AutoServices>());
        List<AutoServices> autoServices = service.getListAutoServicesT();
        assertNotNull(autoServices);
    }

    @Test
    void getAutoServices() {
        when(dao.getAutoServicesByIdT(1L)).thenReturn(new AutoServices(1L, Services.ELECTRICIAN));
        AutoServices autoServices = service.getAutoServicesByIdT(1l);
        assertNotNull(autoServices);
        assertEquals(Services.ELECTRICIAN, autoServices.getServices());
    }

    @Test
    void addAutoServicesTest() {
        AutoServices autoServices = new AutoServices(null, Services.ELECTRICIAN);
        when(dao.addAutoServicesT(autoServices)).thenReturn(1l);
        Long id = service.addAutoServicesT(autoServices);
        assertNotNull(id);
        assertEquals(id, 1l);
    }

    @Test
    void updateAutoServicesTest() {
        AutoServices autoServices = new AutoServices(null, Services.ELECTRICIAN);
        when(dao.updateAutoServicesT(autoServices)).thenReturn(true);
        assertEquals(true, service.updateAutoServicesT(autoServices));
        verify(dao).updateAutoServicesT(autoServices);
    }

    @Test
    void deleteAutoServicesTest() {
        when(dao.deleteAutoServicesT(1l)).thenReturn(true);
        assertEquals(true, service.deleteAutoServicesT(1l));
        verify(dao).deleteAutoServicesT(1l);
    }


}
