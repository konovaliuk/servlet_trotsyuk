package com.periodicalpublishing.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BaseCommand implements ICommand{
    protected String MAIN_PAGE = "main.jsp";
    protected String LOGIN_PAGE = "login.jsp";
    protected String SIGNUP_PAGE = "signup.jsp";
    protected String PERIODICALS_PAGE = "periodicals.jsp";
    protected String USER_ORDERS_PAGE = "userOrders.jsp";
    protected String ORDER_PERIODICALS_PAGE = "orderPeriodicals.jsp";
    protected String SUBSCRIPTIONS_PAGE = "subscriptions.jsp";
    protected String USERS_PAGE = "users.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return LOGIN_PAGE;
    }
}
