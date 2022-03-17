package arcs.commands;

import arcs.data.Route;

import java.util.ArrayList;

public class FindRouteCommand extends Command {
    public static final String COMMAND_WORD = "findRoute";
    private String date;
    private String to;
    private String from;
    private String time;
    private ArrayList<String> emptyFields = new ArrayList<>();
    private static final String FOUND_MESSAGE = "The following flight routes has been found:";
    private static final String EMPTY_MESSAGE = "No flight route found.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";

    public FindRouteCommand(String date, String to, String from, String time) {
        this.date = date;
        this.to = to;
        this.from = from;
        this.time = time;
        checkEmptyField();
    }

    private void checkEmptyField() {
        if (date == null || date.isEmpty()) {
            emptyFields.add("Flight date");
        }
        if (to == null || to.isEmpty()) {
            emptyFields.add("Destination");
        }
        if (from == null || from.isEmpty()) {
            emptyFields.add("Source");
        }
    }

    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }

        ArrayList<Route> result;
        ArrayList<String> routesInfo = new ArrayList<>();
        if (time == null) {
            result = routeManager.findRoute(date, to, from);
        } else {
            result = routeManager.findRoute(date, to, from, time);
        }

        for (Route route: result) {
            routesInfo.add(route.getFlightInfo());
        }

        CommandResult commandResult;
        if (routesInfo.isEmpty()) {
            commandResult = new CommandResult(EMPTY_MESSAGE);
        } else {
            commandResult = new CommandResult(FOUND_MESSAGE, routesInfo);
        }

        return commandResult;
    }
}
