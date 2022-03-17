package ARCS.commands;

import ARCS.data.Route;

public class DeleteRouteCommand extends Command {
    public static final String COMMAND_WORD = "deleteRoute";
    private int index;
    private static final String SUCCESS_MESSAGE = "OK! The following route has been deleted:";

    public DeleteRouteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        Route deleted = routeManager.deleteRoute(index);
        return new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + deleted.getFlightInfo());
    }
}
