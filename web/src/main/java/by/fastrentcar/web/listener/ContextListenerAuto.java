package by.fastrentcar.web.listener;


import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.service.AutoService;
import by.fastrentcar.service.impl.DefaultAutoService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static java.lang.Math.*;

@WebListener()
public class ContextListenerAuto implements ServletContextListener {
AutoService defaultAutoService=DefaultAutoService.getInstance();
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void  contextInitialized(ServletContextEvent servletContextEvent) {



















        String[] brandArray={"BMV","Mercedes","Audi","Honda","GMC","Lada","Tesla","Toyota", "Land Rover"};
        String[] modelArray={"X5","T1","A8","Civic","Tundra","Granta","CLS","Camry", "Evouke"};
        String[] fuelArray={"Бензин","Дизель","Газ","Электричество"};
        String[] statusArray={"свободно","занято","резерв"};


        for (int i=0;i<50;i++){
double rand=random();
            String brand=brandArray[(int) (rand*(brandArray.length))];
            String model=modelArray[(int) (rand*(modelArray.length))];
            String fuel=fuelArray[(int) (rand*(fuelArray.length))];
            String date=String.valueOf((int)(rand*10+2010));
            Double price= (double) (int) (rand * 260 + 180);
            String status=statusArray[(int) (rand*(statusArray.length))];


            Auto auto = new Auto(null, brand, model,fuel,date,price,status);
        defaultAutoService.addAuto(auto);}


    }
}
