package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.DataSource;
import by.fastrentcar.dao.OrderDAO;
import by.fastrentcar.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        final String sql = "insert into orders( authuser_id, auto_id, create_order_date, start_order_date, stop_order_date, commentary, reserv_status, price_arend) values(?,?,?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, order.getAuthuserId());
                ps.setLong(2, order.getAutoId());
                ps.setTimestamp(3, Timestamp.valueOf(order.getCreateOrderDate()));
                ps.setTimestamp(4, Timestamp.valueOf(order.getStartOrderDate()));
                ps.setTimestamp(5, Timestamp.valueOf(order.getStopOrderDate()));
                ps.setString(6, order.getComment());
                ps.setString(7, order.getReservStatus());
                ps.setDouble(8, order.getPriceArend());
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys();
                ) {
                    keys.next();
                    long key = keys.getLong(1);
                    connection.commit();
                    //      log.info("authuser saved:{} with id:{}", authUser, key);
                    return key;
                }
            }
        } catch (SQLException e) {
            // log.error("fail to save autchuser:{}", authUser, e);
            try {
                connection.rollback();
                //     log.info("rollback is completed");
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
                    //     log.error("fail close connection", e);
                }
            }
        }

    }

    @Override
    public boolean updateOrderT(Order order) {
        final String sql = "update orders set authuser_id=?, auto_id=?, create_order_date=?, start_order_date=?, stop_order_date=?, commentary=?, reserv_status=?, price_arend=? where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, order.getAuthuserId());
                ps.setLong(2, order.getAutoId());
                ps.setTimestamp(3, Timestamp.valueOf(order.getCreateOrderDate()));
                ps.setTimestamp(4, Timestamp.valueOf(order.getStartOrderDate()));
                ps.setTimestamp(5, Timestamp.valueOf(order.getStopOrderDate()));
                ps.setString(6, order.getComment());
                ps.setString(7, order.getReservStatus());
                ps.setDouble(8, order.getPriceArend());
                ps.setLong(9, order.getId());

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

    @Override
    public boolean deleteOrderT(Long id) {
        final String sql = "delete from orders where id=?";
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

    @Override
    public List<Order> getListOrderT() {
        final List<Order> orders = new ArrayList<>();
        final String sql = "select * from orders";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        orders.add(new Order(
                                        rs.getLong("id"),
                                        rs.getLong("authuser_id"),
                                        rs.getLong("auto_id"),
                                       rs.getTimestamp("create_order_date").toLocalDateTime(),
                                        rs.getTimestamp("start_order_date").toLocalDateTime(),
                                        rs.getTimestamp("stop_order_date").toLocalDateTime(),
                                        rs.getString("commentary"),
                                        rs.getString("reserv_status"),
                                        rs.getDouble("price_arend")
                                )
                        );
                    }
                    connection.commit();
                    return orders;
                }
            }
        } catch (SQLException e) {
            log.error("users search error", e);
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
    @Override
    public List<Order> getListOrderByIdUserT(Long authuser_id) {
        final List<Order> orders = new ArrayList<>();
        final String sql = "select * from orders where authuser_id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, authuser_id);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        orders.add(new Order(
                                        rs.getLong("id"),
                                        rs.getLong("authuser_id"),
                                        rs.getLong("auto_id"),
                                rs.getTimestamp("create_order_date").toLocalDateTime(),
                                rs.getTimestamp("start_order_date").toLocalDateTime(),
                                rs.getTimestamp("stop_order_date").toLocalDateTime(),
                                        rs.getString("commentary"),
                                        rs.getString("reserv_status"),
                                        rs.getDouble("price_arend")
                                )
                        );
                    }
                    connection.commit();
                    return orders;
                }
            }
        } catch (SQLException e) {
            log.error("users search error", e);
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

    @Override
    public Order getOrderByIdT(Long id) {
        final String sql = "select * from orders where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    Order order = rs.next() ? new Order(
                            rs.getLong("id"),
                            rs.getLong("authuser_id"),
                            rs.getLong("auto_id"),
                            rs.getTimestamp("create_order_date").toLocalDateTime(),
                            rs.getTimestamp("start_order_date").toLocalDateTime(),
                            rs.getTimestamp("stop_order_date").toLocalDateTime(),
                            rs.getString("commentary"),
                            rs.getString("reserv_status"),
                            rs.getDouble("price_arend")
                    ) : null;
                    connection.commit();
                    return order;
                }
            }
        } catch (SQLException e) {
            //   log.error("user search error, login:{}", login, e);
            try {
                connection.rollback();
                //    log.info("rollback is completed");
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
}
