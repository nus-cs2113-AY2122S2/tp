package arcs.commands.mealreservation;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.menuitems.MenuItem;
import arcs.data.route.Route;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMealReservationCommand extends Command {

    public static final String COMMAND_WORD = "reserveMeal";

    /**
     * Declarations for Messages for the User.
     */
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";
    private static final String NO_CUSTOMER_MESSAGE = "This customer has not been registered in the system."
            + System.lineSeparator() + "Please add this customer before booking a flight.";
    private static final String INVALID_IC_MESSAGE = "The IC is invalid";
    private static final String NO_ROUTE_MESSAGE = "No flight with this flight ID is found.";
    private static final String NO_MEALS_RESERVED_MESSAGE = "You must reserve at least 1 meal.";
    private static final String SUCCESS_MESSAGE = "Meal successfully reserved for: ";

    private String ic;
    private String fid;
    private HashMap<String, String> mealsReserved;
    private ArrayList<String> emptyFields = new ArrayList<>();

    public AddMealReservationCommand(String ic, String fid,
            HashMap<String, String> mealsReserved) {
        this.ic = ic;
        this.fid = fid;
        this.mealsReserved = mealsReserved;
        checkEmptyField();
    }

    /**
     * Executes the command to list of menu items.
     *
     * @return CommandResult result of the executed command.
     */
    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }
        //Find the customer
        Customer customer;
        try {
            customer = customerManager.findCustomer(ic);
            if (customer == null) {
                return new CommandResult(NO_CUSTOMER_MESSAGE);
            }
        } catch (ArcsException e) {
            return new CommandResult(INVALID_IC_MESSAGE);
        }
        //Find the route
        Route route;
        route = routeManager.findRoute(fid);
        if (route == null) {
            return new CommandResult(NO_ROUTE_MESSAGE);
        }
        //menu items to be reserved
        ArrayList<MenuItem> menuItemsToReserve = new ArrayList<>();
        for (String menuItemType : mealsReserved.keySet()) {
            MenuItem menuItem = menuItemManager.getMenuItemByNameAndType(menuItemType,mealsReserved.get(menuItemType));
            if (menuItem != null) {
                menuItemsToReserve.add(menuItem);
            } else if (menuItem == null) {
                System.out.println("There was an invalid Menu Item, only the valid ones were reserved");
            }
        }
        if (menuItemsToReserve.isEmpty()) {
            return new CommandResult(NO_MEALS_RESERVED_MESSAGE);
        }
        try {
            mealReservationManager.reserveMeal(customer, route, menuItemsToReserve);
        } catch (ArcsException e) {
            return new CommandResult(e.getMessage());
        } catch (NullPointerException e) {
            return new CommandResult(e.getMessage());
        }
        return new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + "Customer: " + customer.getName() + System.lineSeparator()
                + "Flight: " + route.getFlightID() + System.lineSeparator()
                + "Items: " + menuItemsToReserve);
    }

    /**
     * Stores empty field in an array for empty fields.
     */
    public void checkEmptyField() {
        if (ic == null || ic.isEmpty()) {
            emptyFields.add("IC");
        }
        if (fid == null || fid.isEmpty()) {
            emptyFields.add("Flight ID");
        }

        if (mealsReserved == null || mealsReserved.isEmpty()) {
            emptyFields.add("Meals Reserved");
        }
    }
}
