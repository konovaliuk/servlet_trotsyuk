package com.periodicalpublishing.database.dao.sql;

import com.periodicalpublishing.database.connection.ConnectionPool;
import com.periodicalpublishing.database.dao.interfaces.IOrderDAO;
import com.periodicalpublishing.database.entities.Order;
import com.periodicalpublishing.database.entities.enums.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDaoImpl implements IOrderDAO {

    public MySQLOrderDaoImpl() {

    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("user_id");
        LocalDate creationDate = LocalDate.parse(resultSet.getString("creation_date"));
        Status status = Status.valueOf(resultSet.getString("status"));

        return new Order(id, userId, creationDate, status);
    }

    public List<Order> findAll() {
        String query = "SELECT * FROM orders ORDER BY id DESC";
        List<Order> orderList = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                Order order = getOrder(resultSet);
                orderList.add(order);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public Order findById(long id) {
        String query = "SELECT * FROM orders WHERE id = " + id;
        Order order = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                order = getOrder(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<Order> findByUserId(long userId) {
        String query = "SELECT * FROM orders WHERE user_id = " + userId + " ORDER BY id DESC";
        List<Order> orders = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                orders.add(getOrder(resultSet));
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order save(Order order) {
        Order newOrder = null;
        String query = "CALL insert_order('" +
                order.getUserId() + "', '" +
                order.getCreationDate() + "', '" +
                order.getStatus() + "')";

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                newOrder = findById(resultSet.getLong("id"));
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newOrder;
    }

    public Order update(Order order) {
        Order newOrder = null;
        String query = "UPDATE orders SET user_id = '" + order.getUserId()
                + "', creation_date = '" + order.getCreationDate()
                + "', status = '" + order.getStatus()
                + "' WHERE id = " + order.getId();

        Connection connection;
        Statement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(query);

            newOrder = findById(order.getId());

            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newOrder;
    }


    public void delete(long id) {
        String query = "DELETE FROM orders WHERE id = " + id;

        Connection connection;
        Statement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
