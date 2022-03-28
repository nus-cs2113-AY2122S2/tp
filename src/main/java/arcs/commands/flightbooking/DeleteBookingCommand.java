package arcs.commands.flightbooking;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.exception.ArcsException;
import arcs.data.flightbooking.FlightBooking;

public class DeleteBookingCommand extends Command {
    public static final String COMMAND_WORD = "deleteBooking";
    private int index;
    private static final String SUCCESS_MESSAGE = "OK! The following flight booking has been deleted:";

    public DeleteBookingCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            FlightBooking deleted = flightBookingManager.deleteBooking(index);
            return new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                    + deleted.getFlightBookingInfo());
        } catch (ArcsException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
