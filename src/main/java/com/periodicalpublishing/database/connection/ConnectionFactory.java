package com.periodicalpublishing.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return  MySQLConnector.getInstance().getConnection();
    }
}
