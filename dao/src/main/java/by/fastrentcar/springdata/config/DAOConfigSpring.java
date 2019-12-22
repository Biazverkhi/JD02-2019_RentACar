package by.fastrentcar.springdata.config;

import by.fastrentcar.model.page.PageAuto;
import by.fastrentcar.springdata.AuthUserDAO;
import by.fastrentcar.springdata.AutoDAO;
import by.fastrentcar.springdata.AutoServicesDAO;
import by.fastrentcar.springdata.OrderDAO;
import by.fastrentcar.springdata.impl.DefaultAuthUserDAO;
import by.fastrentcar.springdata.impl.DefaultAutoDAO;
import by.fastrentcar.springdata.impl.DefaultAutoServicesDAO;
import by.fastrentcar.springdata.impl.DefaultOrderDAO;
import by.fastrentcar.springdata.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"by.fastrentcar.springdata.repository"})
@PropertySource("classpath:page.properties")

public class DAOConfigSpring {

    @Autowired
    private AuthUserJpaRepository authUserJpaRepository;
    @Autowired
    private AutoJpaRepository autoJpaRepository;
    @Autowired
    private AutoServicesJpaRepository autoServicesJpaRepository;
    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Bean
    public AutoDAO getDefaultAutoDAO() {
        return new DefaultAutoDAO(autoJpaRepository);
    }

    @Bean
    public AuthUserDAO getDefaultAuthUserDAO() {
        return new DefaultAuthUserDAO(authUserJpaRepository, userJpaRepository);
    }

    @Bean
    public AutoServicesDAO getDefaultAutoServicesDAO() {
        return new DefaultAutoServicesDAO(autoServicesJpaRepository, autoJpaRepository);
    }

    @Bean
    public OrderDAO getDefaultOrderDAO() {
        return new DefaultOrderDAO(orderJpaRepository, authUserJpaRepository);
    }

    @Bean
    public PageAuto pageAuto() {
        return new PageAuto();
    }


}
