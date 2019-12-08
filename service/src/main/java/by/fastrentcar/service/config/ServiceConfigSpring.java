package by.fastrentcar.service.config;

import by.fastrentcar.service.*;
import by.fastrentcar.service.impl.*;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfigSpring {

    private DAOConfigSpring daoConfigSpring;

    public ServiceConfigSpring(DAOConfigSpring daoConfigSpring) {
        this.daoConfigSpring = daoConfigSpring;
    }

    @Bean
    public UserService getDefaultUserService() {
        return new DefaultUserService(daoConfigSpring.getDefaultAuthUserDAO());
    }

    @Bean
    public SecurityService getDefaultSecurityService() {
        return new DefaultSecurityService(daoConfigSpring.getDefaultAuthUserDAO());
    }

    @Bean
    public OrderService getDefaultOrderService() {
        return new DefaultOrderService(daoConfigSpring.getDefaultOrderDAO());
    }

    @Bean
    public BussinesLogic getDefaultBussinesLogic() {
        return new DefaultBussinesLogic();
    }

    @Bean
    public AutoServicesService getDefaultAutoServicesService() {
        return new DefaultAutoServicesService(daoConfigSpring.getDefaultAutoServicesDAO());
    }

    @Bean
    public AutoService getDefaultAutoService() {
        return new DefaultAutoService(daoConfigSpring.getDefaultAutoDAO());
    }
}
