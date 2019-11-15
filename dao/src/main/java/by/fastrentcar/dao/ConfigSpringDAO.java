package by.fastrentcar.dao;

import by.fastrentcar.dao.impl.DefaultAuthUserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSpringDAO {

    @Bean
    public AuthUserDAO getDefaultAuthUserDAO() {
        return new DefaultAuthUserDAO();
    }


}
