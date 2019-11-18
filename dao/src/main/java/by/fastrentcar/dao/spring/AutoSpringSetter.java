package by.fastrentcar.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoSpringSetter {
    @Autowired
    public void setAutoSport(AutoSport autoSport) {
        this.autoSport = autoSport;
    }

    AutoSport autoSport;


    public void getAuto() {
        System.out.println("This work @Autowired from setter:");
        autoSport.getAuto();
    }
}
