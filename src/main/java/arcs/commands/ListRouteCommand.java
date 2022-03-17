package arcs.commands;

import arcs.data.Route;

import java.util.ArrayList;

public class ListRouteCommand extends Command{
    public static final String COMMAND_WORD = "listRoute";
    private static final String FEEDBACK = "Existing routes:";

    @Override
    public CommandResult execute() {
        ArrayList<Route> routes = routeManager.getAllRoutes();
        ArrayList<String> routesInfo = new ArrayList<>();
        for (Route route: routes) {
            routesInfo.add(route.getFlightInfo());
        }
        return new CommandResult(FEEDBACK, routesInfo);
    }
}
