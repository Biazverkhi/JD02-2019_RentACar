package by.fastrentcar.service.impl;

import by.fastrentcar.model.order.OrderDTO;
import by.fastrentcar.service.BussinesLogic;

import java.time.Duration;
import java.time.LocalDateTime;

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
