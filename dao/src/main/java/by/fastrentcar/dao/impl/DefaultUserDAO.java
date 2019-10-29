package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.DataSource;
import by.fastrentcar.dao.UserDAO;
import by.fastrentcar.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDAO implements UserDAO {
    // private static final Logger log = LoggerFactory.getLogger(DefaultUserDao.class);

    private DefaultUserDAO() {
    }

//https://habr.com/ru/post/129494/    варианты синглтона

    private static class SingletonHolder {
        static final UserDAO HOLDER_INSTANCE = new DefaultUserDAO();
    }

    public static UserDAO getInstance() {
        return DefaultUserDAO.SingletonHolder.HOLDER_INSTANCE;
    }

    //добавить пользователя
    public Long addUserT(User user) {
        final String sql = "insert into user(firstname, lastname, phone, email, passport_number,passport_data,passport_authority) values(?,?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPassport_number());
                ps.setString(6, user.getPassport_data());
                ps.setString(7, user.getPassport_authority());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys();
                ) {
                    keys.next();
                    long key = keys.getLong(1);
                    connection.commit();
                    //     log.info("user saved:{} with id:{}", user, key);
                    return key;
                }
            }
        } catch (SQLException e) {
            // log.error("fail to save user:{}", user, e);
            try {
                connection.rollback();
                //  log.info("rollback is completed");
            } catch (SQLException ex) {
                //   log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //   log.error("fail close connection", e);
                }
            }
        }
    }

    //обновление юзера
    public boolean updateUserT(User user) {
        final String sql = "update user set firstname=?, lastname=?, phone=?, email=?, passport_number=?,passport_data=?,passport_authority=? where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPassport_number());
                ps.setString(6, user.getPassport_data());
                ps.setString(7, user.getPassport_authority());
                ps.setLong(8, user.getId());
                final int count = ps.executeUpdate();
                connection.commit();
                //    log.info("user updated:{} with id:{}", user, user.getId());
                return count > 0;
            }
        } catch (SQLException e) {
            // log.error("fail to update user:{}", user, e);
            try {
                connection.rollback();
                //log.info("rollback is completed");
            } catch (SQLException ex) {
                // log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //  log.error("fail close connection", e);
                }
            }
        }
    }

    @Deprecated
    //НЕ ИСПОЛЬЗУЕТСЯ удаление юзера. Удаляется черет authuser
    public boolean deleteUserT(long id) {
        final String sql = "delete from user where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                final int count = ps.executeUpdate();
                connection.commit();
                return count > 0;
            }
        } catch (SQLException e) {
            // log.error("user delete error, id:{}", id, e);
            try {
                connection.rollback();
                // log.info("rollback is completed");
            } catch (SQLException ex) {
                // log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // log.error("fail close connection", e);
                }
            }
        }
    }

    @Deprecated
    //НЕ ИСПОЛЬЗУЕТСЯ Вывод пользователя по логину???
    public User getUserByLogin(String login) {
        final String sql = "select * from user where login=?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                //тернарник: если юзер есть - вернуть юзера, если нет - null;
                return rs.next() ? new User(
                        rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("passport_number"),
                        rs.getString("passport_data"),
                        rs.getString("passport_authority")
                ) : null;
            }
        } catch (SQLException e) {
            //   log.error("user search error, user login:{}", login, e);

            throw new RuntimeException(e);
        }
    }

    @Deprecated
    //НЕ ИСПОЛЬЗУЕТСЯ вывод пользователя по ID
    public User getUserByIdT(long id) {
        final String sql = "select * from user where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    //тернарник: если юзер есть - вернуть юзера, если нет - null;
                    User user = rs.next() ? new User(
                            rs.getLong("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("passport_number"),
                            rs.getString("passport_data"),
                            rs.getString("passport_authority")
                    ) : null;
                    connection.commit();
                    return user;
                }
            }
        } catch (SQLException e) {
            //log.error("user search error, id:{}", id, e);
            try {
                connection.rollback();
                // log.info("rollback is completed");
            } catch (SQLException ex) {
                // log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //  log.error("fail close connection", e);
                }
            }
        }
    }

    @Deprecated
    //НЕ ИСПОЛЬЗУЕТСЯ вывод списка пользователей????  список получается из authuser
    public List<User> getUsersT() {
        final List<User> users = new ArrayList<>();
        final String sql = "select * from user";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        users.add(new User(
                                        rs.getLong("id"),
                                        rs.getString("firstName"),
                                        rs.getString("lastName"),
                                        rs.getString("phone"),
                                        rs.getString("email"),
                                        rs.getString("passport_number"),
                                        rs.getString("passport_data"),
                                        rs.getString("passport_authority")
                                )
                        );
                    }
                    connection.commit();
                    return users;
                }
            }
        } catch (SQLException e) {
            // log.error("users search error", e);
            try {
                connection.rollback();
                //log.info("rollback is completed");
            } catch (SQLException ex) {
                // log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //   log.error("fail close connection", e);
                }
            }
        }

    }
}