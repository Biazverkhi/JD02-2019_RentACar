package by.fastrentcar.web.spring;

import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.web.controller.*;
import by.fastrentcar.web.page.PageAutoProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.Locale;

@Configuration
@EnableWebMvc
@PropertySource("classpath:pages.properties")
public class WebConfig {

    private ServiceConfigSpring serviceConfig;
    @Autowired
    private PageAutoProperty pageAutoProperty;

    public WebConfig(ServiceConfigSpring serviceConfig) {
        this.serviceConfig = serviceConfig;
    }


    @Bean
    public IndexPageController indexPageController() {
        return new IndexPageController(serviceConfig.getDefaultAutoService(), pageAutoProperty);
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
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PageAutoProperty pageAutoProperty() {
        return new PageAutoProperty();
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

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");

        return source;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("en"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }

}
