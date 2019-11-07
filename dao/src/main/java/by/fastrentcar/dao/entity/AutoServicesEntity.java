package by.fastrentcar.dao.entity;

import by.fastrentcar.model.auto.AutoServices;
import by.fastrentcar.model.auto.Services;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auto_services")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class AutoServicesEntity {
    private Long id;
    private Services services;
    private List<AutoEntity> autoEntity = new ArrayList<>();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auto_autoservices",
            joinColumns = {@JoinColumn(name = "autoservices_id")},
            inverseJoinColumns = {@JoinColumn(name = "auto_id")})
    public List<AutoEntity> getAutoEntity() {
        return autoEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public void setAutoEntity(List<AutoEntity> autoEntity) {
        this.autoEntity = autoEntity;
    }

    public AutoServicesEntity(AutoServices autoServices) {
        this.id = autoServices.getId();
        this.services = autoServices.getServices();
    }

    public AutoServicesEntity() {
    }

    public AutoServices convertAutoServicesByAutoServicesEntity() {
        return new AutoServices(
                this.id,
                this.services
        );
    }

}
