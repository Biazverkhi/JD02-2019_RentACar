package by.fastrentcar.web.spring;

import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfigSpring serviceConfig;

    public WebConfig(ServiceConfigSpring serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public IndexPageController indexPageController() {
        return new IndexPageController(serviceConfig.getDefaultAutoService());
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

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }
//    @Bean
//    public UrlBasedViewResolver tilesViewResolver(){
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setViewClass(TilesView.class);
//        return resolver;
//    }

//    @Bean
//    public TilesConfigurer tilesConfigurer(){
//        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
//        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
//        return tilesConfigurer;
//    }
}
