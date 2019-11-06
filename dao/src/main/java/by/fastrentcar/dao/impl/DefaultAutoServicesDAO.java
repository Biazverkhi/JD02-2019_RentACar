package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoServicesDAO;
import by.fastrentcar.dao.EMUtil;
import by.fastrentcar.dao.entity.AutoEntity;
import by.fastrentcar.dao.entity.AutoServicesEntity;
import by.fastrentcar.model.auto.AutoServices;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultAutoServicesDAO implements AutoServicesDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultAutoServicesDAO.class);

    private static class SingletonHolder {
        static final AutoServicesDAO HOLDER_INSTANCE = new DefaultAutoServicesDAO();
    }

    public static AutoServicesDAO getInstance() {
        return DefaultAutoServicesDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean addAutoServicesToAuto(Long autoId, Long servicesId) {
        Session session = EMUtil.getEntityManager();
        boolean flag = false;
        try {
            session.beginTransaction();
            AutoServicesEntity autoServicesEntity = session.get(AutoServicesEntity.class, servicesId);
            AutoEntity autoEntity = session.get(AutoEntity.class, autoId);
            autoEntity.getAutoServicesEntity().add(autoServicesEntity);
            autoServicesEntity.getAutoEntity().add(autoEntity);
            session.getTransaction().commit();
            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public Long addAutoServicesT(AutoServices autoServices) {
        Session session = EMUtil.getEntityManager();
        AutoServicesEntity autoServicesEntity = new AutoServicesEntity(autoServices);
        Long key = null;
        try {
            session.beginTransaction();
            // session.persist(autoEntity);
            key = (Long) session.save(autoServicesEntity);
            session.getTransaction().commit();
            // EMUtil.closeEMFactory();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return key;
    }

    @Override
    public boolean updateAutoServicesT(AutoServices autoServices) {
        Session session = EMUtil.getEntityManager();
        AutoServicesEntity autoServicesEntity = new AutoServicesEntity(autoServices);
        boolean flag = false;
        try {
            session.beginTransaction();
            session.update(autoServicesEntity);
            session.getTransaction().commit();
            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean deleteAutoServicesT(Long id) {
        Session session = EMUtil.getEntityManager();
        boolean flag = false;
        try {
            session.beginTransaction();
            session.delete(session.get(AutoServicesEntity.class, id));
            session.getTransaction().commit();
            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List<AutoServices> getListAutoServicesT() {
        Session session = null;
        List<AutoServices> autoServices = new ArrayList<>();
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();

            Query query = session.createQuery("from AutoServicesEntity");
            List autoEntity = query.list();
            System.out.println("entity size: " + autoEntity.size());

            for (Object ae : autoEntity) {
                autoServices.add(((AutoServicesEntity) ae).convertAutoServicesByAutoServicesEntity());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return autoServices;
    }

    @Override
    public AutoServices getAutoServicesByIdT(Long id) {
        Session session = null;
        AutoServicesEntity autoServicesEntity = null;
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();
            autoServicesEntity = session.get(AutoServicesEntity.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return autoServicesEntity != null ? autoServicesEntity.convertAutoServicesByAutoServicesEntity() : null;
    }
}
