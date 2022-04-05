package arcs.parser;

import arcs.commands.Command;
import arcs.commands.mealreservation.AddMealReservationCommand;

import java.util.HashMap;

public class MealReservationParser {
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
            case "fid":
                flightId = value;
                break;
            case "ic":
                customerIc = value;
                break;
            case "drinks":
                menuItemsToReserve.put("DRINKS", value.replace("_", " "));
                break;
            case "appetizer":
                menuItemsToReserve.put("APPETIZER", value.replace("_", " "));
                break;
            case "main":
                menuItemsToReserve.put("MAIN", value.replace("_", " "));
                break;
            case "side":
                menuItemsToReserve.put("SIDE", value.replace("_", " "));
                break;
            case "dessert":
                menuItemsToReserve.put("DESSERT", value.replace("_", " "));
                break;
            default:
                break;
            }
        }
        return new AddMealReservationCommand(customerIc,flightId,menuItemsToReserve);
    }
}
