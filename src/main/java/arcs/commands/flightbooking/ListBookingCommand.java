package arcs.commands.flightbooking;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.flightbooking.FlightBooking;

import java.util.ArrayList;

public class ListBookingCommand extends Command {
    public static final String COMMAND_WORD = "listBooking";
    private static final String FEEDBACK = "Current flight booking:";

    @Override
    public CommandResult execute() {
        ArrayList<FlightBooking> flightBookings = flightBookingManager.getAllFlightBookings();
        ArrayList<String> bookingInfo = new ArrayList<>();
        for (FlightBooking booking: flightBookings) {
            bookingInfo.add(booking.getFlightBookingInfo());
        }
        return new CommandResult(FEEDBACK, bookingInfo);
    }
}
