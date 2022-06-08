package com.periodicalpublishing.database.connection;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConnector {
    private static MySQLConnector instance = null;

    private MySQLConnector() {

    }

    public static MySQLConnector getInstance() {
        if (instance == null)
            instance = new MySQLConnector();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String server_name = resource.getString("server_name");
        String database_name = resource.getString("database_name");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        Connection connection = null;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName(server_name);
            dataSource.setDatabaseName(database_name);
            dataSource.setUser(user);
            dataSource.setPassword(pass);

            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
