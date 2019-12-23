package by.fastrentcar.web.spring;

import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import by.fastrentcar.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfigSpring serviceConfig;

    private DAOConfigSpring daoConfigSpring;

    public WebConfig(ServiceConfigSpring serviceConfig, DAOConfigSpring daoConfigSpring) {
        this.serviceConfig = serviceConfig;
        this.daoConfigSpring = daoConfigSpring;
    }


    @Bean
    public IndexPageController indexPageController() {
        return new IndexPageController(serviceConfig.getDefaultAutoService(), daoConfigSpring.pageAuto());
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(serviceConfig.getDefaultSecurityService());
    }

    @Bean
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public AutoController autoController() {
        return new AutoController(serviceConfig.getDefaultAutoService());
    }

    @Bean
    public AutoServicesController autoServicesController() {
        return new AutoServicesController(serviceConfig.getDefaultAutoServicesService());
    }

    @Bean
    public OrderController orderController() {
        return new OrderController(serviceConfig.getDefaultOrderService());
    }

    @Bean
    public UserController userController() {
        return new UserController(serviceConfig.getDefaultUserService());
    }

    @Bean
    public UserRegistrationController userRegistrationController() {
        return new UserRegistrationController(serviceConfig.getDefaultUserService());
    }

    @Bean
    public NewOrderController newOrderController() {
        return new NewOrderController(serviceConfig.getDefaultOrderService(), serviceConfig.getDefaultBussinesLogic());
    }

    //    @Bean
//    ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }
}
