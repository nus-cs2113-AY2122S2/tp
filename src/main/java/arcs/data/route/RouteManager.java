package arcs.data.route;

import arcs.data.exception.ArcsException;
import arcs.data.exception.DuplicateDataException;

import java.util.ArrayList;

public class RouteManager {
    private ArrayList<Route> routes;

    public RouteManager() {
        routes = new ArrayList<>();
    }

    public RouteManager(ArrayList<Route> routes) {
        this.routes = routes;
    }

    /**
     * Adds a new route into the list of routes.
     *
     * @param newRoute the route to be added.
     * @throws DuplicateDataException if the flight ID of the new route already exists.
     */
    public void addRoute(Route newRoute) throws DuplicateDataException {
        boolean hasDuplicateId = hasDuplicateFlightId(newRoute);
        if (hasDuplicateId) {
            throw new DuplicateDataException("The flight ID is already occupied.");
        }

        routes.add(newRoute);
    }

    public ArrayList<Route> getAllRoutes() {
        return routes;
    }

    /**
     * Deletes a route at the specified index.
     *
     * @param index the position of the route in the list of routes
     * @return the deleted route
     * @throws ArcsException if the index is equal to or less than 0,
     *      or greater than the total number of routes.
     */
    public Route deleteRoute(int index) throws ArcsException {
        assert routes != null : "Routes is null";
        if (index <= 0 || index > routes.size()) {
            throw new ArcsException("Index out of bound.");
        }
        Route deleted = routes.get(index - 1);
        routes.remove(index - 1);
        return deleted;
    }

    /**
     * Finds a route by the date, source and destination.
     *
     * @param date the flight date
     * @param to the flight destination
     * @param from the flight source
     * @return the list of flight routes satisfying the condition.
     */
    public ArrayList<Route> findRoute(String date, String to, String from) {
        ArrayList<Route> result = new ArrayList<>();
        for (Route route: routes) {
            Boolean dateMatch = route.getDate().equals(date);
            Boolean toMatch = route.getTo().equals(to);
            Boolean fromMatch = route.getFrom().equals(from);
            if (dateMatch && toMatch && fromMatch) {
                result.add(route);
            }
        }
        return result;
    }

    /**
     * Finds a route by the date, source and destination.
     *
     * @param date the flight date
     * @param to teh flight destination
     * @param from the flight source
     * @param time the flight time
     * @return the list of flight routes satisfying the condition.
     */
    public ArrayList<Route> findRoute(String date, String to, String from, String time) {
        ArrayList<Route> result = new ArrayList<>();
        for (Route route: routes) {
            Boolean dateMatch = route.getDate().equals(date);
            Boolean toMatch = route.getTo().equals(to);
            Boolean fromMatch = route.getFrom().equals(from);
            Boolean timeMatch = route.getTime().equals(time);
            if (dateMatch && toMatch && fromMatch && timeMatch) {
                result.add(route);
            }
        }
        return result;
    }

    /**
     * Finds the route by flight ID.
     *
     * @param fid the flight ID to be searched
     * @return the route with the specified ID, null if no result is found.
     */
    public Route findRoute(String fid) {
        for (Route route: routes) {
            if (route.getFlightID().equals(fid)) {
                return route;
            }
        }
        return null;
    }

    /**
     * Checks whether the flight ID already exists.
     * @param newRoute the new route
     * @return true if the flight ID already exists.
     */
    public boolean hasDuplicateFlightId(Route newRoute) {
        String newId = newRoute.getFlightID();
        for (Route route: routes) {
            if (route.getFlightID().equals(newId)) {
                return true;
            }
        }
        return false;
    }

}
