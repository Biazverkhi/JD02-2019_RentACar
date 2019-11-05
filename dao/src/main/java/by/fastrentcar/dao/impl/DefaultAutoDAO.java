package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.dao.EMUtil;
import by.fastrentcar.dao.entity.AutoEntity;
import by.fastrentcar.model.auto.Auto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultAutoDAO implements AutoDAO {
    private DefaultAutoDAO() {
    }

    private static final Logger log = LoggerFactory.getLogger(DefaultAutoDAO.class);

    private static class SingletonHolder {
        static final AutoDAO HOLDER_INSTANCE = new DefaultAutoDAO();
    }

    public static AutoDAO getInstance() {
        return DefaultAutoDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Auto getAutoByIdT(Long id) {
        Session session = null;
        AutoEntity autoEntity = null;
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();
            autoEntity = session.get(AutoEntity.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return autoEntity != null ? autoEntity.convertAutoByAutoEntity() : null;
    }


    @Override
    public List<Auto> getListAutoT() {

        Session session = null;
        List<Auto> auto = new ArrayList<>();
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();

            Query query = session.createQuery("from AutoEntity");
            List autoEntity = query.list();
            System.out.println("entity size: " + autoEntity.size());

            for (Object ae : autoEntity) {
                auto.add(((AutoEntity) ae).convertAutoByAutoEntity());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return auto;
    }

    @Override
    public Long addAutoT(Auto auto) {
        Session session = EMUtil.getEntityManager();
        AutoEntity autoEntity = new AutoEntity(auto);
        Long key = null;
        try {
            session.beginTransaction();
            // session.persist(autoEntity);
            key = (Long) session.save(autoEntity);
            session.getTransaction().commit();
            // EMUtil.closeEMFactory();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return key;
    }

    @Override
    public boolean updateAutoT(Auto auto) {
        Session session = EMUtil.getEntityManager();
        AutoEntity autoEntity = new AutoEntity(auto);
        boolean flag = false;
        try {
            session.beginTransaction();
            session.update(autoEntity);
            session.getTransaction().commit();
            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean deleteAutoT(Long id) {
        Session session = EMUtil.getEntityManager();
        boolean flag = false;
        try {
            session.beginTransaction();
            session.delete(session.get(AutoEntity.class, id));
            session.getTransaction().commit();
            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return flag;
    }
}
