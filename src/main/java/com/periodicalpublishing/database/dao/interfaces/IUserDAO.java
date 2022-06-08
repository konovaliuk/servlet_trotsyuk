package com.periodicalpublishing.database.dao.interfaces;

import com.periodicalpublishing.database.entities.User;

import java.util.List;

public interface IUserDAO {
    List<User> findAll();
    User findById(long id);
    User findByEmail(String email);
    User save(User user);
    User update(User user);
    void delete(long id);
}
