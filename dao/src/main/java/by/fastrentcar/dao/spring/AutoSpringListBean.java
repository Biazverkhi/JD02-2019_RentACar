package by.fastrentcar.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoSpringListBean {
    List<Auto> autoList;

    @Autowired
    public AutoSpringListBean(List<Auto> autoList) {
        this.autoList = autoList;
    }

    public void getListBeans() {

        System.out.println("List Auto:");
        autoList.forEach(auto -> auto.getAuto());


    }
}
