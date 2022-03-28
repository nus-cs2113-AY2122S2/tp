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
}
