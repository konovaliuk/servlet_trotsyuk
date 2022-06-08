package com.periodicalpublishing.database.dao.interfaces;

import com.periodicalpublishing.database.entities.Periodical;

import java.util.List;

public interface IPeriodicalDAO {
    List<Periodical> findAll();
    Periodical findById(long id);
    Periodical save(Periodical periodical);
    Periodical update(Periodical periodical);
    void delete(long id);
}
