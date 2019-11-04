package by.fastrentcar.service.impl;

import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.dao.impl.DefaultOrderDAO;
import by.fastrentcar.model.order.Order;
import by.fastrentcar.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DefaultOrderServiceTest {
    static LocalDateTime dateTime = LocalDateTime.of(2019, 9, 25, 14, 25, 32);

    @Mock
    OrderDAO dao = DefaultOrderDAO.getInstance();

    @InjectMocks
    OrderService service = DefaultOrderService.getInstance();

    @Test
    void getInstance() {
        assertNotNull(DefaultOrderDAO.getInstance());
        assertNotNull(DefaultOrderService.getInstance());
        assertEquals(service, DefaultOrderService.getInstance());
    }

    @Test
    void getListOrder() {
        when(dao.getListOrderT()).thenReturn(new ArrayList<Order>());
        when(dao.getListOrderByIdUserT(12l)).thenReturn(new ArrayList<Order>());
        List<Order> order = service.getListOrder();
        List<Order> order2 = service.getListOrderByIdUser(12l);
        assertNotNull(order);
        assertNotNull(order2);
    }

    @Test
    void getOrder() {
        when(dao.getOrderByIdT(1L)).thenReturn(new Order(1l, 12l, 45l, dateTime, dateTime, dateTime, "rr", "rtr", 235d));
        Order order = service.getOrder(1l);
        assertNotNull(order);
        assertEquals("rr", order.getComment());
    }

    @Test
    void addCustomerTest() {
        Order order = new Order(null, 12l, 45l, dateTime, dateTime, dateTime, "rr", "rtr", 235d);
        when(dao.addOrderT(order)).thenReturn(1l);
        Long id = service.addOrder(order);
        assertNotNull(id);
        assertEquals(id, 1l);
    }

    @Test
    void updateCustomerTest() {
        Order order = new Order(1l, 12l, 45l, dateTime, dateTime, dateTime, "rr", "rtr", 235d);
        when(dao.updateOrderT(order)).thenReturn(true);
        assertEquals(true, service.updateOrder(order));
        verify(dao).updateOrderT(order);
    }

    @Test
    void deleteCustomerTest() {
        when(dao.deleteOrderT(1l)).thenReturn(true);
        assertEquals(true, service.deleteOrder(1l));
        verify(dao).deleteOrderT(1l);
    }


}