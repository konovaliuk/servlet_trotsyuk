package com.periodicalpublishing.services;

import com.periodicalpublishing.database.dao.DAOFactory;
import com.periodicalpublishing.database.dao.interfaces.IPeriodicalDAO;
import com.periodicalpublishing.database.entities.OrderPeriodical;
import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.database.entities.enums.PeriodicityType;

import java.util.ArrayList;
import java.util.List;

public class PeriodicalService {
    private static final IPeriodicalDAO periodicalDAO = DAOFactory.getPeriodicalDAO();

    public List<Periodical> getAll() {
        return periodicalDAO.findAll();
    }

    public List<Periodical> getByOrderId(long orderId) {
        List<OrderPeriodical> orderPeriodicals = DAOFactory.getOrderPeriodicalDAO().findByOrderId(orderId);
        List<Periodical> periodicals = new ArrayList<>();
        if(orderPeriodicals == null)
            return periodicals;
        for(OrderPeriodical orderPeriodical : orderPeriodicals) {
            periodicals.add(periodicalDAO.findById(orderPeriodical.getPeriodicalId()));
        }
        return periodicals;
    }

    public Periodical addPeriodical(String name, Double price, PeriodicityType periodicityType, int periodicityValue) {
        Periodical newPeriodical = new Periodical(null, name, price, periodicityType, periodicityValue);
        return periodicalDAO.save(newPeriodical);
    }

    public void removePeriodical(long id) {
        periodicalDAO.delete(id);
    }
}
