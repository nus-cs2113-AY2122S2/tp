package arcs.data.mealreservation;

import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.menuitems.MenuItem;
import arcs.data.menuitems.MenuItemManager;
import arcs.data.route.Route;

import java.util.ArrayList;

public class MealReservationManager {

    private static final String NO_FLIGHT_BOOKED_MESSAGE = "You have to book a seat on this flight, "
            + "before you can reserve a meal.";

    private static final String MEAL_ALREADY_RESERVED_MESSAGE = "A meal was already reserved for this customer";

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

    public void reserveMeal(Customer customer, Route route,
            ArrayList<MenuItem> reservedMenuItems) throws ArcsException {
        if (!flightBookingManager.checkFlightBookingValidity(customer.getIc(),route.getFlightID())) {
            throw new ArcsException(NO_FLIGHT_BOOKED_MESSAGE);
        }
        if (checkExistingMealReservation(customer.getIc(), route.getFlightID())) {
            throw new ArcsException(MEAL_ALREADY_RESERVED_MESSAGE);
        }
        mealReservations.add(new MealReservation(customer,route,reservedMenuItems));
    }

    public boolean checkExistingMealReservation(String ic, String fid) {
        for (MealReservation mealReservation : mealReservations) {
            if (ic.equals(mealReservation.getCustomer().getIc()) && fid.equals(mealReservation.getRoute().getFlightID())) {
                return true;
            }
        }
        return false;
    }

}
