package by.fastrentcar.springdata;

import by.fastrentcar.model.order.Order;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import by.fastrentcar.springdata.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DAOConfigSpring.class})

public class OrderJpaRepositoryTest {
    @Autowired
    OrderDAO dao;

    static Long ID;

    @Test
    void getInstance() {
        assertNotNull(dao);
    }

    @Transactional
    @Test
    void getListOrderTTest() {
        assertFalse(dao.getListOrderT().isEmpty());
        List<Order> d = dao.getListOrderT();
        for (Order order : d) {
            dao.deleteOrderT(order.getId());
        }
        assertTrue(dao.getListOrderT().isEmpty());
    }

    @Test
    void getListOrderByIdUserTTest() {
        assertFalse(dao.getListOrderByIdUserT(48l).isEmpty());
        assertTrue(dao.getListOrderByIdUserT(47l).isEmpty());
    }

    @Test
    void getOrderByIdTTest() {

        assertNotNull(dao.getOrderByIdT(14l));
        assertNull(dao.getOrderByIdT(13l));
    }

    @Test
    void addUpdateDeleteOrder() {
        Order order = new Order(null, 48l, 81l, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), LocalDateTime.of(2019, 5, 26, 12, 23, 56), LocalDateTime.of(2019, 5, 27, 12, 23, 56), "commenttt", "rrr", 235d);
        ID = dao.addOrderT(order);
//        dao.getListOrderByIdUserT(48l);
//        assertNotNull(ID);
//        Order oFromDb = dao.getOrderByIdT(ID);
//        oFromDb.setReservStatus("ddd");
//        dao.updateOrderT(oFromDb);
//        assertEquals("ddd", dao.getOrderByIdT(ID).getReservStatus());
//        dao.deleteOrderT(ID);
//        assertNull(dao.getOrderByIdT(ID));
    }


}
