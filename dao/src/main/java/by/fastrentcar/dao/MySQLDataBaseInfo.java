package by.fastrentcar.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDataBaseInfo {

    private static class SingletonHolder {
        static final MySQLDataBaseInfo HOLDER_INSTANCE = new MySQLDataBaseInfo();
    }

    public static MySQLDataBaseInfo getInstance() {
        return MySQLDataBaseInfo.SingletonHolder.HOLDER_INSTANCE;
    }

    private MySQLDataBaseInfo() {
    }

        public List<String> getTablesMetadata(DatabaseMetaData metadata) throws SQLException {
        String table[] = {"TABLE"};
        List<String> tables;
        try (ResultSet rs = metadata.getTables("rentacar", null, null, table)) {
            tables = new ArrayList<>();
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        }
        return tables;
    }

    public String getColumnsMetadata(DatabaseMetaData metadata, String tableName) throws SQLException {
        StringBuilder result;
        try (ResultSet rs = metadata.getColumns("rentacar", null, tableName, null)) {
            result = new StringBuilder();
            while (rs.next()) {
                result.append(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE") + " | ");
            }
        }
        return result.toString();
    }
}

