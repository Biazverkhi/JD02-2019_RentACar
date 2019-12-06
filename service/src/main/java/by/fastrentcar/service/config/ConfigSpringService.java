package by.fastrentcar.service.config;

import by.fastrentcar.service.*;
import by.fastrentcar.service.impl.*;
import by.fastrentcar.springdata.config.DAOSpringConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSpringService {

    private DAOSpringConfig daoSpringConfig;

    public ConfigSpringService(DAOSpringConfig daoSpringConfig) {
        this.daoSpringConfig = daoSpringConfig;
    }

    @Bean
    public UserService getDefaultUserService() {
        return new DefaultUserService(daoSpringConfig.getDefaultAuthUserDAO());
    }

    @Bean
    public SecurityService getDefaultSecurityService() {
        return new DefaultSecurityService(daoSpringConfig.getDefaultAuthUserDAO());
    }

    @Bean
    public OrderService getDefaultOrderService() {
        return new DefaultOrderService(daoSpringConfig.getDefaultOrderDAO());
    }

    @Bean
    public BussinesLogic getDefaultBussinesLogic() {
        return new DefaultBussinesLogic();
    }

    @Bean
    public AutoServicesService getDefaultAutoServicesService() {
        return new DefaultAutoServicesService(daoSpringConfig.getDefaultAutoServicesDAO());
    }

    @Bean
    public AutoService getDefaultAutoService() {
        return new DefaultAutoService(daoSpringConfig.getDefaultAutoDAO());
    }
}
