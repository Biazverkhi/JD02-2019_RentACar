package by.fastrentcar.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emFactory = null;

    public static EntityManager getEntityManager() {
        return getEntityManager("by.fastrentcar");
    }

    public static EntityManager getEntityManager(String unit) {
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory(unit);
        }
        return emFactory.createEntityManager();
    }

    public static Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }

    public static void closeEMFactory() {
        emFactory.close();
    }
}
