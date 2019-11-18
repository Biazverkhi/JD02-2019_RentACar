package by.fastrentcar.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoSpringField {
    @Autowired
    AutoBus autoBus;

    public void getAuto() {
        System.out.println("This work @Autowired from field:");
        autoBus.getAuto();
    }
}
