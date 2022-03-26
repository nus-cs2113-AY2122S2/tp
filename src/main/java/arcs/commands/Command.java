package arcs.commands;

import arcs.data.Customer.CustomerManager;
import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.route.RouteManager;
import arcs.data.menuitems.MenuItemManager;


public abstract class Command {

    protected boolean isExit = false;
    protected RouteManager routeManager;
    protected FlightBookingManager flightBookingManager;
    protected MenuItemManager menuItemManager;
    protected CustomerManager customerManager;

    public void setData(RouteManager routeManager, FlightBookingManager flightBookingManager,
                        MenuItemManager menuItemManager, CustomerManager customerManager) {
        this.routeManager = routeManager;
        this.flightBookingManager = flightBookingManager;
        this.menuItemManager = menuItemManager;
        this.customerManager = customerManager;
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
