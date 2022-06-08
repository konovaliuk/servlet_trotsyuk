package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.User;
import com.periodicalpublishing.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends BaseCommand{
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.getUser(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        if(user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("isAdmin", userService.isAdmin(user.getId()));
            session.setAttribute("errorMessageLogin", null);
            return MAIN_PAGE;
        } else {
            session.setAttribute("errorMessageLogin", "Invalid login or password!");
            return LOGIN_PAGE;
        }
    }
}
