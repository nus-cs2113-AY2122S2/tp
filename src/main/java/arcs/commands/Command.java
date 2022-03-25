package arcs.commands;

import arcs.data.menuitems.MenuItemManager;
import arcs.data.RouteManager;


public abstract class Command {

    protected boolean isExit = false;
    protected RouteManager routeManager;
    protected MenuItemManager menuItemManager;

    public void setData(RouteManager routeManager, MenuItemManager menuItemManager) {
        this.routeManager = routeManager;
        this.menuItemManager = menuItemManager;
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
