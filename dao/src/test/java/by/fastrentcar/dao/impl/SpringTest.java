package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.dao.ConfigSpringDAO;
import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.dao.spring.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringTest {

    @Test
    void task1() {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-by-AutoDao.xml");
        final AutoDAO dao = context.getBean(AutoDAO.class);
        assertNotNull(dao.getListAutoT(0, 10));
        context.close();
    }


    @Test
    void task2() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpringDAO.class);
        AuthUserDAO dao = context.getBean(AuthUserDAO.class);
        assertNotNull(dao.getAuthUserUserDTO("11"));
        context.close();
    }

    @Test
    void task3() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao");
        OrderDAO dao = context.getBean(DefaultOrderDAO.class);
        assertNotNull(dao.getListOrderT());
        context.close();
    }

    @Test
    void task4() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao.spring");
        AutoSpringField field = context.getBean(AutoSpringField.class);
        field.getAuto();
        context.close();
    }


    @Test
    void task5() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao.spring");
        AutoSpringSetter setter = context.getBean(AutoSpringSetter.class);
        setter.getAuto();
        context.close();
    }


    @Test
    void task6() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao.spring");
        AutoSpringConstr c = context.getBean(AutoSpringConstr.class);
        c.getAuto();
        context.close();
    }

    @Test
    void task7() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao.spring");
        AutoSpring2Beans c = context.getBean(AutoSpring2Beans.class);
        c.get2Beans();
        context.close();
    }

    @Test
    void task8() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao.spring");
        AutoSpringListBean c = context.getBean(AutoSpringListBean.class);
        c.getListBeans();
        context.close();
    }


    @Test
    void task9() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("by.fastrentcar.dao.spring");
        AutoSpringPropertyBean c = context.getBean(AutoSpringPropertyBean.class);
        c.getAutos();
        context.close();
    }


}
