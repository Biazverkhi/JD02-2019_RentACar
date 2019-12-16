package by.fastrentcar.service.impl;

import by.fastrentcar.model.order.OrderDTO;
import by.fastrentcar.service.BussinesLogic;
import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {ServiceConfigSpring.class, DAOConfigSpring.class})

public class DefaultBussinesLogicTest {
    @Autowired
    BussinesLogic log;

    @Test
    void getPrice() {
        Double pr = log.getPriceArend(new OrderDTO(LocalDateTime.of(2019, 9, 25, 14, 25, 32), LocalDateTime.of(2019, 9, 14, 23, 32, 35), 355.0));
        assertNotNull(pr);
    }

}
