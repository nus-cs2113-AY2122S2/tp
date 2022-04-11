package arcs.commands.route;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.route.Route;
import arcs.data.exception.DuplicateDataException;

import java.util.ArrayList;

public class AddRouteCommand extends Command {
    public static final String COMMAND_WORD = "addRoute";
    private Route toAdd;
    private ArrayList<String> emptyFields = new ArrayList<>();

    private static final String SUCCESS_MESSAGE = "OK! The following new route is added: ";
    private static final String DUPLICATE_MESSAGE = "The flight ID already exits. This flight cannot be added.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";

    public AddRouteCommand(String flightID, String date, String time,
                           String from, String to, int capacity) {
        checkEmptyField(flightID, date, time, from, to, capacity);
        this.toAdd = new Route(flightID, date, time, from, to, capacity);
    }

    /**
     * Checks whether the input fields are null or empty.
     * @param flightID input flight ID
     * @param date input flight date
     * @param time input flight time
     * @param from input flight source
     * @param to input flight destination
     * @param capacity input flight capacity
     */
    private void checkEmptyField(String flightID, String date, String time,
                                 String from, String to, int capacity) {
        if (flightID == null || flightID.isEmpty()) {
            emptyFields.add("Flight ID");
        }
        if (date == null || date.isEmpty()) {
            emptyFields.add("Flight date");
        }
        if (time == null || time.isEmpty()) {
            emptyFields.add("Flight time");
        }
        if (from == null || from.isEmpty()) {
            emptyFields.add("Source");
        }
        if (to == null || to.isEmpty()) {
            emptyFields.add("Destination");
        }
        if (capacity == 0) {
            emptyFields.add("Capacity");
        }
    }

    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }
        CommandResult result;

        try {
            routeManager.addRoute(toAdd);
            result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                    + toAdd.getFlightInfo());
        } catch (DuplicateDataException e) {
            result = new CommandResult(DUPLICATE_MESSAGE);
        }

        return result;
    }

}
