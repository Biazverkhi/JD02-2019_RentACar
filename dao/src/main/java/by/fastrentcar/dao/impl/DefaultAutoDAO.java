package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.dao.entity.AutoEntity;
import by.fastrentcar.dao.entity.AutoServicesEntity;
import by.fastrentcar.model.auto.Auto;
import by.fastrentcar.model.auto.AutoServices;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DefaultAutoDAO implements AutoDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultAutoDAO.class);

//    @Override
//    public Long addAutoT(Auto auto) {
//        Session session = EMUtil.getSession();
//        AutoEntity autoEntity = new AutoEntity(auto);
//        Long key = null;
//        try {
//            session.beginTransaction();
//            // session.persist(autoEntity);
//            key = (Long) session.save(autoEntity);
//            session.getTransaction().commit();
//            // EMUtil.closeEMFactory();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return key;
//    }
//
//    @Override
//    public boolean updateAutoT(Auto auto) {
//        Session session = EMUtil.getSession();
//        AutoEntity autoEntity = new AutoEntity(auto);
//        boolean flag = false;
//        try {
//            session.beginTransaction();
//            session.update(autoEntity);
//            session.getTransaction().commit();
//            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
//
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return flag;
//    }
//
//    @Override
//    public boolean deleteAutoT(Long id) {
//        Session session = EMUtil.getSession();
//        boolean flag = false;
//        try {
//            session.beginTransaction();
//            session.delete(session.get(AutoEntity.class, id));
//            session.getTransaction().commit();
//            flag = session.getTransaction().getStatus().isOneOf(TransactionStatus.COMMITTED);
//
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//        return flag;
//    }
//
//    @Override
//    public Auto getAutoByIdT(Long id) {
//        Session session = null;
//        AutoEntity autoEntity = null;
//        try {
//            session = EMUtil.getSession();
//            session.beginTransaction();
//            autoEntity = session.get(AutoEntity.class, id);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return autoEntity != null ? autoEntity.convertAutoByAutoEntity() : null;
//    }
//
//
//    @Override
//    public List<Auto> getListAutoT() {
//
//        Session session = null;
//        List<Auto> auto = new ArrayList<>();
//        try {
//            session = EMUtil.getSession();
//            session.beginTransaction();
//
//
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<AutoEntity> criteria = cb.createQuery(AutoEntity.class);//указываем результат
//            Root<AutoEntity> autoEntityRoot = criteria.from(AutoEntity.class);//указываем откуда
//            criteria.select(autoEntityRoot);
//            List autoEntity = session.createQuery(criteria).getResultList();
//
//
//            for (Object ae : autoEntity) {
//                auto.add(((AutoEntity) ae).convertAutoByAutoEntity());
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return auto;
//    }
//
//    public List<Auto> getListAutoT(int start, int stop) {
//
//        Session session = null;
//        List<Auto> auto = new ArrayList<>();
//        try {
//            session = EMUtil.getSession();
//            session.beginTransaction();
//
//            List autoEntity = session.createQuery("from AutoEntity")
//                    .setFirstResult(start)
//                    .setMaxResults(stop)
//                    .getResultList();
//            for (Object ae : autoEntity) {
//                auto.add(((AutoEntity) ae).convertAutoByAutoEntity());
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return auto;
//    }
//
//    @Override
//    public long getCountAuto() {
//        Session session = null;
//        Long count = 0l;
//        try {
//            session = EMUtil.getSession();
//            session.beginTransaction();
//
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
//            criteria.select(cb.count(criteria.from(AutoEntity.class)));
//            count = session.createQuery(criteria).getResultList().get(0);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return count;
//    }
//
//    @Override
//    public List<AutoServices> getAutoServicesByAutoIdT(Long id) {
//        Session session = null;
//        List<AutoServices> autoServices = new ArrayList<>();
//        try {
//            session = EMUtil.getSession();
//            session.beginTransaction();
//
//            AutoEntity autoEntity = session.get(AutoEntity.class, id);
//            List<AutoServicesEntity> autoServicesEntities = autoEntity.getAutoServicesEntity();
//            for (AutoServicesEntity ase : autoServicesEntities) {
//                autoServices.add(ase.convertAutoServicesByAutoServicesEntity());
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return autoServices;
//
//    }
}
