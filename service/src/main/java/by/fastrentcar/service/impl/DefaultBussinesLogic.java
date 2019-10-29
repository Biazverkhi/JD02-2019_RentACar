package by.fastrentcar.service.impl;

import by.fastrentcar.model.OrderDTO;
import by.fastrentcar.service.BussinesLogic;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class DefaultBussinesLogic implements BussinesLogic {
    private DefaultBussinesLogic() {
    }

    private static class SingletonHolder {
        static final BussinesLogic HOLDER_INSTANCE = new DefaultBussinesLogic();
    }

    public static BussinesLogic getInstance() {
        return DefaultBussinesLogic.SingletonHolder.HOLDER_INSTANCE;
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
