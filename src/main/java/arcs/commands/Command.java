package arcs.commands;

import arcs.data.customer.CustomerManager;
import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.mealreservation.MealReservationManager;
import arcs.data.route.RouteManager;
import arcs.data.menuitems.MenuItemManager;
import arcs.data.staff.StaffManager;

public abstract class Command {

    protected boolean isExit = false;
    protected RouteManager routeManager;
    protected FlightBookingManager flightBookingManager;
    protected MenuItemManager menuItemManager;
    protected CustomerManager customerManager;
    protected StaffManager staffManager;
    protected MealReservationManager mealReservationManager;

    public void setData(RouteManager routeManager, FlightBookingManager flightBookingManager,
                        MenuItemManager menuItemManager, CustomerManager customerManager,
                        StaffManager staffManager, MealReservationManager mealReservationManager) {
        this.routeManager = routeManager;
        this.flightBookingManager = flightBookingManager;
        this.menuItemManager = menuItemManager;
        this.customerManager = customerManager;
        this.staffManager = staffManager;
        this.mealReservationManager = mealReservationManager;
    }

    /**
     * Returns application's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets application's exit status to true.
     */
    protected void setIsExit() {
        isExit = true;
    }

    /**
     * Executes command.
     */
    public abstract CommandResult execute();

}
