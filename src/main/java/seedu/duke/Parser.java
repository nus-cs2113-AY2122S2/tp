package seedu.duke;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.command.*;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.InvalidDateException;

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

    public static final Pattern HOTEL_ARGS_FORMAT = Pattern.compile(
            "(?<id>[\\d]+),(?<name>.+),(?<country>.+),(?<price>.+),(?<packageid>[\\d]+)");

    public static final Pattern HOTELS_ARGS_FORMAT = Pattern.compile("(?<id>[\\d]+)");

    public static DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static Command parse(String input) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(input);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case PackagesCommand.COMMAND_WORD:
            return new PackagesCommand();

        case InfoCommand.COMMAND_WORD:
            return prepareInfo(arguments);

        case ReservationCommand.COMMAND_WORD:
            return prepareReserve(arguments);

        case RemoveReservationCommand.COMMAND_WORD:
            return prepareRemove(arguments);

        case PrintReservationsCommand.COMMAND_WORD:
            return prepareReservations(arguments);

        case PrintAllCommand.COMMAND_WORD:
            return new PrintAllCommand();

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
            String end = matcher.group("endDate");
            LocalDate startDate = LocalDate.from(PARSE_FORMAT.parse(start));
            LocalDate endDate = LocalDate.from(PARSE_FORMAT.parse(end));
            if (!dateStartEndValid(startDate, endDate)) {
                throw new InvalidDateException("End date cannot be before start date!");
            }
            String hotel = matcher.group("hotel");
            double price = Double.parseDouble(matcher.group("price"));
            if (price <= 0) {
                throw new InvalidInputException("Price should not be less than or equal to 0!");
            }
            String country = matcher.group("country");
            int vacancies = Integer.parseInt(matcher.group("vacancies"));
            return new AddCommand(name, id, startDate, endDate, hotel, price, country, vacancies);
        } catch (InvalidDateException e) {
            return new InvalidDateCommand(e.getMessage());
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
            int id = new BigInteger(matcher.group("id")).intValueExact();
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

    private static Command prepareAddHotel(String args) {
        final Matcher matcher = HOTEL_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int hotelID = Integer.parseInt(matcher.group("id"));
            String hotelName = matcher.group("name");
            String country = matcher.group("country");
            double price = Double.parseDouble(matcher.group("price"));
            int packageID = Integer.parseInt(matcher.group("packageid"));
            return new AddHotelCommand(hotelID, hotelName, country, price, packageID);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    private static Command prepareHotels(String args) {
        final Matcher matcher = HOTELS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new WrongFormatCommand(args);
        }
        try {
            int id = Integer.parseInt(matcher.group("id"));
            return new PrintHotelsCommand(id);
        } catch (Exception e) {
            return new WrongFormatCommand(e.getMessage());
        }
    }

    public static boolean dateStartEndValid(LocalDate startDate, LocalDate endDate) {
        return endDate.isAfter(startDate);
    }
}
