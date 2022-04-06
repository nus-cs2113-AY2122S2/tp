package arcs.data.mealreservation;

import arcs.data.customer.Customer;
import arcs.data.menuitems.MenuItem;
import arcs.data.route.Route;

import java.util.ArrayList;

public class MealReservation {

    private Customer customer;
    private Route route;

    private ArrayList<MenuItem> reservedMenuItems;

    public MealReservation(Customer customer, Route route, ArrayList<MenuItem> reservedMenuItems) {
        this.customer = customer;
        this.route = route;
        this.reservedMenuItems = reservedMenuItems;
    }

    public MealReservation() {
        reservedMenuItems = new ArrayList<>();
    }

    /**
     * Gets the customer that reserved the meal.
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the Route that the meal was reserved for.
     * @return Route
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Gets all the reserved menu items as a string.
     * @return String of Reserved menu items.
     */
    public String getReservedMenuItemsInfoAsString() {
        StringBuilder builder = new StringBuilder();
        for (MenuItem menuItem: reservedMenuItems) {
            builder.append("[");
            builder.append(menuItem.getMenuItemType());
            builder.append("]");
            builder.append(menuItem.getMenuItemName());
            builder.append("\n");
        }
        return builder.toString();

    }

    /**
     * Gets the meal reservation info as a string.
     * @return string of the meal reservation including flight and passenger details.
     */
    public String getMealReservationInfo() {
        return "Customer name: " + customer.getName() + System.lineSeparator()
                + "Flight ID: " + route.getFlightID() + System.lineSeparator()
                + "In-flight Meal Reserved: " + System.lineSeparator()
                + this.getReservedMenuItemsInfoAsString();

    }

    /**
     * Gets the reserved menu items for storage.
     * @return String of reserved menu items for storing.
     */
    public String getReservedMenuItemsToStoreAsString() {
        StringBuilder builder = new StringBuilder();
        for (MenuItem menuItem: reservedMenuItems) {
            builder.append("/");
            builder.append(menuItem.getMenuItemType());
            builder.append("|");
            builder.append(menuItem.getMenuItemName());
            builder.append("|");
            builder.append(menuItem.getMenuItemPrice());
        }
        return builder.toString();
    }

    /**
     * Gets the Meal Reservation info as a string for storage.
     * @return String of meal reservation info for storage.
     */
    @Override
    public String toString() {
        assert customer != null : "Customer is null.";
        assert route != null : "Route is null";
        return customer.getIc() + "/" + customer.getName() + "/"
                + customer.getPhone() + "/" + customer.getEmail() + "/"
                + route.getFlightID() + "/" + route.getDate() + "/"
                + route.getTime() + "/" + route.getFrom() + "/"
                + route.getTo() + "/" + route.getCapacity() + "/"
                + route.getSold()
                + this.getReservedMenuItemsToStoreAsString();
    }
}
