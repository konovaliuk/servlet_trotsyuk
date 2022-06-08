package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.User;
import com.periodicalpublishing.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersCommand extends BaseCommand{
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
        return USERS_PAGE;
    }
}