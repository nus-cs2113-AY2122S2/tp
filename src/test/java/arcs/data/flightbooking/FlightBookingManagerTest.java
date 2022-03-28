package arcs.data.flightbooking;

import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.route.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlightBookingManagerTest {
    @Test
    public void bookFlight_timeClash_ExceptionThrown() {
        FlightBookingManager flightBookingManager = new FlightBookingManager();
        Customer customer = new Customer("E1234567A", "Alice White",
                "09298123", "alice988@gmail.com");
        Route route1 = new Route("ABC12", "2021-07-01", "13:00", "Seoul",
                "SG", 120);
        Route route2 = new Route("DEF43", "2021-07-01", "13:00", "Tokyo",
                "Canada", 90);

        try {
            flightBookingManager.bookFlight(customer, route1);
        } catch (ArcsException e) {
            System.out.println(e.getMessage());
        }

        ArcsException thrown = Assertions
                .assertThrows(ArcsException.class, () -> {
                    flightBookingManager.bookFlight(customer, route2);
                }, "ArcsException error was expected.");
        Assertions.assertEquals("Sorry! " + "There is time clash of this customer. "
                + "The flight cannot be booked for this customer.", thrown.getMessage());
    }

    @Test
    public void bookFlight_soldOut_ExceptionThrown() {
        FlightBookingManager flightBookingManager = new FlightBookingManager();
        Customer customer1 = new Customer("E1234567A", "Alice White",
                "09298123", "alice988@gmail.com");
        Customer customer2 = new Customer("Z9876372B", "Luke Brown",
                "12987654", "brown1238@gmail.com");
        Route route = new Route("ABC12", "2021-07-01", "13:00", "Seoul",
                "SG", 1);

        try {
            flightBookingManager.bookFlight(customer1, route);
        } catch (ArcsException e) {
            System.out.println(e.getMessage());
        }

        ArcsException thrown = Assertions
                .assertThrows(ArcsException.class, () -> {
                    flightBookingManager.bookFlight(customer2, route);
                }, "ArcsException error was expected.");
        Assertions.assertEquals("Sorry! There is no empty seat for this flight.", thrown.getMessage());
    }
}
