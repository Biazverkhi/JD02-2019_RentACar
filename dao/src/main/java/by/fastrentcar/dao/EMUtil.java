package by.fastrentcar.dao;

import org.hibernate.Session;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emFactory = null;

    public static Session getEntityManager() {
        emFactory = Persistence.createEntityManagerFactory("by.fastrentcar");
        return emFactory.createEntityManager().unwrap(Session.class);
    }

    public static void closeEMFactory() {
        emFactory.close();
    }
}
