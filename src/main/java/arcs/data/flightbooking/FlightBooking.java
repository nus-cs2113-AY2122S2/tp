package arcs.data.flightbooking;


import arcs.data.customer.Customer;
import arcs.data.mealreservation.MealReservation;
import arcs.data.route.Route;


public class FlightBooking {
    private Customer customer;
    private Route route;

    public FlightBooking(Customer customer, Route route) {
        this.customer = customer;
        this.route = route;
    }

    public FlightBooking(Customer customer, Route route, MealReservation mealReservation) {
        this.customer = customer;
        this.route = route;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Route getRoute() {
        return route;
    }

    /**
     * Gets information of a flight.
     * @return a string containing the description of the flight booking.
     */
    public String getFlightBookingInfo() {
        String info = "Customer name: " + customer.getName() + System.lineSeparator()
                + "Customer IC: " + customer.getIc() + System.lineSeparator()
                + "Flight ID: " + route.getFlightID() + System.lineSeparator()
                + "From: " + route.getFrom() + "\t To: " + route.getTo() + System.lineSeparator()
                + "Date: " + route.getDate() + "\t Time: " + route.getTime();
        return info;
    }

    @Override
    public String toString() {
        assert customer != null : "Customer is null.";
        assert route != null : "Route is null.";
        String str = customer.getIc() + "/" + customer.getName() + "/"
                + customer.getPhone() + "/" + customer.getEmail() + "/"
                + route.getFlightID() + "/" + route.getDate() + "/"
                + route.getTime() + "/" + route.getFrom() + "/"
                + route.getTo() + "/" + route.getCapacity() + "/"
                + route.getSold();
        return str;
    }
}
