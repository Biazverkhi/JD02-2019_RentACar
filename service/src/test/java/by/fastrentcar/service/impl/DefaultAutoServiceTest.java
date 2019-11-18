package by.fastrentcar.service.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.service.AutoService;
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

public class DefaultAutoServiceTest {

    @Mock
    AutoDAO dao;

    @InjectMocks
    AutoService service = DefaultAutoService.getInstance();

    @Test
    void getInstance() {
        assertNotNull(service);
    }

    @Test
    void getListAuto() {
        when(dao.getListAutoT()).thenReturn(new ArrayList<Auto>());
        List<Auto> auto = service.getListAuto();
        verify(dao).getListAutoT();
        assertNotNull(auto);
    }

    @Test
    void getAuto() {
        when(dao.getAutoByIdT(1L)).thenReturn(new Auto(1l, "11", "11", "123", "12l", 235d, "11"));
        Auto auto = service.getAuto(1l);
        assertNotNull(auto);
        assertEquals("12l", auto.getDate());
    }

    @Test
    void addCustomerTest() {
        Auto auto = new Auto(null, "11", "11", "123", "12l", 235d, "11");
        when(dao.addAutoT(auto)).thenReturn(1l);
        Long id = service.addAuto(auto);
        assertNotNull(id);
        assertEquals(id, 1l);
    }

    @Test
    void updateCustomerTest() {
        Auto auto = new Auto(1l, "11", "11", "123", "12l", 235d, "11");
        when(dao.updateAutoT(auto)).thenReturn(true);
        assertEquals(true, service.updateAuto(auto));
        verify(dao).updateAutoT(auto);
    }

    @Test
    void deleteCustomerTest() {
        when(dao.deleteAutoT(1l)).thenReturn(true);
        assertEquals(true, service.deleteAuto(1l));
        verify(dao).deleteAutoT(1l);
    }


}
