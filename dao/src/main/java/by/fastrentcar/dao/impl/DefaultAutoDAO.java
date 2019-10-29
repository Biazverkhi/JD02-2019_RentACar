package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AutoDAO;
import by.fastrentcar.dao.DataSource;
import by.fastrentcar.model.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
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
        final String sql = "select * from auto where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    Auto auto = rs.next() ? new Auto(
                            rs.getLong("id"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("fuel"),
                            rs.getString("date"),
                            rs.getDouble("price"),
                            rs.getString("status")
                    ) : null;
                    connection.commit();
                    return auto;
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

    @Override
    public List<Auto> getListAutoT() {
        final List<Auto> autos = new ArrayList<>();
        final String sql = "select * from auto";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        autos.add(new Auto(
                                        rs.getLong("id"),
                                        rs.getString("brand"),
                                        rs.getString("model"),
                                        rs.getString("fuel"),
                                        rs.getString("date"),
                                        rs.getDouble("price"),
                                        rs.getString("status")

                                )
                        );
                    }
                    connection.commit();
                    return autos;
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

    @Override
    public Long addAutoT(Auto auto) {
        final String sql = "insert into auto( brand, model, fuel, date, price,status) values(?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, auto.getBrand());
                ps.setString(2, auto.getModel());
                ps.setString(3, auto.getFuel());
                ps.setString(4, auto.getDate());
                ps.setDouble(5, auto.getPrice());
                ps.setString(6, auto.getStatus());

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
    public boolean updateAutoT(Auto auto) {
        final String sql = "update auto set brand=?, model=?, fuel=?, date=?, price=?, status=? where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, auto.getBrand());
                ps.setString(2, auto.getModel());
                ps.setString(3, auto.getFuel());
                ps.setString(4, auto.getDate());
                ps.setDouble(5, auto.getPrice());
                ps.setString(6, auto.getStatus());

                ps.setLong(7, auto.getId());

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
    public boolean deleteAutoT(Long id) {
        final String sql = "delete from auto where id=?";
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
}
