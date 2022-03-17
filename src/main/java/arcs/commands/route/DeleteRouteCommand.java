package arcs.commands.route;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.Route;
import arcs.data.exception.ArcsException;

public class DeleteRouteCommand extends Command {
    public static final String COMMAND_WORD = "deleteRoute";
    private int index;
    private static final String SUCCESS_MESSAGE = "OK! The following route has been deleted:";

    public DeleteRouteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            Route deleted = routeManager.deleteRoute(index);
            result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                    + deleted.getFlightInfo());
        } catch (ArcsException e) {
            result = new CommandResult(e.getMessage());
        }

        return result;
    }
}
