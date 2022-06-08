package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.services.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PeriodicalsCommand extends BaseCommand{
    private final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Periodical> periodicals = periodicalService.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("periodicals", periodicals);
        return PERIODICALS_PAGE;
    }
}
