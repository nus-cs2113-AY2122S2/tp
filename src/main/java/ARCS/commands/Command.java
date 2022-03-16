package ARCS.commands;

import ARCS.data.RouteManager;

public abstract class Command {
    protected RouteManager routeManager;

    public void setData(RouteManager routeManager) {
        this.routeManager = routeManager;
    }
    public abstract CommandResult execute();
}
