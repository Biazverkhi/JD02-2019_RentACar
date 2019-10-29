package by.fastrentcar.dao.impl;

import by.fastrentcar.dao.AuthUserDAO;
import by.fastrentcar.model.user.AuthUserUserDTO;
import by.fastrentcar.dao.DataSource;
import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;
import by.fastrentcar.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
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

    //!!!для авторизации и аутентификации
    public AuthUser getByLoginT(String login) {
        final String sql = "select * from auth_user where login=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                try (ResultSet rs = ps.executeQuery()) {
                    //тернарник: если юзер есть - вернуть юзера, если нет - null;
                    AuthUser authuser = rs.next() ? new AuthUser(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role")),
                            rs.getLong("user_id")
                    ) : null;
                    connection.commit();
                    return authuser;
                }
            }
        } catch (SQLException e) {
            log.error("user search error, login:{}", login, e);
            try {
                connection.rollback();
                log.info("rollback is completed");
            } catch (SQLException ex) {
                log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }

    }

    @Override
    public AuthUser getByIdT(Long id) {
        final String sql = "select * from auth_user where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    //тернарник: если юзер есть - вернуть юзера, если нет - null;
                    AuthUser authuser = rs.next() ? new AuthUser(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role")),
                            rs.getLong("user_id")
                    ) : null;
                    connection.commit();
                    return authuser;
                }
            }
        } catch (SQLException e) {
            log.error("user search error, id:{}", id, e);
            try {
                connection.rollback();
                log.info("rollback is completed");
            } catch (SQLException ex) {
                log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }
    }

    //!!!для вывода на панель списка пользователей для администрирования JOIN
    public List<AuthUserUserDTO> getListAuthUserUserDTO() {
        final List<AuthUserUserDTO> users = new ArrayList<>();
        final String sql = "select au.id, au.login, au.password, au.role, au.user_id," +
                "u.firstname, u.lastname, u.phone, u.email, u.passport_number, u.passport_data, u.passport_authority " +
                "from auth_user au left join user u on au.user_id = u.id";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        users.add(new AuthUserUserDTO(
                                        rs.getLong("id"),
                                        rs.getString("login"),
                                        rs.getString("password"),
                                        Role.valueOf(rs.getString("role")),
                                        rs.getLong("user_id"),
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
            log.error("users search error", e);
            try {
                connection.rollback();
                log.info("rollback is completed");
            } catch (SQLException ex) {
                log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }

    }

    //!!!для вывода на панель пользователя для редактированияя
    public AuthUserUserDTO getAuthUserUserDTO(String login) {
        final String sql = "select * from auth_user where login=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sql)
            ) {
                ps.setString(1, login);

                try (ResultSet rs = ps.executeQuery()) {
                    AuthUserUserDTO authUserUserDTO = rs.next() ? new AuthUserUserDTO(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role")),
                            rs.getLong("user_id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("passport_number"),
                            rs.getString("passport_data"),
                            rs.getString("passport_authority")
                    ) : null;
                    connection.commit();
                    return authUserUserDTO;
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

    //!!!добавить пользователя
    public Long addAuthUserUserT(AuthUser authUser, User user) {
        final String sqlUs = "insert into user(firstname, lastname, phone, email, passport_number,passport_data,passport_authority) values(?,?,?,?,?,?,?)";
        final String sqlAu = "insert into auth_user(login, password, role, user_id) values(?,?,?,?)";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement psUser = connection.prepareStatement(sqlUs, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, user.getFirstName());
                psUser.setString(2, user.getLastName());
                psUser.setString(3, user.getPhone());
                psUser.setString(4, user.getEmail());
                psUser.setString(5, user.getPassport_number());
                psUser.setString(6, user.getPassport_data());
                psUser.setString(7, user.getPassport_authority());
                psUser.executeUpdate();
                try (ResultSet keys = psUser.getGeneratedKeys()
                ) {
                    keys.next();
                    long userId = keys.getLong(1);
                    log.info("user saved:{} with id:{}", user, userId);
                    try (PreparedStatement psAuthUser = connection.prepareStatement(sqlAu, Statement.RETURN_GENERATED_KEYS)) {
                        psAuthUser.setString(1, authUser.getLogin());
                        psAuthUser.setString(2, authUser.getPassword());
                        psAuthUser.setString(3, authUser.getRole().name());
                        psAuthUser.setLong(4, userId);
                        psAuthUser.executeUpdate();
                        try (ResultSet keysAuthUser = psAuthUser.getGeneratedKeys()
                        ) {
                            keysAuthUser.next();
                            long authUtherId = keysAuthUser.getLong(1);
                            connection.commit();
                            log.info("authuser saved:{} with id:{}", authUser, authUtherId);
                            return authUtherId;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error("fail to save autchuser:{}", authUser, e);
            try {
                connection.rollback();
                log.info("rollback is completed");
            } catch (SQLException ex) {
                log.error("rollback didn't work", ex);
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("fail close connection", e);
                }
            }
        }
    }

    //!!! обновить пользователя
    public boolean updateAuthUserUserT(AuthUser authUser, User user) {
        final String sqlAu = "update auth_user set login=?, password=?, role=? where id=?";
        final String sqlUs = "update user set firstname=?, lastname=?, phone=?, email=?, passport_number=?,passport_data=?,passport_authority=? where id=?";
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(sqlUs);
            PreparedStatement psAu = connection.prepareStatement(sqlAu)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPassport_number());
                ps.setString(6, user.getPassport_data());
                ps.setString(7, user.getPassport_authority());
                ps.setLong(8, user.getId());

                psAu.setString(1, authUser.getLogin());
                psAu.setString(2, authUser.getPassword());
                psAu.setString(3, authUser.getRole().name());
                psAu.setLong(4, authUser.getId());

               final int count=ps.executeUpdate()+psAu.executeUpdate();
                connection.commit();
                return count > 0;
                //    log.info("user updated:{} with id:{}", user, user.getId());
            }
        } catch (SQLException e) {
//             log.error("fail to update user:{}", user, e);
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

    //удаление юзера JOIN. Лучше так не делать.
    public boolean deleteAuthUserT(Long id) {
        final String sql = "delete au, u from auth_user au left join user u on au.user_id = u.id where au.id=?";
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
