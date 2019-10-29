package by.fastrentcar.web.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void  contextInitialized(ServletContextEvent servletContextEvent) {
     //  AuthUserDao d= DefaultAuthUserDao.getInstance();
    //   d.addAuthUserT(new AuthUser(null, "ttt","www", Role.USER,1l));
     // UserDao day= DefaultUserDao.getInstance();
      //day.addUserT(new User("rrrrr","sranoe", "4456654", "padla@gavno.by","3434534534","344444444","pridurok"));
        //        try {
//            DefaultUserDao.getInstance().addUser("12","13");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            DefaultUserDao.getInstance().addUser("123","132");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
