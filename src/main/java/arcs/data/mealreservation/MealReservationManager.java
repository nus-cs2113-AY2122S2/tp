package arcs.data.mealreservation;

import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;
import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.menuitems.MenuItem;
import arcs.data.route.Route;

import java.util.ArrayList;

public class MealReservationManager {

    private static final String NO_FLIGHT_BOOKED_MESSAGE = "You have to book a seat on this flight, "
            + "before you can reserve a meal.";

    private static final String MEAL_ALREADY_RESERVED_MESSAGE = "A meal was already reserved for this customer";


    private ArrayList<MealReservation> mealReservations;

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

    /**
     * Reserves a meal for a specific customer on a specific flight path.
     * @param customer customer object of the customer reserving the meal.
     * @param route route object of the route the customer reserved the meal on.
     * @param reservedMenuItems array of menu items reserved by the customer.
     * @throws ArcsException when there is no flight booked or there has already been a meal reserved.
     */
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

    /**
     * Checks whether there has already been an existing meal reservation for a customer on a specific flight.
     * @param ic customer id to check
     * @param fid flight id to check
     * @return true if there has already been an existing meal reserved.
     */
    public boolean checkExistingMealReservation(String ic, String fid) {
        for (MealReservation mealReservation : mealReservations) {
            if (ic.equals(mealReservation.getCustomer().getIc())
                    && fid.equals(mealReservation.getRoute().getFlightID())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the meal reservation info for a specific customer.
     * @param ic customer ic to get the meal reservation for.
     * @param fid flight id of the route that the meal reservation was mde for.
     * @return Meal reservation info for the customer, empty string if no meal reservation was made.
     */
    public String getSpecificCustomerMealReservation(String ic, String fid) {
        for (MealReservation mealReservation : mealReservations) {
            if (ic.equals(mealReservation.getCustomer().getIc())
                    && fid.equals(mealReservation.getRoute().getFlightID())) {
                return mealReservation.getMealReservationInfo();
            }
        }
        return "";
    }

    /**
     * Removes the meal reservation for a specific customer.
     * @param ic customer ic to remove the meal reservation for.
     * @param fid flight id that the customer wants to remove the meal reservation for.
     * @return
     */
    public boolean deleteSpecificCustomerMealReservation(String ic, String fid) {
        for (MealReservation mealReservation : mealReservations) {
            if (ic.equals(mealReservation.getCustomer().getIc())
                    && fid.equals(mealReservation.getRoute().getFlightID())) {
                if (!(mealReservation instanceof MealReservation)) {
                    return false;
                } else {
                    mealReservations.remove(mealReservation);
                    return true;
                }
            }
        }
        return false;
    }

}
