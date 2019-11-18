package by.fastrentcar.dao.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:auto.properties")
public class AutoSpringPropertyBean {
    @Value("${auto}")
    String auto;

    public void getAutos() {
        System.out.println(auto);
    }
}
