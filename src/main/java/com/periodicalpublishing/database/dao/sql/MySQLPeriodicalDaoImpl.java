package com.periodicalpublishing.database.dao.sql;

import com.periodicalpublishing.database.connection.ConnectionPool;
import com.periodicalpublishing.database.dao.interfaces.IPeriodicalDAO;
import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.database.entities.enums.PeriodicityType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLPeriodicalDaoImpl implements IPeriodicalDAO {

    public MySQLPeriodicalDaoImpl() {

    }

    private Periodical getPeriodical(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        PeriodicityType periodicityType = PeriodicityType.valueOf(resultSet.getString("periodicity_type"));
        int periodicityValue = resultSet.getInt("periodicity_value");

        return new Periodical(id, name, price, periodicityType, periodicityValue);
    }

    public List<Periodical> findAll() {
        String query = "SELECT * from periodicals ORDER BY id DESC";
        List<Periodical> periodicalList = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                Periodical periodical = getPeriodical(resultSet);
                periodicalList.add(periodical);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return periodicalList;
    }

    public Periodical findById(long id) {
        String query = "SELECT * FROM periodicals WHERE id = " + id;
        Periodical periodical = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                periodical = getPeriodical(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return periodical;
    }

    public Periodical save(Periodical periodical) {
        Periodical newPeriodical = null;
        String query = "CALL insert_periodical('" +
                periodical.getName() + "', " +
                periodical.getPrice() + ", '" +
                periodical.getPeriodicityType() + "', '" +
                periodical.getPeriodicityValue() + "')";

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                newPeriodical = findById(resultSet.getLong("id"));
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newPeriodical;
    }

    public Periodical update(Periodical periodical) {
        Periodical newPeriodical = null;
        String query = "UPDATE periodicals SET name = '" + periodical.getName()
                + "', price = '" + periodical.getPrice() + "',"
                + "', periodicity_type = '" + periodical.getPeriodicityType()
                + "', periodicity_value = '" + periodical.getPeriodicityValue()
                + "' WHERE id = " + periodical.getId();

        Connection connection;
        Statement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(query);

            newPeriodical = findById(periodical.getId());

            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newPeriodical;
    }

    public void delete(long id) {
        String query = "DELETE FROM periodicals WHERE id = " + id;

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
