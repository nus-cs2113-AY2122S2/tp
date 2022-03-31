package seedu.duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.command.*;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static Command parse(String input) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(input);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case "bye":
            return new ByeCommand();

        case "help":
            return new HelpCommand();

        case "add":
            return prepareAdd(arguments);

        case "delete":
            // delete TravelPackage by its ID
            return prepareDelete(arguments);
        case "packages":
            return new PackagesCommand();

        case "info":
            return prepareInfo(arguments);

        case "reserve":
            return prepareReserve(arguments);

        case "remove": // delete reservation by giving travelpackage ID and contact number.
            return prepareRemove(arguments);

        case "reservations":
            return prepareReservations(arguments);

        default:
            return new ErrorCommand(input);
        }
    }

    private static Command prepareAdd(String args) {
        String[] argsArray = args.trim().split(",");
        if (argsArray.length != 8) {
            return new WrongFormatCommand(args);
        }

        try {
            final int nameIndex = 0;
            final int idIndex = 1;
            final int startIndex = 2;
            final int endIndex = 3;
            final int hotelIndex = 4;
            final int priceIndex = 5;
            final int countryIndex = 6;
            final int vacanciesIndex = 7;
            String name = argsArray[nameIndex];
            int id = Integer.parseInt(argsArray[idIndex]);
            String start = argsArray[startIndex];
            String end = argsArray[endIndex];
            String hotel = argsArray[hotelIndex];
            double price = Double.parseDouble(argsArray[priceIndex]);
            String country = argsArray[countryIndex];
            int vacancies = Integer.parseInt(argsArray[vacanciesIndex]);
            return new AddCommand(name, id, start, end, hotel, price, country, vacancies);

        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareDelete(String args) {
        try {
            int id = Integer.parseInt(args.trim());
            return new DeleteCommand(id);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareReserve(String args) {
        try {
            String[] argsArray = args.trim().split(","); // process the rest and split by comma
            int packageID = Integer.parseInt(argsArray[0]);
            String name = argsArray[1];
            String number = argsArray[2];
            int pax = Integer.parseInt(argsArray[3]);

            return new ReservationCommand(packageID, name, number, pax);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareRemove(String args) {
        try {
            String[] argsArray = args.trim().split(",");
            int packageID = Integer.parseInt(argsArray[0]);
            String number = argsArray[1];
            return new RemoveReservationCommand(packageID, number);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareInfo(String args) {
        try {
            String[] argsArray = args.trim().split(",");
            int packageID = Integer.parseInt(argsArray[0]);
            //String number = argsArray[1];
            return new InfoCommand(packageID);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareReservations(String args) {
        try {
            int id = Integer.parseInt(args.trim());
            return new PrintReservationsCommand(id);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }
}
