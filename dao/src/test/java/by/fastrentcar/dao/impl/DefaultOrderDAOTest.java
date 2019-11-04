package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.model.order.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultOrderDAOTest {
    static Long ID;
    OrderDAO dao = DefaultOrderDAO.getInstance();

    @Test
    void getInstance() {
        assertNotNull(dao);
        assertEquals(dao, DefaultOrderDAO.getInstance());
    }

    @Test
    void getOrderByIdTest() {
        assertNotNull(dao.getOrderByIdT(1l));
        assertEquals("открыт", dao.getOrderByIdT(1l).getReservStatus());
        assertNotNull(dao.getListOrderByIdUserT(1l));


    }

    @Test
    void getOrderListTest() {
        assertNotNull(dao.getListOrderT());
        assertEquals(false, dao.getListOrderT().isEmpty());
    }


    @Test
    void addUpdateDeleteOrder() {
        Order order = new Order(null, 24l, 648l, LocalDateTime.now(), LocalDateTime.of(2019, 5, 26, 12, 23, 56), LocalDateTime.of(2019, 5, 27, 12, 23, 56), "comment", "rrr", 235d);
        ID = dao.addOrderT(order);
        assertNotNull(ID);
        Order order2 = new Order(ID, 24l, 645l, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "comment", "rrr", 237d);
        assertEquals(true, dao.updateOrderT(order2));
        assertEquals(true, dao.deleteOrderT(ID));


    }


}