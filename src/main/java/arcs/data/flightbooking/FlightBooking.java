package arcs.data.flightbooking;


import arcs.data.customer.Customer;
import arcs.data.route.Route;

public class FlightBooking {
    private Customer customer;
    private Route route;

    public FlightBooking(Customer customer, Route route) {
        this.customer = customer;
        this.route = route;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Route getRoute() {
        return route;
    }

    public String getFlightBookingInfo() {
        String info = "Customer name: " + customer.getName() + System.lineSeparator()
                + "Customer IC: " + customer.getIc() + System.lineSeparator()
                + "Flight ID: " + route.getFlightID() + System.lineSeparator()
                + "From: " + route.getFrom() + "\t To: " + route.getTo() + System.lineSeparator()
                + "Date: " + route.getDate() + "\t Time: " + route.getTime();
        return info;
    }
}
