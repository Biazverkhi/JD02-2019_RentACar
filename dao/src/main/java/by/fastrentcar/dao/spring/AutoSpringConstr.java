package by.fastrentcar.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoSpringConstr {

    AutoSport autoSport;

    public AutoSpringConstr() {
    }

    @Autowired
    public AutoSpringConstr(AutoSport autoSport) {
        this.autoSport = autoSport;
    }

    public void getAuto() {
        System.out.println("This work @Autowired from constructor:");
        autoSport.getAuto();
    }
}
