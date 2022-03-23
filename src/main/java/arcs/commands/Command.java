package arcs.commands;

import arcs.data.Route;
import arcs.data.RouteManager;
import arcs.data.exception.ArcsException;

public abstract class Command {

    protected boolean isExit = false;
    protected RouteManager routeManager;

    public void setData(RouteManager routeManager) {
        this.routeManager = routeManager;
    }


    /**
     * Returns applications's exit status.
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
