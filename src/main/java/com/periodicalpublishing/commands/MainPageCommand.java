package com.periodicalpublishing.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand extends BaseCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return MAIN_PAGE;
    }
}
