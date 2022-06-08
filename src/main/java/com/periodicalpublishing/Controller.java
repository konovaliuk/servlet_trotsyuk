package com.periodicalpublishing;

import com.periodicalpublishing.commands.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    public Controller() {
        super();
    }

    CommandHelper commandHelper = CommandHelper.getInstance();
    @Override
    public void init() {
        commandHelper = CommandHelper.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            BaseCommand command = commandHelper.getCommand(request);
            var com = command.execute(request, response);

            RequestDispatcher rd = request.getRequestDispatcher(com);
            rd.forward(request, response);
        } catch (IOException | ServletException throwables) {
            throwables.printStackTrace();
        }
    }
}
