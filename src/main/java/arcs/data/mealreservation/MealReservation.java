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

    public Customer getCustomer() {
        return customer;
    }

    public Route getRoute() {
        return route;
    }

    /*public ArrayList<MenuItem> getReservedMenuItems() {
        return reservedMenuItems;
    }*/

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

    public String getMealReservationInfo() {
        return "Customer name: " + customer.getName() + System.lineSeparator()
                + "Flight ID: " + route.getFlightID() + System.lineSeparator()
                + "In-flight Meal Reserved: " + System.lineSeparator()
                + this.getReservedMenuItemsInfoAsString();

    }

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
