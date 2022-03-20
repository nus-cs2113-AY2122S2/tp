package seedu.splitlah.parser;

import seedu.splitlah.ui.Message;

public class ParserErrors {

    // ERROR REPORTING FUNCTIONS
    /**
     * Returns a String object containing an error message for a specified delimiter that is missing.
     * 
     * @param delimiter A String object that represents a demarcation of a specific argument in the command. 
     * @return A String object representing an error message for missing a specified delimiter in the input command.
     */
    static String getMissingDelimiterErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_DELIMITER_NOT_FOUND + delimiter;
    }

    /**
     * Returns a String object containing an error message for a missing argument that should follow after a specified
     * delimiter in the command input by the user.
     *
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return A String object representing an error message for missing an argument in the input command.
     */
    static String getMissingArgumentErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_MISSING_ARGUMENT + delimiter;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to read an input String object
     * as an integer.
     *
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return A String object representing an error message for a non-integer input.
     */
    static String getNonIntegerErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_NON_INTEGER_ARGUMENT + delimiter;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to read an input String object
     * as a double.
     *
     * @param delimiter A String object that represents a demarcation of a specific argument in the command.
     * @return A String object representing an error message for a non-double input.
     */
    static String getNonMonetaryErrorMessage(String delimiter) {
        return Message.ERROR_PARSER_NON_MONETARY_VALUE_ARGUMENT + delimiter;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to parse an input String object
     * as a valid Goods and Services Tax (GST) in percents.
     *
     * @return A String object representing an error message for an invalid GST input.
     */
    static String getInvalidGstErrorMessage() {
        return Message.ERROR_PARSER_INVALID_GST_SURCHARGE + ParserUtils.GST_DELIMITER;
    }

    /**
     * Returns a String object containing an error message when the parser is not able to parse an input String object
     * as a valid service charge in percents.
     *
     * @return A String object representing an error message for an invalid service charge input.
     */
    static String getInvalidServiceChargeErrorMessage() {
        return Message.ERROR_PARSER_INVALID_SERVICE_CHARGE + ParserUtils.SERVICE_CHARGE_DELIMITER;
    }
}
