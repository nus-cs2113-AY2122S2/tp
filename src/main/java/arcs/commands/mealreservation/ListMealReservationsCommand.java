package arcs.commands.mealreservation;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.mealreservation.MealReservation;

import java.util.ArrayList;

public class ListMealReservationsCommand extends Command {
    public static final String COMMAND_WORD = "listMealReservations";
    private static final String FEEDBACK = "Existing Meal Reservations: ";

    /**
     * Executes the command to list out all meal reservations.
     *
     * @return CommandResult result of the executed command.
     */
    @Override
    public CommandResult execute() {
        ArrayList<MealReservation> mealReservations = mealReservationManager.getAllMealReservations();
        ArrayList<String> mealReservationsInfo = new ArrayList<>();
        for (MealReservation mealReservation: mealReservations) {
            mealReservationsInfo.add(mealReservation.getMealReservationInfo());
        }
        return new CommandResult(FEEDBACK, mealReservationsInfo);
    }
}
