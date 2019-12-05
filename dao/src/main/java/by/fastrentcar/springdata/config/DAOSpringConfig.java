package by.fastrentcar.springdata.config;

import by.fastrentcar.springdata.AuthUserDAO;
import by.fastrentcar.springdata.AutoDAO;
import by.fastrentcar.springdata.AutoServicesDAO;
import by.fastrentcar.springdata.OrderDAO;
import by.fastrentcar.springdata.impl.DefaultAuthUserDAO;
import by.fastrentcar.springdata.impl.DefaultAutoDAO;
import by.fastrentcar.springdata.impl.DefaultAutoServicesDAO;
import by.fastrentcar.springdata.impl.DefaultOrderDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOSpringConfig {


    @Bean
    public AutoDAO getDefaultAutoDAO() {
        return new DefaultAutoDAO();
    }

    @Bean
    public AuthUserDAO getDefaultAuthUserDAO() {
        return new DefaultAuthUserDAO();
    }

    @Bean
    public AutoServicesDAO getDefaultAutoServicesDAO() {
        return new DefaultAutoServicesDAO();
    }

    @Bean
    public OrderDAO getDefaultOrderDAO() {
        return new DefaultOrderDAO();
    }


}
