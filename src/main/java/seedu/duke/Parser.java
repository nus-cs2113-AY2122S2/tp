package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.command.*;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern PACKAGE_DATA_ARGS_FORMAT = Pattern.compile(
            "(?<name>.+),(?<id>[\\d]+),(?<startDate>.+),(?<endDate>.+),(?<hotel>.+),(?<price>.+),(?<country>.+),(?<vacancies>[\\d]+)");

    public static final Pattern RESERVATION_DATA_ARGS_FORMAT = Pattern.compile(
            "(?<id>[\\d]+),(?<name>.+),(?<number>[\\d]+),(?<pax>[\\d]+)");

    public static final Pattern REMOVE_RESERVATION_ARGS_FORMAT = Pattern.compile("(?<id>[\\d]+),(?<number>[\\d]+)");

    public static final Pattern ONE_ARGS_FORMAT = Pattern.compile("(?<id>[\\d]+)");

    public static DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

        final Matcher matcher = PACKAGE_DATA_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }

        try {
            String name = matcher.group("name");
            int id = Integer.parseInt(matcher.group("id"));
            String start = matcher.group("startDate");
            LocalDate startDate = LocalDate.from(PARSE_FORMAT.parse(start));
            String end = matcher.group("endDate");
            LocalDate endDate = LocalDate.from(PARSE_FORMAT.parse(end));
            String hotel = matcher.group("hotel");
            double price = Double.parseDouble(matcher.group("price"));
            String country = matcher.group("country");
            int vacancies = Integer.parseInt(matcher.group("vacancies"));
            return new AddCommand(name, id, startDate, endDate, hotel, price, country, vacancies);

        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareDelete(String args) {
        final Matcher matcher = ONE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int id = Integer.parseInt(matcher.group("id"));
            return new DeleteCommand(id);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareReserve(String args) {
        final Matcher matcher = RESERVATION_DATA_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int packageID = Integer.parseInt(matcher.group("id"));
            String name = matcher.group("name");
            String number = matcher.group("number");
            int pax = Integer.parseInt(matcher.group("pax"));
            return new ReservationCommand(packageID, name, number, pax);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareRemove(String args) {
        final Matcher matcher = REMOVE_RESERVATION_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int packageID = Integer.parseInt(matcher.group("id"));
            String number = matcher.group("number");
            return new RemoveReservationCommand(packageID, number);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareInfo(String args) {
        final Matcher matcher = ONE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int packageID = Integer.parseInt(matcher.group("id"));
            return new InfoCommand(packageID);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareReservations(String args) {
        final Matcher matcher = ONE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int id = Integer.parseInt(matcher.group("id"));
            return new PrintReservationsCommand(id);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }
}
