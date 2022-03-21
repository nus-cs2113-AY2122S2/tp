package seedu.splitlah.parser;

import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.command.ActivityDeleteCommand;
import seedu.splitlah.command.ActivityListCommand;
import seedu.splitlah.command.ActivityViewCommand;
import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.command.GroupCreateCommand;
import seedu.splitlah.command.GroupDeleteCommand;
import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.command.SessionDeleteCommand;
import seedu.splitlah.command.SessionListCommand;
import seedu.splitlah.command.SessionSummaryCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.ui.Message;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class ParserUtils {
    
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
    static final String DELIMITER_INDICATOR = "/";
    private static final String NEXT_DELIMITER_INDICATOR = " /";
    private static final int ZERO_INDEXING_OFFSET = 1;
    private static final int PERCENTAGE_ALLOWED_INTEGER_PLACES = 3;
    static final String REGEX_WHITESPACES_DELIMITER = "\\s+";
    static final int INVALID_INDEX_INDICATOR = -1;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
    static String getArgumentFromDelimiter(String commandArgs, String delimiter) throws InvalidFormatException {
        assert commandArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;
        assert delimiter != null : Message.ASSERT_PARSER_DELIMITER_NULL;
        
        int delimiterIndex = commandArgs.indexOf(delimiter);
        if (delimiterIndex == INVALID_INDEX_INDICATOR) {
            throw new InvalidFormatException(ParserErrors.getMissingDelimiterErrorMessage(delimiter));
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
            throw new InvalidFormatException(ParserErrors.getMissingArgumentErrorMessage(delimiter));
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
    static int parseIntFromString(String input, String delimiter) throws InvalidFormatException {
        assert input != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        assert delimiter != null : Message.ASSERT_PARSER_DELIMITER_NULL;
        
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(ParserErrors.getNonIntegerErrorMessage(delimiter));
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
    static int parseIdFromString(String input, String delimiter) throws InvalidFormatException {
        assert input != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        assert delimiter != null : Message.ASSERT_PARSER_DELIMITER_NULL;
        
        int idVal = parseIntFromString(input, delimiter);
        if (idVal <= 0) {
            throw new InvalidFormatException(Message.ERROR_PARSER_ID_VALUE_NOT_POSITIVE);
        }
        return idVal;
    }

    /**
     * Checks if the given String object representing a real number has at most two decimal places.
     * 
     * @param input A String object representing a real number.
     * @return true if the String object can be parsed as a double and 
     *         represents a real number has at most two decimal places,
     *         false otherwise.
     */
    private static boolean hasAtMostTwoDecimalPlaces(String input) {
        assert input != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        final int TWO_DECIMAL_PLACES = 2;
        
        try {
            double value = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        
        int indexOfDecimal = input.indexOf('.');
        if (indexOfDecimal == INVALID_INDEX_INDICATOR) {
            return true;
        }
        int decimalPlaces = input.length() - indexOfDecimal - ZERO_INDEXING_OFFSET;
        return decimalPlaces <= TWO_DECIMAL_PLACES;
    }

    /**
     * Checks if the given String object representing a real number has less than
     * a specified number of digits before decimal point.
     *
     * @param input  A String object representing a real number.
     * @param places An integer representing the maximum number of digits that the input value
     *               should have before the decimal point.
     * @return true if the String object can be parsed as a double and
     *         represents a real number that has at most the specified number of digits before decimal point,
     *         false otherwise.
     */
    private static boolean hasAtMostGivenIntegerPlaces(String input, int places) {
        assert input != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        assert places >= 0 : Message.ASSERT_PARSER_PLACES_NEGATIVE;
        
        try {
            double value = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            return false;
        }

        int numberChars = input.length();
        int indexOfDecimal = input.indexOf('.');
        if (indexOfDecimal == INVALID_INDEX_INDICATOR) {
            return numberChars <= places;
        }
        return indexOfDecimal <= places;
    }

    /**
     * Checks if the given String object representing a monetary value has at most twelve digits before decimal point.
     *
     * @param input A String object representing a monetary value.
     * @return true if the String object can be parsed as a double and
     *         represents a monetary value that has at most twelve digits before decimal point,
     *         false otherwise.
     */
    private static boolean hasAtMostTwelveIntegerPlaces(String input) {
        return hasAtMostGivenIntegerPlaces(input, 12);
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
     *                                and cannot be parsed as a double,
     *                                if the double parsed from the input String object is not a positive value,
     *                                if the parsed double has more than 2 decimal points, or
     *                                if the parsed double has more than 12 digits before the decimal point.
     */
    static double parseCostFromString(String input, String delimiter) throws InvalidFormatException {
        assert input != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        assert delimiter != null : Message.ASSERT_PARSER_DELIMITER_NULL;
        
        double cost;
        try {
            cost = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(ParserErrors.getNonMonetaryErrorMessage(delimiter));
        }
        
        if (cost <= 0) {
            throw new InvalidFormatException(Message.ERROR_PARSER_COST_NOT_POSITIVE);
        }
        if (!hasAtMostTwoDecimalPlaces(input)) {
            throw new InvalidFormatException(Message.ERROR_PARSER_COST_NOT_TWO_DP);
        }
        if (!hasAtMostTwelveIntegerPlaces(input)) {
            throw new InvalidFormatException(Message.ERROR_PARSER_COST_MORE_THAN_TWELVE_DIGITS_BEFORE_DP);
        }
        return cost;
    }

    /**
     * Returns a double representing a percentage value, represented by the provided input String object.
     *
     * @param input     A String object that contains numeric characters or a single decimal point character,
     *                  representing a percentage value.
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return An double representing a percentage value.
     * @throws InvalidFormatException If the provided input String object contains characters other than numeric
     *                                characters or a single decimal point character,
     *                                and cannot be parsed as a double,
     *                                if the double parsed from the input String object is not a positive value,
     *                                if the parsed double has more than 2 decimal points, or
     *                                if the parsed double has more than 3 digits before the decimal point.
     */
    static double parsePercentageFromString(String input, String delimiter) throws InvalidFormatException {
        assert input != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        assert delimiter != null : Message.ASSERT_PARSER_DELIMITER_NULL;

        double percentage;
        try {
            percentage = Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(ParserErrors.getNonPercentageErrorMessage(delimiter));
        }

        if (percentage < 0) {
            throw new InvalidFormatException(Message.ERROR_PARSER_PERCENTAGE_NEGATIVE);
        }
        if (!hasAtMostTwoDecimalPlaces(input)) {
            throw new InvalidFormatException(Message.ERROR_PARSER_PERCENTAGE_NOT_TWO_DP);
        }
        if (!hasAtMostGivenIntegerPlaces(input, PERCENTAGE_ALLOWED_INTEGER_PLACES)) {
            throw new InvalidFormatException(Message.ERROR_PARSER_PERCENTAGE_MORE_THAN_THREE_DIGITS_BEFORE_DP);
        }
        return percentage;
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
    static boolean hasDelimiter(String commandArgs, String delimiter) {
        assert commandArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;
        assert delimiter != null : Message.ASSERT_PARSER_DELIMITER_NULL;
        
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
        assert token != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        
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
        assert commandArgs != null : Message.ASSERT_PARSER_TOKEN_INPUT_NULL;
        
        String[] argumentTokens = commandArgs.split(REGEX_WHITESPACES_DELIMITER);
        for (String token : argumentTokens) {
            if (token.contains(DELIMITER_INDICATOR) && !isValidDelimiter(token)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the provided String object which represents the command arguments contains any duplicate
     * delimiters.
     * 
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return true if there are tokens in the command arguments containing a forward slash character ('/') that
     *         appears twice or more times in the arguments,
     *         false otherwise.
     */
    private static boolean containsDuplicateDelimiters(String commandArgs) {
        assert commandArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;

        Set<String> delimiterSet = new HashSet<>();
        String[] argumentTokens = commandArgs.split(REGEX_WHITESPACES_DELIMITER);
        for (String token : argumentTokens) {
            if (token.contains(DELIMITER_INDICATOR) && !delimiterSet.add(token)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a String array object contains a String object 
     * with the same contents, ignoring case, as the specified String object.
     * 
     * @param stringToCheck A String object that is specified to be checked against the String array object.
     * @param stringArray   A String array object to be checked against.
     * @return true if stringArray contains a String object with the same contents as stringToCheck,
     *         false otherwise.
     */
    private static boolean hasStringInStringArray(String stringToCheck, String[] stringArray) {
        assert stringToCheck != null && stringArray != null : Message.ASSERT_PARSER_PARAMETERS_NULL;
        
        for (String string : stringArray) {
            if (string.equalsIgnoreCase(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether any delimiter inside the provided String object which represents the command arguments does not
     * belong to the command type specified by the String object which represents the command type.
     * 
     * @param commandType   A String object representing the command type of the command input from the user.
     * @param remainingArgs A String object containing the arguments portion of the entire command input from the user.
     * @return true if any delimiter inside remainingArgs do not belong to command type as represented by commandType,
     *         false otherwise.
     */
    private static boolean containsDelimitersNotFromCommand(String commandType, String remainingArgs) {
        assert commandType != null : Message.ASSERT_PARSER_COMMAND_TYPE_NULL;
        assert remainingArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;
        
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
        case GroupCreateCommand.COMMAND_TEXT:
            delimiterList = GroupCreateCommand.COMMAND_DELIMITERS;
            break;
        case GroupDeleteCommand.COMMAND_TEXT:
            delimiterList = GroupDeleteCommand.COMMAND_DELIMITERS;
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
    
    /**
     * Checks if the provided String object representing the command type of the command input from the user,
     * is a valid command type in SplitLah.
     *
     * @param commandType   A String object representing the command type of the command input from the user.
     * @return true if the provided String object is a command type available in SplitLah,
     *         false otherwise.
     */
    private static boolean isValidCommandType(String commandType) {
        assert commandType != null : Message.ASSERT_PARSER_COMMAND_TYPE_NULL;
        
        switch (commandType.toLowerCase()) {
        case SessionCreateCommand.COMMAND_TEXT:
            // Fallthrough
        case SessionDeleteCommand.COMMAND_TEXT:
            // Fallthrough
        case SessionSummaryCommand.COMMAND_TEXT:
            // Fallthrough
        case SessionListCommand.COMMAND_TEXT:
            // Fallthrough
        case ActivityCreateCommand.COMMAND_TEXT:
            // Fallthrough
        case ActivityDeleteCommand.COMMAND_TEXT:
            // Fallthrough
        case ActivityListCommand.COMMAND_TEXT:
            // Fallthrough
        case ActivityViewCommand.COMMAND_TEXT:
            // Fallthrough
        case GroupCreateCommand.COMMAND_TEXT:
            // Fallthrough
        case GroupDeleteCommand.COMMAND_TEXT:
            // Fallthrough
        case HelpCommand.COMMAND_TEXT:
            // Fallthrough
        case ExitCommand.COMMAND_TEXT:
            // Fallthrough
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns a String object that represents a corresponding error message for the first error check to be failed,
     * when checking for validity of arguments for a specified command.
     * 
     * @param commandType   A String object representing the command type of the command input from the user.
     * @param remainingArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A String object with the first error check to be failed, if any, or
     *         an empty String object if remainingArgs is empty or if none of the error checks fail.
     */
    static String checkIfArgumentsValidForCommand(String commandType, String remainingArgs) {
        assert commandType != null : Message.ASSERT_PARSER_COMMAND_TYPE_NULL;
        assert remainingArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;
        
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

    /**
     * Returns a String object that represents a corresponding error message for the first error check to be failed,
     * when checking for validity of the command type followed by the validity of the command arguments.
     * 
     * @param commandType   A String object representing the command type of the command input from the user.
     * @param remainingArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A String object with the first error check to be failed, if any, or
     *         an empty String object if remainingArgs is empty or if none of the error checks fail.
     */
    static String checkIfCommandIsValid(String commandType, String remainingArgs) {
        assert commandType != null : Message.ASSERT_PARSER_COMMAND_TYPE_NULL;
        assert remainingArgs != null : Message.ASSERT_PARSER_COMMAND_ARGUMENTS_NULL;

        if (commandType.isEmpty()) {
            return Message.ERROR_PARSER_EMPTY_COMMAND;
        }
        
        if (!isValidCommandType(commandType)) {
            return Message.ERROR_PARSER_INVALID_COMMAND;
        }
        return checkIfArgumentsValidForCommand(commandType, remainingArgs);
    }
}
