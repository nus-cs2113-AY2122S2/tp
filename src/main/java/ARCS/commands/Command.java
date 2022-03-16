package ARCS.commands;

import ARCS.data.RouteManager;

public abstract class Command {
    protected RouteManager routeManager;
    protected boolean isExit = false;

    public void setData(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public abstract CommandResult execute();

    public boolean isExit() {
        return isExit;
    }
}
