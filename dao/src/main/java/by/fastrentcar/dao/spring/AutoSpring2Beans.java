package by.fastrentcar.dao.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AutoSpring2Beans {
    @Qualifier("autoSport")
    private
    Auto autoSport;

    @Qualifier("autoBus")
    private
    Auto autoBus;

    public AutoSpring2Beans(Auto autoSport, Auto autoBus) {
        this.autoSport = autoSport;
        this.autoBus = autoBus;
    }


    public void get2Beans() {
        System.out.println("This work @Autowired from 2 Beans & one interface:");
        autoSport.getAuto();
        autoBus.getAuto();


    }
}
