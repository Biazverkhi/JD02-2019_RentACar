package by.fastrentcar.service.impl;

import by.fastrentcar.model.order.OrderDTO;
import by.fastrentcar.service.BussinesLogic;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Transactional
public class DefaultBussinesLogic implements BussinesLogic {
    public DefaultBussinesLogic() {
    }


    @Override
    public Double getPriceArend(OrderDTO orderDTO) {
        LocalDateTime startDate =orderDTO.getStartOrderDate();
        LocalDateTime stopDate = orderDTO.getStopOrderDate();
        Integer period = (int) Duration.between(startDate, stopDate).toDays();
        Double priceArend = period * orderDTO.getPrice();
        return priceArend;
    }
}
