package ARCS.data;

import java.util.ArrayList;

public class RouteManager {
    private ArrayList<Route> routes;

    public RouteManager() {
        routes = new ArrayList<>();
    }

    public void addRoute(Route newRoute) {
        routes.add(newRoute);
    }

    public ArrayList<Route> getAllRoutes() {
        return routes;
    }

    public Route deleteRoute(int index) {
        Route deleted = routes.get(index - 1);
        routes.remove(index - 1);
        return deleted;
    }
}
