package arcs.commands.mealreservation;

import arcs.commands.Command;
import arcs.commands.CommandResult;

import java.util.ArrayList;

public class FindMealReservationCommand extends Command {
    public static final String COMMAND_WORD = "findMealReservation";

    private String cid;
    private String fid;

    private ArrayList<String> emptyFields = new ArrayList<>();

    private static final String FOUND_MESSAGE = "The following meal reservation was found: ";
    private static final String EMPTY_MESSAGE = "No meal reservation was found.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified: ";

    public FindMealReservationCommand(String cid, String fid) {
        this.cid = cid;
        this.fid = fid;
        checkEmptyField();
    }

    /**
     * Stores empty field in an array for empty fields.
     */
    private void checkEmptyField() {
        if (cid == null || cid.isEmpty()) {
            emptyFields.add("Customer IC");
        }

        if (fid == null || fid.isEmpty()) {
            emptyFields.add("Flight ID");
        }
    }

    /**
     * Executes the command to find meal reservation.
     *
     * @return CommandResult result of the executed command.
     */
    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }

        CommandResult commandResult;
        if (mealReservationManager.checkExistingMealReservation(cid, fid)) {
            commandResult = new CommandResult(FOUND_MESSAGE,
                    mealReservationManager.getSpecificCustomerMealReservation(cid, fid));
        } else {
            commandResult = new CommandResult(EMPTY_MESSAGE);
        }

        return commandResult;
    }
}
