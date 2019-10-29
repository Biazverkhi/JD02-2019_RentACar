package by.fastrentcar.model;

import java.time.LocalDateTime;

public class OrderDTO {
    private LocalDateTime startOrderDate;
    private LocalDateTime stopOrderDate;
    private Double price;

    public OrderDTO(LocalDateTime startOrderDate, LocalDateTime stopOrderDate, Double price) {
        this.startOrderDate = startOrderDate;
        this.stopOrderDate = stopOrderDate;
        this.price = price;
    }

    public LocalDateTime getStartOrderDate() {
        return startOrderDate;
    }

    public void setStartOrderDate(LocalDateTime startOrderDate) {
        this.startOrderDate = startOrderDate;
    }

    public LocalDateTime getStopOrderDate() {
        return stopOrderDate;
    }

    public void setStopOrderDate(LocalDateTime stopOrderDate) {
        this.stopOrderDate = stopOrderDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
