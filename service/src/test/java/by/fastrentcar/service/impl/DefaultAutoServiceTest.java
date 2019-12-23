package by.fastrentcar.service.impl;

import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.service.DTO.ChekBoxColumnAutoMenu;
import by.fastrentcar.springdata.AutoDAO;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {DAOConfigSpring.class})

public class DefaultAutoServiceTest {

    @Mock
    AutoDAO dao;
    @InjectMocks
    DefaultAutoService service;

    @Test
    void getInstance() {
        assertNotNull(service);
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
    void getListAutoTest() {
        PageAuto pageAuto = new PageAuto(10);
        when(dao.getListAutoT(pageAuto)).thenReturn(pageAuto);
        PageAuto page = service.getListAuto(pageAuto);
        verify(dao).getListAutoT(pageAuto);
        assertNotNull(page);

    }


    @Test
    void getCheckBoxColumnAutoTest() {
        ChekBoxColumnAutoMenu ch = service.getCheckBoxColumnAuto();
        assertNotNull(ch);
        verify(dao).getDistinctBrendAuto();
        verify(dao).getDistinctModelAuto();
    }


    @Test
    void getAutoServicesByAutoIdTest() {
        when(dao.getAutoServicesByAutoIdT(23l)).thenReturn(new ArrayList<AutoServices>());
        List<AutoServices> list = service.getAutoServicesByAutoIdT(23l);
        assertNotNull(list);
    }

    @Test
    void getCountAutoTest() {
        when(dao.getCountAuto()).thenReturn(51l);
        Long count = service.getCountAuto();
        assertEquals(51l, count);

    }


}
