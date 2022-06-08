package com.periodicalpublishing.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandHelper {
    private static CommandHelper instance = null;
    public HashMap<String, BaseCommand> commandType = initMap();

    private CommandHelper(){}

    public HashMap<String, BaseCommand> initMap() {
        HashMap<String, BaseCommand> map = new HashMap<>();
        map.put("", new MainPageCommand());
        map.put("main", new MainPageCommand());
        map.put("login", new LoginCommand());
        map.put("signup", new SignupCommand());
        map.put("logout", new LogoutCommand());
        map.put("periodicals", new PeriodicalsCommand());
        map.put("createPeriodical", new CreatePeriodicalCommand());
        map.put("deletePeriodical", new DeletePeriodicalCommand());
        map.put("userOrders", new UserOrdersCommand());
        map.put("createOrder", new CreateOrderCommand());
        map.put("deleteOrder", new DeleteOrderCommand());
        map.put("payOrder", new PayOrderCommand());
        map.put("order", new OrderPeriodicalsCommand());
        map.put("editToOrder", new EditOrderCommand());
        map.put("addPeriodicalsToOrder", new AddPeriodicalsToOrderCommand());
        map.put("removePeriodicalFromOrder", new RemovePeriodicalFromOrderCommand());
        map.put("subscriptions", new SubscriptionsCommand());
        map.put("users", new UsersCommand());
        return map;
    }

    public static CommandHelper getInstance() {
        if (instance == null) {
            instance = new CommandHelper();
        }
        return instance;
    }

    public BaseCommand getCommand(HttpServletRequest request){
        BaseCommand command = commandType.get(request.getParameter("action"));

        if(command == null){
            command = new MainPageCommand();
        }
        return command;
    }
}
