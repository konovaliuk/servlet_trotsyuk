package com.periodicalpublishing.commands;

import com.periodicalpublishing.database.entities.User;
import com.periodicalpublishing.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignupCommand extends BaseCommand{
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");

        HttpSession session = request.getSession();
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("email", email);
        if(userService.isUserExist(email)) {
            session.setAttribute("errorMessageSignup", "User already exists!");
            return SIGNUP_PAGE;
        } else if (!password.equals(passwordRepeat)) {
            session.setAttribute("errorMessageSignup", "Passwords do not match!");
            return SIGNUP_PAGE;
        } else {
            User user = userService.addUser(firstName, lastName, email, password);
            session.setAttribute("userId", user.getId());
            session.setAttribute("isAdmin", userService.isAdmin(user.getId()));
            session.setAttribute("errorMessageSignup", null);
            return MAIN_PAGE;
        }
    }
}
