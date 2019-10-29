package by.fastrentcar.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    //private static final Logger log = LoggerFactory.getLogger(DataSource.class);
    private final ComboPooledDataSource pool;

    private static class SingletonHolder {
        static final DataSource HOLDER_INSTANCE = new DataSource();
    }

    public static DataSource getInstance() {
        return DataSource.SingletonHolder.HOLDER_INSTANCE;
    }

    public DataSource() {
//подключаем драйвер
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //log.error("jdbc driver don't find", e);
            throw new RuntimeException(e);
        }
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(pass);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(1);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(180);
    }

    public Connection getConnection() {
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
