package com.periodicalpublishing.database.dao.sql;

import com.periodicalpublishing.database.connection.ConnectionPool;
import com.periodicalpublishing.database.dao.interfaces.ISubscriptionDAO;
import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.database.entities.Subscription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLSubscriptionDaoImpl implements ISubscriptionDAO {

    public MySQLSubscriptionDaoImpl() {

    }

    private Subscription getSubscription(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long userId = resultSet.getLong("user_id");
        Long periodicalId = resultSet.getLong("periodical_id");
        LocalDate dateStart = LocalDate.parse(resultSet.getString("date_start"));
        LocalDate dateEnd = LocalDate.parse(resultSet.getString("date_end"));

        return new Subscription(id, userId, periodicalId, dateStart, dateEnd);
    }

    public List<Subscription> findAll() {
        String query = "SELECT * FROM subscriptions ORDER BY date_end DESC";
        List<Subscription> subscriptionList = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                Subscription subscription = getSubscription(resultSet);
                subscriptionList.add(subscription);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return subscriptionList;
    }

    public Subscription findById(long id) {
        String query = "SELECT * FROM subscriptions WHERE id = " + id;
        Subscription subscription = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                subscription = getSubscription(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return subscription;
    }

    public List<Subscription> findByUserId(long userId) {
        String query = "SELECT * FROM subscriptions WHERE user_id = " + userId + " ORDER BY date_end DESC";
        List<Subscription> subscriptions = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                Subscription subscription = getSubscription(resultSet);
                subscriptions.add(subscription);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    public Subscription findLastByUserAndPeriodical(long userId, long periodicalId) {
        String query = "SELECT * FROM subscriptions WHERE user_id = " + userId + " AND periodical_id = " + periodicalId + " ORDER BY date_end DESC";
        Subscription subscription = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                subscription = getSubscription(resultSet);
                break;
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return subscription;
    }

    public Subscription save(Subscription subscription) {
        Subscription newSubscription = null;
        String query = "CALL insert_subscription('" +
                subscription.getUserId() + "', '" +
                subscription.getPeriodicalId() + "', '" +
                subscription.getDateStart() + "', '" +
                subscription.getDateEnd() + "')";

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                newSubscription = findById(resultSet.getLong("id"));
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newSubscription;
    }

    public Subscription update(Subscription subscription) {
        Subscription newSubscription = null;
        String query = "UPDATE subscriptions SET user_id = '" + subscription.getUserId()
                + "', periodical_id = '" + subscription.getPeriodicalId()
                + "', date_start = '" + subscription.getDateStart()
                + "', date_end = '" + subscription.getDateEnd()
                + "' WHERE id = " + subscription.getId();

        Connection connection;
        Statement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(query);

            newSubscription = findById(subscription.getId());

            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newSubscription;
    }

    public void delete(long id) {
        String query = "DELETE FROM subscriptions WHERE id = " + id;

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
