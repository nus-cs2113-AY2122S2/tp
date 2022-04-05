package arcs.parser;

import arcs.commands.Command;
import arcs.commands.mealreservation.AddMealReservationCommand;

import java.util.HashMap;

public class MealReservationParser {

    private static final String DRINKS_COMMAND_WORD = "drinks";
    private static final String APPETIZER_COMMAND_WORD = "appetizer";
    private static final String MAIN_COMMAND_WORD = "main";
    private static final String SIDE_COMMAND_WORD = "side";
    private static final String DESSERT_COMMAND_WORD = "dessert";
    private static final String IC_COMMAND_WORD = "ic";
    private static final String FLIGHT_ID_COMMAND_WORD = "fid";

    public static Command prepareMealReservationCommand(String argumentLine) {
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
                continue;
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
                break;
            }
        }
        return new AddMealReservationCommand(customerIc,flightId,menuItemsToReserve);
    }
}

