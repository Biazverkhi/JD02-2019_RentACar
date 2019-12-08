package by.fastrentcar.web.spring;

import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.web.servlet.IndexPageServlet;
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

    //    @Bean
//    public LoginController loginController(){
//        return new LoginController(serviceConfig.securityService());
//    }
//
//    @Bean
//    public LogoutController logoutController(){
//        return new LogoutController(serviceConfig.securityService());
//    }
//
    @Bean
    public IndexPageServlet studentsController() {
        return new IndexPageServlet(serviceConfig.getDefaultAutoService());
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
