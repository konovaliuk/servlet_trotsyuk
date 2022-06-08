package com.periodicalpublishing.database.connection;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance = null;

    private List<Connection> availableConnections = new ArrayList<Connection>();
    private List<Connection> usedConnections = new ArrayList<Connection>();
    private int MAX_CONNECTIONS = 2;

    private ConnectionPool() throws SQLException {
        for(int i = 0; i < MAX_CONNECTIONS; i++) {
            availableConnections.add(ConnectionFactory.getConnection());
        }
    }

    private void increaseConnectionNum() throws SQLException {
        for(int i = 0; i < MAX_CONNECTIONS; i++) {
            availableConnections.add(ConnectionFactory.getConnection());
        }
        MAX_CONNECTIONS = MAX_CONNECTIONS * 2;
    }

    private void decreaseConnectionNum() throws SQLException {
        for(int i = 0; i < MAX_CONNECTIONS / 2; i++) {
            Connection connection = availableConnections.remove(availableConnections.size() - 1);
            if(connection != null) {
                connection.close();
            }
        }
        MAX_CONNECTIONS = MAX_CONNECTIONS / 2;
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Connection connection = availableConnections.remove(availableConnections.size() - 1);
        usedConnections.add(connection);
        if (usedConnections.size() > MAX_CONNECTIONS / 2) {
            try {
                increaseConnectionNum();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if(connection != null) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
            if(usedConnections.size() < MAX_CONNECTIONS / 4) {
                try {
                    decreaseConnectionNum();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getAvailableConnectionsCount() {
        return availableConnections.size();
    }
}
