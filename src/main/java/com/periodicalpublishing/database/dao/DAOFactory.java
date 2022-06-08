package com.periodicalpublishing.database.dao;

import com.periodicalpublishing.database.dao.interfaces.*;
import com.periodicalpublishing.database.dao.sql.*;

public class DAOFactory {

    public static IOrderDAO getOrderDAO() {
        return new MySQLOrderDaoImpl();
    }

    public static IOrderPeriodicalDAO getOrderPeriodicalDAO() {
        return new MySQLOrderPeriodicalDaoImpl();
    }

    public static IPeriodicalDAO getPeriodicalDAO() {
        return new MySQLPeriodicalDaoImpl();
    }

    public static ISubscriptionDAO getSubscriptionDAO() {
        return new MySQLSubscriptionDaoImpl();
    }

    public static IUserDAO getUserDAO() {
        return new MySQLUserDaoImpl();
    }
}
