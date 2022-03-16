package ARCS.commands;

import ARCS.data.RouteManager;

public abstract class Command {
    protected RouteManager routeManager;


    public abstract CommandResult execute();
}
