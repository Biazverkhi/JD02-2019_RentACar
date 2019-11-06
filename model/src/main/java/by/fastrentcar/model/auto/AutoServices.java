package by.fastrentcar.model.auto;

public class AutoServices {
    private Long id;
    Services services;

    public AutoServices(Long id, Services services) {
        this.id = id;
        this.services = services;
    }

    public AutoServices() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }


}
