package arcs.parser;

import arcs.commands.Command;
import arcs.commands.mealreservation.AddMealReservationCommand;
import arcs.commands.mealreservation.DeleteMealReservationCommand;
import arcs.commands.mealreservation.FindMealReservationCommand;
import arcs.data.exception.ArcsException;

import java.util.HashMap;

public class MealReservationParser {


    private static final String INVALID_PARAMETER_MESSAGE = "Spaces for menu item name and type"
            + " must be separated by '_'";

    /**
     * Command words for switch statements.
     */
    private static final String DRINKS_COMMAND_WORD = "drinks";
    private static final String APPETIZER_COMMAND_WORD = "appetizer";
    private static final String MAIN_COMMAND_WORD = "main";
    private static final String SIDE_COMMAND_WORD = "side";
    private static final String DESSERT_COMMAND_WORD = "dessert";
    private static final String IC_COMMAND_WORD = "ic";
    private static final String FLIGHT_ID_COMMAND_WORD = "fid";

    /**
     * Parses the User input into the correct format to make a Meal Reservation.
     * @param argumentLine of raw user input
     * @return Command to Add Meal Reservation.
     * @throws ArcsException If user did not use "_" to separate its name or type input.
     */
    public static Command prepareMealReservationCommand(String argumentLine) throws ArcsException {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new AddMealReservationCommand(null, null, null);
        }
        String[] args = argumentLine.split(" ");
        String flightId = null;
        String customerIc = null;
        HashMap<String, String> menuItemsToReserve = new HashMap<>();
        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String [] argSplit = arg.split("/",2);
            if (argSplit.length < 2) {
                throw new ArcsException(INVALID_PARAMETER_MESSAGE);
            }
            String field = argSplit[0].trim().toLowerCase();
            String value = argSplit[1].trim();
            switch (field) {
            case FLIGHT_ID_COMMAND_WORD:
                flightId = value;
                break;
            case IC_COMMAND_WORD:
                customerIc = value;
                break;
            case DRINKS_COMMAND_WORD:
                menuItemsToReserve.put("DRINKS", value.replace("_", " "));
                break;
            case APPETIZER_COMMAND_WORD:
                menuItemsToReserve.put("APPETIZER", value.replace("_", " "));
                break;
            case MAIN_COMMAND_WORD:
                menuItemsToReserve.put("MAIN", value.replace("_", " "));
                break;
            case SIDE_COMMAND_WORD:
                menuItemsToReserve.put("SIDE", value.replace("_", " "));
                break;
            case DESSERT_COMMAND_WORD:
                menuItemsToReserve.put("DESSERT", value.replace("_", " "));
                break;
            default:
                menuItemsToReserve.put(field, value.replace("_", " "));
                break;
            }
        }
        return new AddMealReservationCommand(customerIc,flightId,menuItemsToReserve);
    }

    /**
     * Formats the raw user input into a correct type for the Find Meal Reservation Command.
     * @param argumentLine Raw User Input.
     * @return Command of Formatted Find Meal Reservation Command.
     */
    public static Command prepareFindMealReservation(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new FindMealReservationCommand(null, null);
        }
        String[] args = argumentLine.split(" ");
        String cid = null;
        String fid = null;
        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String[] argSplit = arg.split("/", 2);
            if (argSplit.length < 2) {
                continue;
            }
            String field = argSplit[0].trim().toLowerCase();
            String value = argSplit[1].trim();
            switch (field) {
            case IC_COMMAND_WORD:
                cid = value;
                break;
            case FLIGHT_ID_COMMAND_WORD:
                fid = value;
                break;
            default:
                break;
            }
        }
        return new FindMealReservationCommand(cid, fid);
    }

    /**
     * Parses the user input and prepares it to the correct format for Delete Meal Reservation Command.
     * @param argumentLine Raw User Input.
     * @return Delete Meal Reservation Command.
     */
    public static Command prepareDeleteMealReservation(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new DeleteMealReservationCommand(null, null);
        }
        String[] args = argumentLine.split(" ");
        String cid = null;
        String fid = null;
        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String[] argSplit = arg.split("/", 2);
            if (argSplit.length < 2) {
                continue;
            }
            String field = argSplit[0].trim().toLowerCase();
            String value = argSplit[1].trim();
            switch (field) {
            case IC_COMMAND_WORD:
                cid = value;
                break;
            case FLIGHT_ID_COMMAND_WORD:
                fid = value;
                break;
            default:
                break;
            }
        }
        return new DeleteMealReservationCommand(cid, fid);
    }
}

