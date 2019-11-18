package by.fastrentcar.dao.spring;

import org.springframework.stereotype.Component;

@Component
public class AutoBus implements Auto {

    @Override
    public void getAuto() {
        System.out.println("This is bus from class AutoBus.class");
    }
}
