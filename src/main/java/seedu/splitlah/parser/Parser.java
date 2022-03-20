package seedu.splitlah.parser;

import seedu.splitlah.command.Command;
import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.command.ActivityDeleteCommand;
import seedu.splitlah.command.ActivityListCommand;
import seedu.splitlah.command.ActivityViewCommand;
import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.command.SessionDeleteCommand;
import seedu.splitlah.command.SessionListCommand;
import seedu.splitlah.command.SessionSummaryCommand;
import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.command.GroupCreateCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a parser that interprets the user input into data that can be understood by the program.
 * 
 * @author Warren
 */
public class Parser {

    // KEY CONSTANTS
    private static final String LOCALDATE_TODAY_INDICATOR = "today";
    private static final int COMMAND_WITH_ARGS_TOKEN_COUNT = 3;
    private static final int DELIMITERED_COMMAND_MIN_TOKEN_COUNT = 2;
    private static final int MINIMUM_SURCHARGE_PERCENT = 0;
    private static final int MAXIMUM_SURCHARGE_PERCENT = 100;

    // ERROR REPORTING FUNCTIONS
    /**
     * Returns a String object containing an error message for a specified delimiter that is missing.
     * 
     * @param delimiter A String object that represents a demarcation of a specific argument in the command. 
     * @return A String object representing an error message for missing a specified delimiter in the input command.
     */
    private static String getMissingDelimiterErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_DELIMITER_NOT_FOUND + delimiter;
    }

    /**
     * Returns a String object containing an error message for a missing argument that should follow after a specified
     * delimiter in the command input by the user.
     *
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return A String object representing an error message for missing an argument in the input command.
     */
    private static String getMissingArgumentErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_MISSING_ARGUMENT + delimiter;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to read an input String object
     * as an integer.
     *
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return A String object representing an error message for a non-integer input.
     */
    private static String getNonIntegerErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_NON_INTEGER_ARGUMENT + delimiter;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to read an input String object
     * as a double.
     *
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return A String object representing an error message for a non-double input.
     */
    private static String getNonMonetaryErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT + delimiter;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to parse an input String object
     * as a valid Goods and Services Tax (GST) in percents.
     *
     * @return A String object representing an error message for an invalid GST input.
     */
    private static String getInvalidGstErrorMessage() {
        return Message.ERROR_PARSER_INVALID_GST_SURCHARGE + GST_DELIMITER;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to parse an input String object
     * as a valid service charge in percents.
     *
     * @return A String object representing an error message for an invalid service charge input.
     */
    private static String getInvalidServiceChargeErrorMessage() {
        return Message.ERROR_PARSER_INVALID_SERVICE_CHARGE + SERVICE_CHARGE_DELIMITER;
    }

    // MAIN PUBLIC PARSING FUNCTIONS
    /**
     * Returns a String object that represents a name, given the command arguments from user input, delimited by the
     * Name delimiter.
     * 
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A String object that represents a name.
     * @throws InvalidFormatException If the Name delimiter is not found in the command arguments, or
     *                                if no arguments representing a name were provided after the Name delimiter.
     */
    public static String parseName(String commandArgs) throws InvalidFormatException {
        return getArgumentFromDelimiter(commandArgs, NAME_DELIMITER);
    }

    /**
     * Returns a String array object that represents a list of names separated by whitespaces,
     * given the command arguments from user input, delimited by the Person list delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A String array object that represents a list of names.
     * @throws InvalidFormatException If the Person list delimiter is not found in the command arguments, or
     *                                if no arguments representing a list of names were provided after the 
     *                                Person list delimiter.
     */
    public static String[] parsePersonList(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, PERSON_LIST_DELIMITER);
        return argument.split(REGEX_WHITESPACES_DELIMITER);
    }

    /**
     * Returns a String array object that represents a list of names of involved persons separated by whitespaces,
     * given the command arguments from user input, delimited by the Involved delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A String array object that represents a list of names of involved persons.
     * @throws InvalidFormatException If the Involved delimiter is not found in the command arguments, or
     *                                if no arguments representing a list of names were provided after the 
     *                                Involved delimiter.
     */
    public static String[] parseInvolved(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, INVOLVED_DELIMITER);
        return argument.split(REGEX_WHITESPACES_DELIMITER);
    }

    /**
     * Returns a String object that represents a name of a payer, given the command arguments from user input, 
     * delimited by the Payer delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A String object that represents a name of a payer.
     * @throws InvalidFormatException If the Payer delimiter is not found in the command arguments,
     *                                if no arguments representing a name were provided after the Payer delimiter, or
     *                                if the argument contains more than a single name.
     */
    public static String parsePayer(String commandArgs) throws InvalidFormatException {
        String payer = getArgumentFromDelimiter(commandArgs, PAYER_DELIMITER);
        if (payer.indexOf(' ') != INVALID_INDEX_INDICATOR) {
            throw new InvalidFormatException(Message.ERROR_PARSER_MORE_THAN_ONE_PAYER);
        }
        return payer;
    }

    /**
     * Returns an integer that represents a session unique identifier, given the command arguments from user input, 
     * delimited by the Session ID delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return An integer that represents a session unique identifier.
     * @throws InvalidFormatException If the Session ID delimiter is not found in the command arguments,
     *                                if no arguments representing a session unique identifier were provided after the 
     *                                Session ID delimiter,
     *                                if the parsed argument cannot be parsed as an integer, or
     *                                if the integer parsed from the argument is not a positive integer.
     */
    public static int parseSessionId(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, SESSION_ID_DELIMITER);
        return parseIdFromString(argument, SESSION_ID_DELIMITER);
    }

    /**
     * Returns an integer that represents an activity unique identifier, given the command arguments from user input, 
     * delimited by the Activity ID delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return An integer that represents an activity unique identifier.
     * @throws InvalidFormatException If the Activity ID delimiter is not found in the command arguments,
     *                                if no arguments representing an activity unique identifier were provided after 
     *                                the Activity ID delimiter,
     *                                if the parsed argument cannot be parsed as an integer, or
     *                                if the integer parsed from the argument is not a positive integer.
     */
    public static int parseActivityId(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, ACTIVITY_ID_DELIMITER);
        return parseIdFromString(argument, ACTIVITY_ID_DELIMITER);
    }

    /**
     * Returns an integer that represents a group unique identifier, given the command arguments from user input, 
     * delimited by the Group ID delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return An integer that represents a group unique identifier.
     * @throws InvalidFormatException If the Group ID delimiter is not found in the command arguments,
     *                                if no arguments representing a group unique identifier were provided after 
     *                                the Group ID delimiter,
     *                                if the parsed argument cannot be parsed as an integer, or
     *                                if the integer parsed from the argument is not a positive integer.
     */
    public static int parseGroupId(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, GROUP_ID_DELIMITER);
        return parseIdFromString(argument, GROUP_ID_DELIMITER);
    }

    /**
     * Returns a LocalDate object that represents a date, given the command arguments from user input, 
     * delimited by the Date delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A LocalDate object that represents a date specified by the argument in the format of 'DD-MM-YYYY' or
     *         the current date if the argument following the Date delimiter indicates "today".
     * @throws InvalidFormatException If the Date delimiter is not found in the command arguments,
     *                                if no arguments representing a date were provided after the 
     *                                Date delimiter, or
     *                                if the argument provided does not indicate "today" nor follow the date format of
     *                                'DD-MM-YYYY'.
     */
    public static LocalDate parseLocalDate(String commandArgs) throws InvalidFormatException {
        if (!hasDelimiter(commandArgs, DATE_DELIMITER)) {
            throw new InvalidFormatException(getMissingDelimiterErrorMessage(DATE_DELIMITER));
        }

        String argument = getArgumentFromDelimiter(commandArgs, DATE_DELIMITER);
        if (argument.equalsIgnoreCase(LOCALDATE_TODAY_INDICATOR)) {
            return LocalDate.now();
        }
        
        try {
            return LocalDate.parse(argument, DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new InvalidFormatException(Message.ERROR_PARSER_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Returns a double that represents a single total cost, given the command arguments from user input, 
     * delimited by the Total cost delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A double that represents a single total cost.
     * @throws InvalidFormatException If the Total cost delimiter is not found in the command arguments,
     *                                if no arguments representing a total cost were provided after the 
     *                                Total cost delimiter,
     *                                if the arguments cannot be parsed as a double,
     *                                if the parsed cost value is not positive,
     *                                if the parsed cost value has more than 2 decimal points, or
     *                                if the parsed cost value has more than 12 digits before the decimal point.
     */
    public static double parseTotalCost(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, TOTAL_COST_DELIMITER);
        return parseCostFromString(argument, TOTAL_COST_DELIMITER);
    }

    /**
     * Returns a double array object that represents a list of cost values, given the command arguments from 
     * user input, delimited by the Cost list delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A double array object that represents a list of cost values.
     * @throws InvalidFormatException If the Cost list delimiter is not found in the command arguments,
     *                                if no arguments representing a list of cost values were provided after the 
     *                                Cost list delimiter,
     *                                if any token in the argument cannot be parsed as a double, or
     *                                if any cost value parsed is not positive,
     *                                if any parsed cost value has more than 2 decimal points, or
     *                                if any parsed cost value has more than 12 digits before the decimal point.
     */
    public static double[] parseCostList(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, COST_LIST_DELIMITER);
        String[] costStrings = argument.split(REGEX_WHITESPACES_DELIMITER);
        double[] costs = new double[costStrings.length];
        for (int i = 0; i < costStrings.length; i++) {
            costs[i] = parseCostFromString(costStrings[i], COST_LIST_DELIMITER);
        }
        return costs;
    }

    /**
     * Returns an integer that represents the GST charge in percents, given the command arguments from user input, 
     * delimited by the GST delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return An integer that represents a GST charge in percents.
     * @throws InvalidFormatException If no arguments representing a GST charge were provided after the 
     *                                GST delimiter,
     *                                if the argument cannot be parsed as an integer, or
     *                                if the parsed GST percentage is not in [0, 100].
     */
    public static int parseGst(String commandArgs) throws InvalidFormatException {
        if (!hasDelimiter(commandArgs, GST_DELIMITER)) {
            return 0;
        }

        String argument = getArgumentFromDelimiter(commandArgs, GST_DELIMITER);
        int gst = parseIntFromString(argument, GST_DELIMITER);
        if (gst < MINIMUM_SURCHARGE_PERCENT || gst > MAXIMUM_SURCHARGE_PERCENT) {
            throw new InvalidFormatException(getInvalidGstErrorMessage());
        }
        return gst;
    }

    /**
     * Returns an integer that represents the service charge in percents, given the command arguments from user input, 
     * delimited by the Service charge delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return An integer that represents a service charge in percents.
     * @throws InvalidFormatException If no arguments representing a service charge were provided after the 
     *                                Service charge delimiter,
     *                                if the argument cannot be parsed as an integer, or
     *                                if the parsed service charge percentage is not in [0, 100].
     */
    public static int parseServiceCharge(String commandArgs) throws InvalidFormatException {
        if (!hasDelimiter(commandArgs, SERVICE_CHARGE_DELIMITER)) {
            return 0;
        }

        String argument = getArgumentFromDelimiter(commandArgs, SERVICE_CHARGE_DELIMITER);
        int serviceCharge = parseIntFromString(argument, SERVICE_CHARGE_DELIMITER);
        if (serviceCharge < MINIMUM_SURCHARGE_PERCENT || serviceCharge > MAXIMUM_SURCHARGE_PERCENT) {
            throw new InvalidFormatException(getInvalidServiceChargeErrorMessage());
        }
        return serviceCharge;
    }
    
    // COMMAND PARSING METHODS

    /**
     * Returns a String object containing the arguments portion of the entire command input from the user.<br>
     * E.g. Returns "/n Lunch /d 11-03-2022 /pl Warren Ivan Roy" where
     *      commandString = "session /create /n Lunch /d 11-03-2022 /pl Warren Ivan Roy"
     * 
     * @param commandString A String object that contains the entire command input provided by the user.
     * @return A String object containing the arguments portion of the entire command input from the user if any,
     *         an empty String object otherwise.
     */
    public static String getRemainingArgument(String commandString) {
        String[] commandTokens = commandString.trim().split(REGEX_WHITESPACES_DELIMITER, COMMAND_WITH_ARGS_TOKEN_COUNT);
        if (commandTokens.length < COMMAND_WITH_ARGS_TOKEN_COUNT) {
            return "";
        }
        return commandTokens[2];
    }

    /**
     * Returns a String object containing the command type portion of the entire command input from the user.<br>
     * E.g. Returns "session /create" where
     *      commandString = "session /create /n Lunch /d 11-03-2022 /pl Warren Ivan Roy"
     *
     * @param commandString A String object that contains the entire command input provided by the user.
     * @return A String object containing the command type portion of the entire command input from the user if valid,
     *         null otherwise.
     */
    public static String getCommandType(String commandString) {
        String[] commandTokens = commandString.trim().split(REGEX_WHITESPACES_DELIMITER, COMMAND_WITH_ARGS_TOKEN_COUNT);
        
        if (commandTokens.length < DELIMITERED_COMMAND_MIN_TOKEN_COUNT) {
            return commandTokens[0];
        } else if (!commandTokens[1].startsWith(DELIMITER_INDICATOR)) {
            return null;
        }
        return commandTokens[0] + " " + commandTokens[1];
    }

    /**
     * Returns a Command object that corresponds to the String object representing the input provided by the user.
     * 
     * @param input A String object representing the input entered by the user of SplitLah.
     * @return A Command object that performs the task specified by the user if the syntax of the input is valid,
     *         an InvalidCommand object that prints an error message otherwise.
     */
    public static Command getCommand(String input) {
        String commandType = getCommandType(input);
        String remainingArgs = getRemainingArgument(input);

        if (commandType == null) {
            return new InvalidCommand(Message.ERROR_PARSER_INVALID_COMMAND);
        }
        
        String errorMessage = checkIfArgumentsValidForCommand(commandType, remainingArgs);
        if (!errorMessage.isEmpty()) {
            return new InvalidCommand(errorMessage);
        }

        switch (commandType.toLowerCase()) {
        case "":
            return new InvalidCommand(Message.ERROR_PARSER_EMPTY_COMMAND);
        case SessionCreateCommand.COMMAND_TEXT:
            return SessionCreateCommand.prepare(remainingArgs);
        case SessionDeleteCommand.COMMAND_TEXT:
            return SessionDeleteCommand.prepare(remainingArgs);
        case SessionSummaryCommand.COMMAND_TEXT:
            return SessionSummaryCommand.prepare(remainingArgs);
        case SessionListCommand.COMMAND_TEXT:
            return new SessionListCommand();
        case ActivityCreateCommand.COMMAND_TEXT:
            return ActivityCreateCommand.prepare(remainingArgs);
        case ActivityDeleteCommand.COMMAND_TEXT:
            return ActivityDeleteCommand.prepare(remainingArgs);
        case ActivityListCommand.COMMAND_TEXT:
            return ActivityListCommand.prepare(remainingArgs);
        case ActivityViewCommand.COMMAND_TEXT:
            return ActivityViewCommand.prepare(remainingArgs);
        case GroupCreateCommand.COMMAND_TEXT:
            return GroupCreateCommand.prepare(remainingArgs);
        case HelpCommand.COMMAND_TEXT:
            return new HelpCommand();
        case ExitCommand.COMMAND_TEXT:
            return new ExitCommand();
        default:
            return new InvalidCommand(Message.ERROR_PARSER_INVALID_COMMAND);
        }
    }
}
