package by.fastrentcar.dao.entity;

import by.fastrentcar.model.auto.Auto;

import javax.persistence.*;

@Entity
@Table(name = "auto")
public class AutoEntity {
    private Long id;
    private String brand;
    private String model;
    private String fuel;
    private String date;
    private Double price;
    private String status;

    public AutoEntity(Auto auto) {
        this.id = auto.getId();
        this.brand = auto.getBrand();
        this.model = auto.getModel();
        this.fuel = auto.getFuel();
        this.date = auto.getDate();
        this.price = auto.getPrice();
        this.status = auto.getStatus();
    }

    public AutoEntity() {

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Auto convertAutoByAutoEntity() {
        return new Auto(
                this.id,
                this.brand,
                this.model,
                this.fuel,
                this.date,
                this.price,
                this.status);

    }

}

