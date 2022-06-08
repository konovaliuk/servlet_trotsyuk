package com.periodicalpublishing.services;

import com.periodicalpublishing.database.dao.DAOFactory;
import com.periodicalpublishing.database.dao.interfaces.IUserDAO;
import com.periodicalpublishing.database.entities.User;
import com.periodicalpublishing.database.entities.enums.Role;

import java.util.List;

public class UserService {
    private static final IUserDAO userDAO = DAOFactory.getUserDAO();

    public List<User> getAll() {
        return userDAO.findAll();
    }

    public User getById(long id) {
        return userDAO.findById(id);
    }

    public Boolean isUserExist(String email) {
        return userDAO.findByEmail(email) != null;
    }

    public Boolean isAdmin(long id) {
        return userDAO.findById(id).getRole() == Role.ADMIN;
    }

    public User addUser(String firstName, String lastName, String email, String password) {
        User newUser = new User(null, firstName, lastName, email, password, Role.READER);
        return userDAO.save(newUser);
    }

    public User getUser(String email, String password) {
        User user = userDAO.findByEmail(email);
        if(user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }
}
