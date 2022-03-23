package arcs.data;
import arcs.data.exception.DuplicateDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteManagerTest {
    @Test
    public void addRoute_duplicateFlights_exceptionThrown() {
        RouteManager routeManager = new RouteManager();
        Route newRoute = new Route("ABC12", "2021-04-02", "14:00", "Canada", "SG", 130);
        Route duplicateRoute = new Route("ABC12", "2022-03-12", "9:00", "Beijing", "SG", 100);
        try {
            routeManager.addRoute(newRoute);
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        }

        DuplicateDataException thrown = Assertions
                .assertThrows(DuplicateDataException.class, ()->{
                    routeManager.addRoute(duplicateRoute);
                }, "DuplicateDataException error was expected");
        Assertions.assertEquals("Sorry! The flight ID is already occupied.", thrown.getMessage());
    }

    @Test
    public void findRoute() {
        Route route1 = new Route("ABC12", "2021-04-02", "14:00", "Canada", "SG", 130);
        Route route2 = new Route("DE019", "2021-04-02", "05:00", "Canada", "SG", 200);
        Route route3 = new Route("XYZ412", "2021-04-02", "19:00", "Tokyo", "Beijing", 150);
        RouteManager routeManager = new RouteManager();
        try {
            routeManager.addRoute(route1);
            routeManager.addRoute(route2);
            routeManager.addRoute(route3);
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Route> expectedRoutes = new ArrayList<>();
        expectedRoutes.add(route1);
        expectedRoutes.add(route2);
        assertEquals(expectedRoutes, routeManager.findRoute("2021-04-02", "SG", "Canada"));

    }

}
