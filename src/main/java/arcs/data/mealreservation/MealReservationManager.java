package arcs.data.mealreservation;

import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.menuitems.MenuItem;
import arcs.data.menuitems.MenuItemManager;
import arcs.data.route.Route;

import java.util.ArrayList;

public class MealReservationManager {

    private static final String INVALID_MENU_ITEM_MESSAGE = "This Menu Item does not exist. "
            + "Please ensure you have entered the correct name and type. ";

    private static final String NO_FLIGHT_BOOKED_MESSAGE = "You have to book a seat on this flight, "
            + "before you can reserve a meal.";

    //array list of ALL reserved items by each customer
    //within it each customer itself has a list of menu items reserved.
    private ArrayList<MealReservation> mealReservations;

    private MenuItemManager menuItemManager;
    private FlightBookingManager flightBookingManager;


    public MealReservationManager(ArrayList<MealReservation> mealReservations,
            FlightBookingManager flightBookingManager) {
        this.mealReservations = mealReservations;
        this.flightBookingManager = flightBookingManager;
    }

    public MealReservationManager() {
        mealReservations = new ArrayList<>();
    }

    public ArrayList<MealReservation> getAllMealReservations() {
        return mealReservations;
    }

    public void reserveMeal(Customer customer, Route route, ArrayList<MenuItem> reservedMenuItems) throws ArcsException {
        if(!flightBookingManager.checkFlightBookingValidity(customer.getIc(),route.getFlightID())) {
            throw new ArcsException(NO_FLIGHT_BOOKED_MESSAGE);
        }
        mealReservations.add(new MealReservation(customer,route,reservedMenuItems));
    }

    //check validity of all reserved items, if all valid return true.
    /*private boolean reservedItemsValidity(ArrayList<MenuItem> reservedMenuItems) {
        for (MenuItem reservedItem: reservedMenuItems) {
            //check whether item to be reserved is valid
            boolean isValidMenuItem = menuItemManager.menuItemChecker(reservedItem);
            if (!isValidMenuItem) {
                return false;
            }
        }
        return true;
    }*/

}
