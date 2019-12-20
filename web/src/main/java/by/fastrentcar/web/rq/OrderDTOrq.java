package by.fastrentcar.web.rq;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement
public class OrderDTOrq {
    private LocalDateTime startOrderDate;
    private LocalDateTime stopOrderDate;
    private Double price;
    private Long autoId;

    public OrderDTOrq(LocalDateTime startOrderDate, LocalDateTime stopOrderDate, Double price, Long autoId) {
        this.startOrderDate = startOrderDate;
        this.stopOrderDate = stopOrderDate;
        this.price = price;
        this.autoId = autoId;
    }

    public OrderDTOrq() {
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
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
