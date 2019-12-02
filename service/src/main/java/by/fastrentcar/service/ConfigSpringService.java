package by.fastrentcar.service;

import by.fastrentcar.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DAOConfig.class)
public class ConfigSpringService {

    private DAOConfig DAOConfig;

    public ConfigSpringService(DAOConfig daoConfig) {
        this.DAOConfig = daoConfig;
    }

    @Bean
    public UserService getDefaultUserService() {
        return new DefaultUserService(DAOConfig.getDefaultAuthUserDAO());
    }

    @Bean
    public SecurityService getDefaultSecurityService() {
        return new DefaultSecurityService(DAOConfig.getDefaultAuthUserDAO());
    }

    @Bean
    public OrderService getDefaultOrderService() {
        return new DefaultOrderService(DAOConfig.getDefaultOrderDAO());
    }

    @Bean
    public BussinesLogic getDefaultBussinesLogic() {
        return new DefaultBussinesLogic();
    }

    @Bean
    public AutoServicesService getDefaultAutoServicesService() {
        return new DefaultAutoServicesService(DAOConfig.getDefaultServicesDAO());
    }

    @Bean
    public AutoService getDefaultAutoService() {
        return new DefaultAutoService(DAOConfig.getDefaultAutoDAO());
    }
}
