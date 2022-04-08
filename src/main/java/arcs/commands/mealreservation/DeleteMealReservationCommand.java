package arcs.commands.mealreservation;

import arcs.commands.Command;
import arcs.commands.CommandResult;

import java.util.ArrayList;

public class DeleteMealReservationCommand extends Command {

    public static final String COMMAND_WORD = "deleteMealReservation";

    private String cid;
    private String fid;

    private ArrayList<String> emptyFields = new ArrayList<>();

    private static final String FOUND_MESSAGE = "The following meal reservation was deleted: ";
    private static final String EMPTY_MESSAGE = "No meal reservation was found.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified: ";

    public DeleteMealReservationCommand(String cid, String fid) {
        this.cid = cid;
        this.fid = fid;
        checkEmptyField();
    }

    /**
     * Stores empty field in an array for empty fields.
     *
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
     * Executes the command delete meal reservation.
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
            String mealReservationInfoString = mealReservationManager.getSpecificCustomerMealReservation(cid, fid);
            commandResult = deleteMealReservationObject(mealReservationInfoString);
        } else {
            commandResult = new CommandResult(EMPTY_MESSAGE);
        }

        return commandResult;
    }

    /**
     * Executes the command to delete the meal reservation.
     *
     * @return CommandResult of the executed command.
     */
    private CommandResult deleteMealReservationObject(String mealReservationInfoString) {
        CommandResult commandResult;
        if (mealReservationManager.deleteSpecificCustomerMealReservation(cid, fid)) {
            commandResult = new CommandResult(FOUND_MESSAGE, mealReservationInfoString);
        } else {
            commandResult = new CommandResult(EMPTY_MESSAGE);
        }
        return commandResult;
    }
}
