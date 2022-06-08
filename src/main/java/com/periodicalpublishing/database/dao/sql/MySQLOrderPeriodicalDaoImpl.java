package com.periodicalpublishing.database.dao.sql;

import com.periodicalpublishing.database.connection.ConnectionPool;
import com.periodicalpublishing.database.dao.interfaces.IOrderPeriodicalDAO;
import com.periodicalpublishing.database.entities.OrderPeriodical;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderPeriodicalDaoImpl implements IOrderPeriodicalDAO {

    public MySQLOrderPeriodicalDaoImpl() {

    }

    private OrderPeriodical getOrderPeriodical(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long periodicalId = resultSet.getLong("periodical_id");

        return new OrderPeriodical(orderId, periodicalId);
    }

    public List<OrderPeriodical> findAll() {
        String query = "SELECT * FROM order_periodical";
        List<OrderPeriodical> orderPeriodicalList = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                OrderPeriodical orderPeriodical = getOrderPeriodical(resultSet);
                orderPeriodicalList.add(orderPeriodical);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return orderPeriodicalList;
    }

    public List<OrderPeriodical> findByOrderId(long order_id) {
        String query = "SELECT * FROM order_periodical WHERE order_id = " + order_id;
        List<OrderPeriodical> orderPeriodicalList = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                OrderPeriodical orderPeriodical = getOrderPeriodical(resultSet);
                orderPeriodicalList.add(orderPeriodical);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return orderPeriodicalList;
    }

    public OrderPeriodical findOne(long order_id, long periodical_id) {
        String query = "SELECT * FROM order_periodical WHERE order_id = " + order_id + " AND periodical_id = " + periodical_id;
        OrderPeriodical orderPeriodical = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                orderPeriodical = getOrderPeriodical(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return orderPeriodical;
    }

    public OrderPeriodical save(OrderPeriodical orderPeriodical) {
        OrderPeriodical newOrderPeriodical = null;
        String query = "CALL insert_order_periodical('" +
                orderPeriodical.getOrderId() + "', '" +
                orderPeriodical.getPeriodicalId()+ "')";

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                newOrderPeriodical = getOrderPeriodical(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newOrderPeriodical;
    }

    public void delete(long order_id, long periodical_id) {
        String query = "DELETE FROM order_periodical WHERE order_id = " + order_id + " AND periodical_id = " + periodical_id;

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
