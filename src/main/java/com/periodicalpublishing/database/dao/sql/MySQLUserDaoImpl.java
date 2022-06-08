package com.periodicalpublishing.database.dao.sql;

import com.periodicalpublishing.database.connection.ConnectionPool;
import com.periodicalpublishing.database.dao.interfaces.IUserDAO;
import com.periodicalpublishing.database.entities.User;
import com.periodicalpublishing.database.entities.enums.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDaoImpl implements IUserDAO {

    public MySQLUserDaoImpl() {

    }

    private User getUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Role role = Role.valueOf(resultSet.getString("role"));

        return new User(id, firstName, lastName, email, password, role);
    }

    public List<User> findAll() {
        String query = "SELECT * FROM users ORDER BY id DESC";
        List<User> userList = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User findById(long id) {
        String query = "SELECT * FROM users WHERE id = " + id;
        User user = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                user = getUser(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = '" + email + "'";
        User user = null;

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                user = getUser(resultSet);
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public User save(User user) {
        User newUser = null;
        String query = "CALL insert_user('" +
                user.getFirstName() + "', '" +
                user.getLastName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() + "', '" +
                user.getRole() + "')";

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                newUser = findById(resultSet.getLong("id"));
            }

            resultSet.close();
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newUser;
    }

    public User update(User user) {
        User newUser = null;
        String query = "UPDATE users SET first_name = '" + user.getFirstName()
                + "', last_name = '" + user.getLastName()
                + "', email = '" + user.getEmail()
                + "', password = '" + user.getPassword()
                + "', role = '" + user.getRole()
                + "' WHERE id = " + user.getId();

        Connection connection;
        Statement statement;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(query);

            newUser = findById(user.getId());

            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return newUser;
    }

    public void delete(long id) {
        String query = "DELETE FROM users WHERE id = " + id;

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
