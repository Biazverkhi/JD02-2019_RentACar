package by.fastrentcar.dao.entity;

import by.fastrentcar.model.order.Order;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {
    private Long id;
    private Long authuserId;
    private Long autoId;
    private LocalDateTime createOrderDate;
    private LocalDateTime startOrderDate;
    private LocalDateTime stopOrderDate;
    private String comment;
    private Double priceArend;
    private String reservStatus;
    private AuthUserEntity authUserEntity;

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.authuserId = order.getAuthuserId();
        this.autoId = order.getAutoId();
        this.createOrderDate = order.getCreateOrderDate();
        this.startOrderDate = order.getStartOrderDate();
        this.stopOrderDate = order.getStopOrderDate();
        this.comment = order.getComment();
        this.reservStatus = order.getReservStatus();
        this.priceArend = order.getPriceArend();
    }

    public OrderEntity() {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "authuser_id", insertable = false, updatable = false)
    public Long getAuthuserId() {
        return authuserId;
    }

    public void setAuthuserId(Long authuserId) {
        this.authuserId = authuserId;
    }

    @Column(name = "auto_id")

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    @Column(name = "create_order_date")

    public LocalDateTime getCreateOrderDate() {
        return createOrderDate;
    }

    public void setCreateOrderDate(LocalDateTime createOrderDate) {
        this.createOrderDate = createOrderDate;
    }

    @Column(name = "start_order_date")

    public LocalDateTime getStartOrderDate() {
        return startOrderDate;
    }

    public void setStartOrderDate(LocalDateTime startOrderDate) {
        this.startOrderDate = startOrderDate;
    }

    @Column(name = "stop_order_date")

    public LocalDateTime getStopOrderDate() {
        return stopOrderDate;
    }

    public void setStopOrderDate(LocalDateTime stopOrderDate) {
        this.stopOrderDate = stopOrderDate;
    }

    @Column(name = "commentary")

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "reserv_status")

    public String getReservStatus() {
        return reservStatus;
    }

    public void setReservStatus(String reservStatus) {
        this.reservStatus = reservStatus;
    }

    @Column(name = "price_arend")

    public Double getPriceArend() {
        return priceArend;
    }

    public void setPriceArend(Double priceArend) {
        this.priceArend = priceArend;
    }

    @ManyToOne
    @JoinColumn(name = "authuser_id", referencedColumnName = "id")
    public AuthUserEntity getAuthUserEntity() {
        return authUserEntity;
    }

    public void setAuthUserEntity(AuthUserEntity authUserEntity) {
        this.authUserEntity = authUserEntity;
    }


    public Order convertOrderByOrderEntity() {
        return new Order(
                this.id,
                this.authuserId,
                this.autoId,
                this.createOrderDate,
                this.startOrderDate,
                this.stopOrderDate,
                this.comment,
                this.reservStatus,
                this.priceArend

        );


    }
}
