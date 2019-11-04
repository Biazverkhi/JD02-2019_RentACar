package by.fastrentcar.dao.entity;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long authuserId;
    private Long autoId;
    private LocalDateTime createOrderDate;
    private LocalDateTime startOrderDate;
    private LocalDateTime stopOrderDate;
    private String comment;
    private Double priceArend;
    private String reservStatus;

    public Order(Long id, Long authuserId, Long autoId, LocalDateTime createOrderDate, LocalDateTime startOrderDate, LocalDateTime stopOrderDate, String comment, String reservStatus, Double priceArend) {
        this.id = id;
        this.authuserId = authuserId;
        this.autoId = autoId;
        this.createOrderDate = createOrderDate;
        this.startOrderDate = startOrderDate;
        this.stopOrderDate = stopOrderDate;
        this.comment = comment;
        this.reservStatus = reservStatus;
        this.priceArend = priceArend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthuserId() {
        return authuserId;
    }

    public void setAuthuserId(Long authuserId) {
        this.authuserId = authuserId;
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public LocalDateTime getCreateOrderDate() {
        return createOrderDate;
    }

    public void setCreateOrderDate(LocalDateTime createOrderDate) {
        this.createOrderDate = createOrderDate;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReservStatus() {
        return reservStatus;
    }

    public void setReservStatus(String reservStatus) {
        this.reservStatus = reservStatus;
    }

    public Double getPriceArend() {
        return priceArend;
    }

    public void setPriceArend(Double priceArend) {
        this.priceArend = priceArend;
    }
}
