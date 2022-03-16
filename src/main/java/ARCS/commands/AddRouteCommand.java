package ARCS.commands;

import ARCS.data.Route;
import ARCS.data.RouteManager;

public class AddRouteCommand extends Command{
    public static final String COMMAND_WORD = "addRoute";
    private Route toAdd;
    private final String SUCCESS_MESSAGE = "OK! The following new route is added: ";
    private final String DUPLICATE_MESSAGE = "Sorry! The flight ID already exits. This flight cannot be added.";

    public AddRouteCommand(RouteManager routeManager,
                           int flightID, String date, String time,
                           String from, String to, int capacity) {
        this.routeManager = routeManager;
        this.toAdd = new Route(flightID, date, time, from, to, capacity);
    }

    @Override
    public CommandResult execute() {
        routeManager.addRoute(toAdd);
        CommandResult result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + toAdd.getFlightInfo());
        return result;
    }
}
