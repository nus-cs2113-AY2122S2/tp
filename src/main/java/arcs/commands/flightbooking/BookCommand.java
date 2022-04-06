package arcs.commands.flightbooking;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.route.Route;

import java.util.ArrayList;

public class BookCommand extends Command {
    public static final String COMMAND_WORD = "book";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";
    private static final String INVALID_IC_MESSAGE = "The IC is invalid";
    private static final String NO_CUSTOMER_MESSAGE = "This customer has not been registered in the system."
            + System.lineSeparator() + "Please add this customer before booking a flight.";
    private static final String NO_ROUTE_MESSAGE = "No flight with this flight ID is found.";
    private static final String SUCCESS_MESSAGE = "The flight is successfully booked for this customer:";

    private String ic;
    private String fid;
    private ArrayList<String> emptyFields = new ArrayList<>();

    public BookCommand(String ic, String fid) {
        this.ic = ic;
        this.fid = fid;
        checkEmptyField();
    }

    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }
        // Find the customer
        Customer customer;
        try {
            customer = customerManager.findCustomer(ic);
            if (customer == null) {
                return new CommandResult(NO_CUSTOMER_MESSAGE);
            }
        } catch (ArcsException e) {
            return new CommandResult(INVALID_IC_MESSAGE);
        }
        // Find the route
        Route route;
        route = routeManager.findRoute(fid);
        if (route == null) {
            return new CommandResult(NO_ROUTE_MESSAGE);
        }
        // Book the flight
        try {
            flightBookingManager.bookFlight(customer, route);
        } catch (ArcsException e) {
            return new CommandResult(e.getMessage());
        }

        return new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + "Customer:" + System.lineSeparator() + customer.getCustomerInfo()
                + System.lineSeparator() + "Flight:" + System.lineSeparator() + route.getFlightInfo());
    }

    /**
     * Checks whether the input fields are null or empty.
     */
    public void checkEmptyField() {
        if (ic == null || ic.isEmpty()) {
            emptyFields.add("IC");
        }
        if (fid == null || fid.isEmpty()) {
            emptyFields.add("Flight ID");
        }
    }

}
