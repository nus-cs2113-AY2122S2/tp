package arcs.data.flightbooking;

import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.route.Route;

import java.util.ArrayList;

public class FlightBookingManager {
    private ArrayList<FlightBooking> flightBookings;
    private static final String TIME_CLASH_MESSAGE = "There is time clash of this customer. "
            + "The flight cannot be booked for this customer.";
    private static final String SOLD_OUT_MESSAGE = "There is no empty seat for this flight.";

    public FlightBookingManager() {
        flightBookings = new ArrayList<>();
    }

    public FlightBookingManager(ArrayList<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public ArrayList<FlightBooking> getAllFlightBookings() {
        return flightBookings;
    }

    public void bookFlight(Customer customer, Route route) throws ArcsException {
        if (!hasEmptySeats(route)) {
            throw new ArcsException(SOLD_OUT_MESSAGE);
        }
        if (hasTimeClash(customer.getIc(), route.getDate(), route.getTime())) {
            throw new ArcsException(TIME_CLASH_MESSAGE);
        }
        flightBookings.add(new FlightBooking(customer, route));
        route.incrementSold();
    }

    public FlightBooking deleteBooking(int index) throws ArcsException {
        assert flightBookings != null : "Flight booking list is null.";
        if (index <= 0 || index > flightBookings.size()) {
            throw new ArcsException("Index out of bound");
        }
        FlightBooking deleted = flightBookings.get(index - 1);
        flightBookings.remove(index - 1);
        return deleted;
    }

    public boolean hasTimeClash(String ic, String date, String time) {
        for (FlightBooking flightBooking: flightBookings) {
            Customer customer = flightBooking.getCustomer();
            Route route = flightBooking.getRoute();
            if (customer.getIc().equals(ic) && route.getDate().equals(date)
                && route.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEmptySeats(Route route) {
        return route.getEmptySeats() > 0;
    }

    public boolean checkFlightBookingValidity (String ic, String fid) {
        for (FlightBooking flightBooking: flightBookings) {
            Customer customer = flightBooking.getCustomer();
            Route route = flightBooking.getRoute();
            if (customer.getIc().equals(ic) && route.getFlightID().equals(fid)) {
                return true;
            }
        }
        return false;
    }

}
