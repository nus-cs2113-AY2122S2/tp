package arcs.commands;

import arcs.data.RouteManager;
import arcs.data.exception.ArcsException;

public abstract class Command {

    /**
     * Command error message.
     */
    public static final String COMMAND_ERROR = "I don't understand what you have entered.";

    protected RouteManager routeManager;
    protected boolean isExit = false;

    private static boolean isAtMainMenu = true;

    public void setData(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    //public abstract CommandResult execute();

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

    public boolean isAtMainMenu() {
        return isAtMainMenu;
    }

    protected void setIsAtMainMenu() {
        isAtMainMenu = true;
    }

    /**
     * Executes command.
     *
     * @throws ArcsException If command is invalid.
     */
    public abstract CommandResult execute() throws ArcsException;
}
