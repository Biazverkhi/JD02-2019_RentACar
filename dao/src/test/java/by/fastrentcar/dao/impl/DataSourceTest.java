package by.fastrentcar.dao.impl;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataSourceTest {
    DataSource ds = DataSource.getInstance();

    @Test
    void getConnection() throws SQLException {
        Connection connection;
        assertNotNull(connection = ds.getConnection());
        connection.close();
    }


}
