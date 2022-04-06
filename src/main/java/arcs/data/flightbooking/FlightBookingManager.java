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

    /**
     * Gets to flight bookings.
     * @return an array list of all flight bookings
     */
    public ArrayList<FlightBooking> getAllFlightBookings() {
        return flightBookings;
    }

    /**
     * Adds a new flight booking.
     * @param customer the customer who takes the flight
     * @param route the flight route
     * @throws ArcsException if the route is sold out or when the customer has time clash.
     */
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

    /**
     * Deletes a flight booking.
     *
     * @param index the index of the flight booking to be deleted
     * @return the deleted flight booking
     * @throws ArcsException if the index is equal to or less than 0, or greater than current list size.
     */
    public FlightBooking deleteBooking(int index) throws ArcsException {
        assert flightBookings != null : "Flight booking list is null.";
        if (index <= 0 || index > flightBookings.size()) {
            throw new ArcsException("Index out of bound");
        }
        FlightBooking deleted = flightBookings.get(index - 1);
        flightBookings.remove(index - 1);
        Route route = deleted.getRoute();
        route.decrementSold();
        return deleted;
    }

    /**
     * Checks whether a customer has a time clash.
     * The customer has a time clash if he/she already has a flight taking off at the same date and time.
     *
     * @param ic IC of the customer
     * @param date flight date
     * @param time flight time
     * @return true if the customer has a time clash
     */
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

    /**
     * Checks whether the flight has empty seats.
     *
     * @param route the flight route
     * @return true if the flight has empty sears.
     */
    public boolean hasEmptySeats(Route route) {
        return route.getEmptySeats() > 0;
    }

    public boolean checkFlightBookingValidity(String ic, String fid) {
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
