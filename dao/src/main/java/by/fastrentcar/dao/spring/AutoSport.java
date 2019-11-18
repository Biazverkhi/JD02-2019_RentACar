package by.fastrentcar.dao.spring;

import org.springframework.stereotype.Component;

@Component
public class AutoSport implements Auto {

    @Override
    public void getAuto() {
        System.out.println("This is sport auto from class AutoSport.class");
    }
}
