package arcs.commands;

import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.route.RouteManager;


public abstract class Command {

    protected boolean isExit = false;
    protected RouteManager routeManager;
    protected FlightBookingManager flightBookingManager;

    public void setData(RouteManager routeManager, FlightBookingManager flightBookingManager) {
        this.routeManager = routeManager;
        this.flightBookingManager = flightBookingManager;
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
