package seedu.duke.parser;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.ui.Message;

/**
 * Represents an interpreter that interprets different parts of the user input
 * into data that can be understood by the program.
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
    private static final String NEXT_DELIMITER_INDICATOR = " /";
    private static final String REGEX_WHITESPACES_DELIMITER = "\\s+";
    private static final int INVALID_INDEX_INDICATOR = -1;
    private static final int COMMAND_WITH_ARGS_TOKEN_COUNT = 3;
    private static final int MINIMUM_SURCHARGE_PERCENT = 0;
    private static final int MAXIMUM_SURCHARGE_PERCENT = 100;

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
    
    private static String getInvalidGSTErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_INVALID_GST_SURCHARGE + delimiter;
    }
    
    private static String getInvalidServiceChargeErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_INVALID_SERVICE_CHARGE + delimiter;
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
    
    private static double parseCostFromString(String input, String delimiter) throws InvalidFormatException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            throw new InvalidFormatException(getNonMonetaryErrorMessage(delimiter));
        }
    }
}
