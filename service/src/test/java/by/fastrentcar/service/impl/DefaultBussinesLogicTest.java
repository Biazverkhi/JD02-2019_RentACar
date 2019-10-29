package by.fastrentcar.service.impl;

import by.fastrentcar.model.OrderDTO;
import by.fastrentcar.service.BussinesLogic;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DefaultBussinesLogicTest {
    BussinesLogic log=DefaultBussinesLogic.getInstance();
    @Test
    void getPrice(){
        Double pr=log.getPriceArend(new OrderDTO(LocalDateTime.of(2019,9,25,14,25,32),LocalDateTime.of(2019,9,14,23,32,35),355.0));
        assertNotNull(pr);
    }

}
