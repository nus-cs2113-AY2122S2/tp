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
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a parser that interprets the user input into data that can be understood by the program.
 * 
 * @author Warren
 */
public class Parser {
    
    // DELIMITERS
    public static final String NAME_DELIMITER = "/n";
    public static final String PERSON_LIST_DELIMITER = "/pl";
    public static final String INVOLVED_DELIMITER = "/i";
    public static final String PAYER_DELIMITER = "/p";
    public static final String SESSION_ID_DELIMITER = "/sid";
    public static final String ACTIVITY_ID_DELIMITER = "/aid";
    public static final String GROUP_ID_DELIMITER = "/gid";
    public static final String DATE_DELIMITER = "/d";
    public static final String TOTAL_COST_DELIMITER = "/co";
    public static final String COST_LIST_DELIMITER = "/cl";
    public static final String GST_DELIMITER = "/gst";
    public static final String SERVICE_CHARGE_DELIMITER = "/sc";

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

    /**
     * Checks if the given String object representing a monetary value has at most two decimal places.
     * 
     * @param input A String object representing a monetary value.
     * @return true if the String object can be parsed as a double and 
     *         represents a monetary value has at most two decimal places,
     *         false otherwise.
     */
    private static boolean hasAtMostTwoDecimalPlaces(String input) {
        try {
            double cost = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        
        int indexOfDecimal = input.indexOf('.');
        int decimalPlaces = input.length() - indexOfDecimal - 1;
        return decimalPlaces <= 2;
    }
    
    /**
     * Returns a double representing a cost value, represented by the provided input String object.
     *
     * @param input     A String object that contains numeric characters or a single decimal point character,
     *                  representing a cost value.
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return An double representing a cost value.
     * @throws InvalidFormatException If the provided input String object contains characters other than numeric
     *                                characters or a single decimal point character,
     *                                and cannot be parsed as a double, or
     *                                if the double parsed from the input String object is not a positive value.
     */
    private static double parseCostFromString(String input, String delimiter) throws InvalidFormatException {
        double cost;
        try {
            cost = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(getNonMonetaryErrorMessage(delimiter));
        }
        
        if (cost <= 0) {
            throw new InvalidFormatException(Message.ERROR_PARSER_COST_NOT_POSITIVE);
        }
        if (!hasAtMostTwoDecimalPlaces(input)) {
            throw new InvalidFormatException(Message.ERROR_PARSER_COST_NOT_TWO_DP);
        }
        return cost;
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

    private static boolean containsDuplicateDelimiters(String commandArgs) {
        if (commandArgs == null) {
            return false;
        }

        Set<String> delimiterSet = new HashSet<>();
        String[] argumentTokens = commandArgs.split(REGEX_WHITESPACES_DELIMITER);
        for (String token : argumentTokens) {
            if (token.contains(DELIMITER_INDICATOR) && !delimiterSet.add(token)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasStringInStringArray(String stringToCheck, String[] stringArray) {
        if (stringToCheck == null || stringArray == null) {
            return false;
        }
        
        for (String string : stringArray) {
            if (string.equalsIgnoreCase(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDelimitersNotFromCommand(String commandType, String remainingArgs) {
        String[] delimiterList;
        switch (commandType) {
        case ActivityCreateCommand.COMMAND_TEXT:
            delimiterList = ActivityCreateCommand.COMMAND_DELIMITERS;
            break;
        case ActivityDeleteCommand.COMMAND_TEXT:
            delimiterList = ActivityDeleteCommand.COMMAND_DELIMITERS;
            break;
        case ActivityListCommand.COMMAND_TEXT:
            delimiterList = ActivityListCommand.COMMAND_DELIMITERS;
            break;
        case ActivityViewCommand.COMMAND_TEXT:
            delimiterList = ActivityViewCommand.COMMAND_DELIMITERS;
            break;
        case SessionCreateCommand.COMMAND_TEXT:
            delimiterList = SessionCreateCommand.COMMAND_DELIMITERS;
            break;
        case SessionDeleteCommand.COMMAND_TEXT:
            delimiterList = SessionDeleteCommand.COMMAND_DELIMITERS;
            break;
        case SessionSummaryCommand.COMMAND_TEXT:
            delimiterList = SessionSummaryCommand.COMMAND_DELIMITERS;
            break;
        default:
            return !remainingArgs.isEmpty();
        }

        String[] argumentTokens = remainingArgs.split(REGEX_WHITESPACES_DELIMITER);
        for (String token : argumentTokens) {
            if (token.contains(DELIMITER_INDICATOR) && !hasStringInStringArray(token, delimiterList)) {
                return true;
            }
        }
        return false;
    }

    private static String checkIfArgumentsValidForCommand(String commandType, String remainingArgs) {
        if (remainingArgs.isEmpty()) {
            return "";
        } else if (!remainingArgs.startsWith(DELIMITER_INDICATOR)) {
            return Message.ERROR_PARSER_ADDITIONAL_INVALID_TOKEN;
        } else if (containsInvalidDelimiters(remainingArgs)
                || containsDelimitersNotFromCommand(commandType, remainingArgs)) {
            return Message.ERROR_PARSER_INVALID_DELIMITERS;
        } else if (containsDuplicateDelimiters(remainingArgs)) {
            return Message.ERROR_PARSER_DUPLICATE_DELIMITERS;
        } else {
            return "";
        }
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
     *                                if the arguments cannot be parsed as a double, or
     *                                if the cost value parsed is not positive.
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
     *                                if any cost value parsed is not positive.
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
     * @throws InvalidFormatException If the GST delimiter is not found in the command arguments,
     *                                if no arguments representing a GST charge were provided after the 
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
     * @throws InvalidFormatException If the Service charge delimiter is not found in the command arguments,
     *                                if no arguments representing a service charge were provided after the 
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
        case HelpCommand.COMMAND_TEXT:
            return new HelpCommand();
        case ExitCommand.COMMAND_TEXT:
            return new ExitCommand();
        default:
            return new InvalidCommand(Message.ERROR_PARSER_INVALID_COMMAND);
        }
    }
}
