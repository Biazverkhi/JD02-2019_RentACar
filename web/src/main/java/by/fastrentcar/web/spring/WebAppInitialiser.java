package by.fastrentcar.web.spring;

import by.fastrentcar.service.config.ServiceConfigSpring;
import by.fastrentcar.springdata.config.DAOConfigSpring;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, ServiceConfigSpring.class, DAOConfigSpring.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}