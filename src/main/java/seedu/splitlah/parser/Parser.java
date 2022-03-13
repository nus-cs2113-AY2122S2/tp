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
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that interprets the user input into data that can be understood by the program.
 * 
 * @author Warren
 */
public class Parser {
    
    // DELIMITERS
    private static final String NAME_DELIMITER = "/n";
    private static final String PERSON_LIST_DELIMITER = "/pl";
    private static final String INVOLVED_DELIMITER = "/i";
    private static final String PAYER_DELIMITER = "/p";
    private static final String SESSION_ID_DELIMITER = "/sid";
    private static final String ACTIVITY_ID_DELIMITER = "/aid";
    private static final String GROUP_ID_DELIMITER = "/gid";
    private static final String DATE_DELIMITER = "/d";
    private static final String TOTAL_COST_DELIMITER = "/c";
    private static final String COST_LIST_DELIMITER = "/cl";
    private static final String GST_DELIMITER = "/gst";
    private static final String SERVICE_CHARGE_DELIMITER = "/sc";

    // MISC CONSTANTS
    private static final String DELIMITER_INDICATOR = "/";
    private static final String NEXT_DELIMITER_INDICATOR = " /";
    private static final String REGEX_WHITESPACES_DELIMITER = "\\s+";
    private static final String LOCALDATE_TODAY_INDICATOR = "today";
    private static final int INVALID_INDEX_INDICATOR = -1;
    private static final int COMMAND_WITH_ARGS_TOKEN_COUNT = 3;
    private static final int DELIMITERED_COMMAND_MIN_TOKEN_COUNT = 2;
    private static final int MINIMUM_SURCHARGE_PERCENT = 0;
    private static final int MAXIMUM_SURCHARGE_PERCENT = 100;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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

    // SUPPORTING FUNCTIONS

    /**
     * Returns a String object that represents an argument in the command that follows a specified delimiter.
     * 
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user. 
     * @param delimiter   A String object that represents a demarcation of a specific argument in the command.
     * @return A String object that represents an argument demarcated by the specified delimiter in the command.
     * @throws InvalidFormatException If the specified delimiter is not found in the arguments of the command, or
     *                                if a specified delimiter is found but no argument follows the delimiter.
     */
    private static String getArgumentFromDelimiter(String commandArgs, String delimiter) throws InvalidFormatException {
        int delimiterIndex = commandArgs.indexOf(delimiter);
        if (delimiterIndex == INVALID_INDEX_INDICATOR) {
            throw new InvalidFormatException(getMissingDelimiterErrorMessage(delimiter));
        }
        int argumentIndex = delimiterIndex + delimiter.length();
        int endingIndex = commandArgs.indexOf(NEXT_DELIMITER_INDICATOR, argumentIndex);
        String output;
        if (endingIndex == INVALID_INDEX_INDICATOR) {
            output = commandArgs.substring(argumentIndex).trim();
        } else {
            output = commandArgs.substring(argumentIndex, endingIndex).trim();
        }

        if (output.isEmpty()) {
            throw new InvalidFormatException(getMissingArgumentErrorMessage(delimiter));
        }
        return output;
    }

    /**
     * Returns an integer represented by the provided input String object.
     * 
     * @param input     A String object that contains numeric characters only and represents an integer.
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return An integer represented by the input String object.
     * @throws InvalidFormatException If the provided input String object contains non-numeric characters and cannot
     *                                be parsed as an integer.
     */
    private static int parseIntFromString(String input, String delimiter) throws InvalidFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(getNonIntegerErrorMessage(delimiter));
        }
    }

    /**
     * Returns an integer representing a unique identifier number, represented by the provided input String object.
     * 
     * @param input     A String object that contains numeric characters only and represents a unique identifier number.
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return An integer representing a unique identifier number.
     * @throws InvalidFormatException If the provided input String object contains non-numeric characters and cannot be
     *                                parsed as an integer, or
     *                                if the integer parsed from the input String object is not a positive integer.
     */
    private static int parseIdFromString(String input, String delimiter) throws InvalidFormatException {
        int idVal = parseIntFromString(input, delimiter);
        if (idVal <= 0) {
            throw new InvalidFormatException(Message.ERROR_PARSER_ID_VALUE_NOT_POSITIVE);
        }
        return idVal;
    }
    
    private static double parseCostFromString(String input, String delimiter) throws InvalidFormatException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(getNonMonetaryErrorMessage(delimiter));
        }
    }

    /**
     * Checks the provided String object which represents the command arguments for the existence of a specified
     * delimiter.
     * 
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @param delimiter   A String object that represents a demarcation of a specific argument in the command.
     * @return true if the String object representing the command arguments contains the specified delimiter,
     *         false otherwise.
     */
    private static boolean hasDelimiter(String commandArgs, String delimiter) {
        int delimiterIndex = commandArgs.indexOf(delimiter);
        return delimiterIndex != INVALID_INDEX_INDICATOR;
    }

    /**
     * Checks if the provided String object, which represents a single token in the command arguments, is a delimiter
     * that is used in any of the commands available in SplitLah.
     * 
     * @param token A String object representing a sequence of characters that are isolated by whitespaces in the
     *              command arguments.
     * @return true if the provided String object is a delimiter used in any of the commands available in SplitLah,
     *         false otherwise.
     */
    private static boolean isValidDelimiter(String token) {
        if (token == null) {
            return false;
        }
        
        switch (token) {
        case NAME_DELIMITER:
            // Fallthrough
        case PERSON_LIST_DELIMITER:
            // Fallthrough
        case INVOLVED_DELIMITER:
            // Fallthrough
        case PAYER_DELIMITER:
            // Fallthrough
        case SESSION_ID_DELIMITER:
            // Fallthrough
        case ACTIVITY_ID_DELIMITER:
            // Fallthrough
        case GROUP_ID_DELIMITER:
            // Fallthrough
        case DATE_DELIMITER:
            // Fallthrough
        case TOTAL_COST_DELIMITER:
            // Fallthrough
        case COST_LIST_DELIMITER:
            // Fallthrough
        case GST_DELIMITER:
            // Fallthrough
        case SERVICE_CHARGE_DELIMITER:
            return true;
        default:
            return false;
        }
    }

    /**
     * Checks whether the provided String object which represents the command arguments contains any invalid
     * delimiters.
     * 
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return true if there are tokens in the command arguments that contains a forward slash character ('/') but is
     *         not a valid delimiter used in any commands in SplitLah,
     *         false otherwise.
     */
    private static boolean containsInvalidDelimiters(String commandArgs) {
        if (commandArgs == null) {
            return false;
        }
        
        String[] argumentTokens = commandArgs.split(REGEX_WHITESPACES_DELIMITER);
        for (String token : argumentTokens) {
            if (token.contains(DELIMITER_INDICATOR) && !isValidDelimiter(token)) {
                return true;
            }
        }
        return false;
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
     * @throws InvalidFormatException If the Payer delimiter is not found in the command arguments, or
     *                                if no arguments representing a name were provided after the Payer delimiter.
     */
    public static String parsePayer(String commandArgs) throws InvalidFormatException {
        return getArgumentFromDelimiter(commandArgs, PAYER_DELIMITER);
    }

    /**
     * Returns an integer that represents a session unique identifier, given the command arguments from user input, 
     * delimited by the Session ID delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return An integer that represents a session unique identifier.
     * @throws InvalidFormatException If the Session ID delimiter is not found in the command arguments, or
     *                                if no arguments representing a session unique identifier were provided after the 
     *                                Session ID delimiter.
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
     * @throws InvalidFormatException If the Activity ID delimiter is not found in the command arguments, or
     *                                if no arguments representing an activity unique identifier were provided after the 
     *                                Activity ID delimiter.
     */
    public static int parseActivityId(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, ACTIVITY_ID_DELIMITER);
        return parseIdFromString(argument, ACTIVITY_ID_DELIMITER);
    }

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

    public static double parseTotalCost(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, TOTAL_COST_DELIMITER);
        return parseCostFromString(argument, TOTAL_COST_DELIMITER);
    }

    public static double[] parseCostList(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, COST_LIST_DELIMITER);
        String[] costStrings = argument.split(REGEX_WHITESPACES_DELIMITER);
        double[] costs = new double[costStrings.length];
        for (int i = 0; i < costStrings.length; i++) {
            costs[i] = parseCostFromString(costStrings[i], COST_LIST_DELIMITER);
        }
        return costs;
    }

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
    public static String getRemainingArgument(String commandArgs) {
        String[] commandTokens = commandArgs.trim().split(REGEX_WHITESPACES_DELIMITER, COMMAND_WITH_ARGS_TOKEN_COUNT);
        if (commandTokens.length < COMMAND_WITH_ARGS_TOKEN_COUNT) {
            return "";
        }
        return commandTokens[2];
    }

    public static String getCommandType(String commandArgs) {
        String[] commandTokens = commandArgs.trim().split(REGEX_WHITESPACES_DELIMITER, COMMAND_WITH_ARGS_TOKEN_COUNT);
        
        if (commandTokens.length < DELIMITERED_COMMAND_MIN_TOKEN_COUNT) {
            return commandTokens[0];
        } else if (!commandTokens[1].startsWith(DELIMITER_INDICATOR)) {
            return null;
        }
        return commandTokens[0] + " " + commandTokens[1];
    }

    public static Command getCommand(String input) {
        String commandType = getCommandType(input);
        String remainingArgs = getRemainingArgument(input);

        if (commandType == null) {
            return new InvalidCommand(Message.ERROR_PARSER_INVALID_COMMAND);
        }
        
        if (containsInvalidDelimiters(remainingArgs)) {
            return new InvalidCommand(Message.ERROR_PARSER_INVALID_DELIMITERS);
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
        case HelpCommand.COMMAND_TEXT:
            return new HelpCommand();
        case ExitCommand.COMMAND_TEXT:
            return new ExitCommand();
        default:
            return new InvalidCommand(Message.ERROR_PARSER_INVALID_COMMAND);
        }
    }
}
