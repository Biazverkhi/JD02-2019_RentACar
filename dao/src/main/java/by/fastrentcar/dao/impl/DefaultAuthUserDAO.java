package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.dao.EMUtil;
import by.fastrentcar.dao.entity.AuthUserEntity;
import by.fastrentcar.dao.entity.UserEntity;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.model.user.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserDAO implements AuthUserDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDAO.class);

    private DefaultAuthUserDAO() {
    }

//https://habr.com/ru/post/129494/ //    варианты синглтона

    private static class SingletonHolder {
        static final AuthUserDAO HOLDER_INSTANCE = new DefaultAuthUserDAO();
    }

    public static AuthUserDAO getInstance() {
        return DefaultAuthUserDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthUser getByLoginT(String login) {
        Session session = null;
        AuthUserEntity authUserEntity = null;
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();
            authUserEntity = (AuthUserEntity) session.createQuery("from AuthUserEntity aue where aue.login=:login ")
                    .setParameter("login", login).getSingleResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return authUserEntity != null ? authUserEntity.convertAuthUserByAuthUserEntity() : null;

    }

    @Override
    public AuthUser getByIdT(Long id) {
        Session session = null;
        AuthUserEntity authUserEntity = null;
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();
            authUserEntity = session.get(AuthUserEntity.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return authUserEntity != null ? authUserEntity.convertAuthUserByAuthUserEntity() : null;


    }

    @Override
    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        Session session = null;
        List<AuthUserUserDTO> authUserUserDTO = new ArrayList<>();
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();

            Query query = session.createQuery("from UserEntity");
            List userEntity = query.list();

            for (Object oe : userEntity) {
                AuthUser authUser = ((UserEntity) oe).getAuthUserEntity().convertAuthUserByAuthUserEntity();
                User user = ((UserEntity) oe).convertUserbyUserEntity();
                authUserUserDTO.add(new AuthUserUserDTO(authUser, user));
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return authUserUserDTO;

    }

    @Override
    public AuthUserUserDTO getAuthUserUserDTO(String login) {
        Session session = null;
        AuthUserUserDTO authUserUserDTO = null;
        try {
            session = EMUtil.getEntityManager();
            session.beginTransaction();
            AuthUserEntity authUserEntity = (AuthUserEntity) session.createQuery("from AuthUserEntity aue where aue.login=:login ")
                    .setParameter("login", login).getSingleResult();
            User user = authUserEntity.getUserEntity().convertUserbyUserEntity();
            AuthUser authUser = authUserEntity.convertAuthUserByAuthUserEntity();
            authUserUserDTO = new AuthUserUserDTO(authUser, user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return authUserUserDTO;
    }

    @Override
    public Long addAuthUserUserT(AuthUser authuser, User user) {
        Session session = EMUtil.getEntityManager();
        Long key = null;
        try {
            session.beginTransaction();
            // session.persist(autoEntity);
            UserEntity userEntity = new UserEntity(user);
            AuthUserEntity authUserEntity = new AuthUserEntity(authuser, userEntity);
            userEntity.setAuthUserEntity(authUserEntity);
            key = (Long) session.save(userEntity);
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
    public boolean updateAuthUserUserT(AuthUser authuser, User user) {
        Session session = EMUtil.getEntityManager();
        boolean flag = false;
        try {
            session.beginTransaction();
            UserEntity userEntity = new UserEntity(user);
            AuthUserEntity authUserEntity = new AuthUserEntity(authuser, userEntity);
            userEntity.setAuthUserEntity(authUserEntity);
            session.update(userEntity);
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
    public boolean deleteAuthUserT(Long id) {
        Session session = EMUtil.getEntityManager();
        boolean flag = false;
        try {
            session.beginTransaction();
            session.delete(session.get(AuthUserEntity.class, id).getUserEntity());
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
