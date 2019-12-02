package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.dao.entity.AuthUserEntity;
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

    private static final Logger log = LoggerFactory.getLogger(DefaultOrderDAO.class);

    @Override
    public Long addOrderT(Order order) {
        Session session = EMUtil.getSession();
        OrderEntity orderEntity = new OrderEntity(order);
        Long key = null;
        try {
            session.beginTransaction();
            // session.persist(autoEntity);
            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, order.getAuthuserId());
            orderEntity.setAuthUserEntity(authUserEntity);
            authUserEntity.getOrderEntity().add(orderEntity);
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
        Session session = null;
        int count = 0;
        try {
            OrderEntity orderEntity = new OrderEntity(order);
            session = EMUtil.getSession();
            session.beginTransaction();
            count = session.createQuery("update OrderEntity oe " +
                    "set oe.autoId=:autoId, " +
                    "oe.createOrderDate=:createOrderDate, " +
                    "oe.startOrderDate=:startOrderDate, " +
                    "oe.stopOrderDate=:stopOrderDate, " +
                    "oe.comment=:comment, " +
                    "oe.reservStatus=:reservStatus, " +
                    "oe.priceArend=:priceArend " +
                    "where oe.id=:id")
                    .setParameter("autoId", orderEntity.getAutoId())
                    .setParameter("createOrderDate", orderEntity.getCreateOrderDate())
                    .setParameter("startOrderDate", orderEntity.getStartOrderDate())
                    .setParameter("stopOrderDate", orderEntity.getStopOrderDate())
                    .setParameter("comment", orderEntity.getComment())
                    .setParameter("reservStatus", orderEntity.getReservStatus())
                    .setParameter("priceArend", orderEntity.getPriceArend())
                    .setParameter("id", orderEntity.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            //             log.error("fail to update user:{}", user, e);
            try {
                session.getTransaction().rollback();
                //log.info("rollback is completed");
            } catch (HibernateException ex) {
                // log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    //  log.error("fail close connection", e);
                }
            }
        }
        return count > 0;
    }

    @Override
    public boolean deleteOrderT(Long id) {
        Session session = EMUtil.getSession();
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
            session = EMUtil.getSession();
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
            session = EMUtil.getSession();
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
            session = EMUtil.getSession();
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