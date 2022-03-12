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
    private static String getMissingDelimiterErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_DELIMITER_NOT_FOUND + delimiter;
    }
    
    private static String getMissingArgumentErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_MISSING_ARGUMENT + delimiter;
    }
    
    private static String getNonIntegerErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_NON_INTEGER_ARGUMENT + delimiter;
    }
    
    private static String getNonMonetaryErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT + delimiter;
    }
    
    private static String getInvalidGstErrorMessage() {
        return Message.ERROR_PARSER_INVALID_GST_SURCHARGE + GST_DELIMITER;
    }
    
    private static String getInvalidServiceChargeErrorMessage() {
        return Message.ERROR_PARSER_INVALID_SERVICE_CHARGE + SERVICE_CHARGE_DELIMITER;
    }

    // SUPPORTING FUNCTIONS
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

    private static int parseIntFromString(String input, String delimiter) throws InvalidFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(getNonIntegerErrorMessage(delimiter));
        }
    }

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

    private static boolean hasDelimiter(String commandArgs, String delimiter) {
        int delimiterIndex = commandArgs.indexOf(delimiter);
        return delimiterIndex != INVALID_INDEX_INDICATOR;
    }
    
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
    public static String parseName(String commandArgs) throws InvalidFormatException {
        return getArgumentFromDelimiter(commandArgs, NAME_DELIMITER);
    }

    public static String[] parsePersonList(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, PERSON_LIST_DELIMITER);
        return argument.split(REGEX_WHITESPACES_DELIMITER);
    }

    public static String[] parseInvolved(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, INVOLVED_DELIMITER);
        return argument.split(REGEX_WHITESPACES_DELIMITER);
    }

    public static String parsePayer(String commandArgs) throws InvalidFormatException {
        return getArgumentFromDelimiter(commandArgs, PAYER_DELIMITER);
    }
    
    public static int parseSessionId(String commandArgs) throws InvalidFormatException {
        String argument = getArgumentFromDelimiter(commandArgs, SESSION_ID_DELIMITER);
        return parseIdFromString(argument, SESSION_ID_DELIMITER);
    }

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
            // TEMPORARY INVALID COMMAND UNTIL COMMAND DEVELOPED
            return new InvalidCommand("activity /delete is not yet developed, please try again later.");
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
