package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.Periodical;
import com.periodicalpublishing.database.entities.enums.PeriodicityType;
import com.periodicalpublishing.services.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreatePeriodicalCommand extends BaseCommand{
    private final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        Double price;
        try {
            price = Double.valueOf(request.getParameter("price"));
            if(price <= 0) {
                session.setAttribute("errorMessage", "Wrong value for price");
                return PERIODICALS_PAGE;
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Wrong value for price");
            return PERIODICALS_PAGE;
        }
        int periodicityValue;
        try {
            periodicityValue = Integer.parseInt(request.getParameter("periodicityValue"));
            if(periodicityValue <= 0) {
                session.setAttribute("errorMessage", "Wrong value for price");
                return PERIODICALS_PAGE;
            }
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Wrong value for periodicityValue");
            return PERIODICALS_PAGE;
        }
        PeriodicityType periodicityType = PeriodicityType.valueOf(request.getParameter("periodicityType"));
        Periodical newPeriodical = periodicalService.addPeriodical(name, price, periodicityType, periodicityValue);

        if (newPeriodical == null) {
            session.setAttribute("errorMessage", "Сould not add the periodical, please try again");
        } else {
            List<Periodical> periodicals = periodicalService.getAll();
            session.setAttribute("periodicals", periodicals);
            session.setAttribute("errorMessage", null);
        }
        return PERIODICALS_PAGE;
    }
}
