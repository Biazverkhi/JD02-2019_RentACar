package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.EMUtil;
import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.dao.entity.OrderEntity;
import by.fastrentcar.model.order.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDAO implements OrderDAO {
    private DefaultOrderDAO() {
    }

    private static final Logger log = LoggerFactory.getLogger(DefaultOrderDAO.class);

    private static class SingletonHolder {
        static final OrderDAO HOLDER_INSTANCE = new DefaultOrderDAO();
    }

    public static OrderDAO getInstance() {
        return DefaultOrderDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Long addOrderT(Order order) {
        Session session = EMUtil.getEntityManager();
        OrderEntity orderEntity = new OrderEntity(order);
        Long key = null;
        try {
            session.beginTransaction();
            // session.persist(autoEntity);
            key = (Long) session.save(orderEntity);
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
    public boolean updateOrderT(Order order) {

        Session session = EMUtil.getEntityManager();
        OrderEntity orderEntity = new OrderEntity(order);
        boolean flag = false;
        try {
            session.beginTransaction();
            session.update(orderEntity);
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
    public boolean deleteOrderT(Long id) {
        Session session = EMUtil.getEntityManager();
        boolean flag = false;
        try {
            session.beginTransaction();
            session.delete(session.get(OrderEntity.class, id));
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
    public List<Order> getListOrderT() {
        Session session = null;
        List<Order> order = new ArrayList<>();
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();

            Query query = session.createQuery("from OrderEntity");
            List orderEntity = query.list();
            System.out.println("entity size: " + orderEntity.size());

            for (Object oe : orderEntity) {
                order.add(((OrderEntity) oe).convertOrderByOrderEntity());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;

    }

    @Override
    public List<Order> getListOrderByIdUserT(Long authuser_id) {
        Session session = null;
        List<Order> order = new ArrayList<>();
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();

            Query query = session.createQuery("from OrderEntity oe where oe.authuserId= :id");
            query.setParameter("id", authuser_id);
            List orderEntity = query.list();
            System.out.println("entity size: " + orderEntity.size());

            for (Object oe : orderEntity) {
                order.add(((OrderEntity) oe).convertOrderByOrderEntity());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public Order getOrderByIdT(Long id) {
        Session session = null;
        OrderEntity orderEntity = null;
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();
            orderEntity = session.get(OrderEntity.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return orderEntity != null ? orderEntity.convertOrderByOrderEntity() : null;

    }

}