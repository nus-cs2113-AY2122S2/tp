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
}
