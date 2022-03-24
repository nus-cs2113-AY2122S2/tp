package seedu.splitlah.parser;

import seedu.splitlah.command.Command;
import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.command.ActivityDeleteCommand;
import seedu.splitlah.command.ActivityListCommand;
import seedu.splitlah.command.ActivityViewCommand;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.command.SessionDeleteCommand;
import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.command.GroupCreateCommand;
import seedu.splitlah.command.GroupDeleteCommand;
import seedu.splitlah.command.GroupListCommand;
import seedu.splitlah.command.GroupViewCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.commandparser.HelpCommandParser;
import seedu.splitlah.parser.commandparser.SessionListCommandParser;
import seedu.splitlah.parser.commandparser.SessionSummaryCommandParser;
import seedu.splitlah.parser.commandparser.ActivityCreateCommandParser;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    static final double MINIMUM_SURCHARGE_PERCENT = 0;
    static final double MAXIMUM_SURCHARGE_PERCENT = 100;
    
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
        return ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.NAME_DELIMITER);
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.PERSON_LIST_DELIMITER);
        return argument.split(ParserUtils.REGEX_WHITESPACES_DELIMITER);
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.INVOLVED_DELIMITER);
        return argument.split(ParserUtils.REGEX_WHITESPACES_DELIMITER);
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
        String payer = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.PAYER_DELIMITER);
        if (payer.indexOf(' ') != ParserUtils.INVALID_INDEX_INDICATOR) {
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.SESSION_ID_DELIMITER);
        return ParserUtils.parseIdFromString(argument, ParserUtils.SESSION_ID_DELIMITER);
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.ACTIVITY_ID_DELIMITER);
        return ParserUtils.parseIdFromString(argument, ParserUtils.ACTIVITY_ID_DELIMITER);
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.GROUP_ID_DELIMITER);
        return ParserUtils.parseIdFromString(argument, ParserUtils.GROUP_ID_DELIMITER);
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
        if (!ParserUtils.hasDelimiter(commandArgs, ParserUtils.DATE_DELIMITER)) {
            throw new InvalidFormatException(ParserErrors.getMissingDelimiterErrorMessage(ParserUtils.DATE_DELIMITER));
        }

        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.DATE_DELIMITER);
        if (argument.equalsIgnoreCase(LOCALDATE_TODAY_INDICATOR)) {
            return LocalDate.now();
        }
        
        try {
            return LocalDate.parse(argument, ParserUtils.DATE_FORMAT);
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.TOTAL_COST_DELIMITER);
        return ParserUtils.parseCostFromString(argument, ParserUtils.TOTAL_COST_DELIMITER);
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
        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.COST_LIST_DELIMITER);
        String[] costStrings = argument.split(ParserUtils.REGEX_WHITESPACES_DELIMITER);
        double[] costs = new double[costStrings.length];
        for (int i = 0; i < costStrings.length; i++) {
            costs[i] = ParserUtils.parseCostFromString(costStrings[i], ParserUtils.COST_LIST_DELIMITER);
        }
        return costs;
    }

    /**
     * Returns a double that represents the GST charge in percents, given the command arguments from user input, 
     * delimited by the GST delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A double that represents a GST charge in percents.
     * @throws InvalidFormatException If no arguments representing a GST charge were provided after the 
     *                                GST delimiter,
     *                                if the argument cannot be parsed as a double,
     *                                if the parsed percentage has more than 2 decimal points,
     *                                if the parsed percentage has more than 3 digits before the decimal point, or
     *                                if the parsed percentage is not in [0, 100].
     */
    public static double parseGst(String commandArgs) throws InvalidFormatException {
        if (!ParserUtils.hasDelimiter(commandArgs, ParserUtils.GST_DELIMITER)) {
            return 0;
        }

        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.GST_DELIMITER);
        double gst = ParserUtils.parsePercentageFromString(argument, ParserUtils.GST_DELIMITER);
        if (gst < MINIMUM_SURCHARGE_PERCENT || gst > MAXIMUM_SURCHARGE_PERCENT) {
            throw new InvalidFormatException(ParserErrors.getInvalidGstErrorMessage());
        }
        return gst;
    }

    /**
     * Returns a double that represents the service charge in percents, given the command arguments from user input, 
     * delimited by the Service charge delimiter.
     *
     * @param commandArgs A String object containing the arguments portion of the entire command input from the user.
     * @return A double that represents a service charge in percents.
     * @throws InvalidFormatException If no arguments representing a service charge were provided after the 
     *                                Service charge delimiter,
     *                                if the argument cannot be parsed as a double,
     *                                if the parsed percentage has more than 2 decimal points,
     *                                if the parsed percentage has more than 3 digits before the decimal point, or
     *                                if the parsed percentage is not in [0, 100].
     */
    public static double parseServiceCharge(String commandArgs) throws InvalidFormatException {
        if (!ParserUtils.hasDelimiter(commandArgs, ParserUtils.SERVICE_CHARGE_DELIMITER)) {
            return 0;
        }

        String argument = ParserUtils.getArgumentFromDelimiter(commandArgs, ParserUtils.SERVICE_CHARGE_DELIMITER);
        double serviceCharge = ParserUtils.parsePercentageFromString(argument, ParserUtils.SERVICE_CHARGE_DELIMITER);
        if (serviceCharge < MINIMUM_SURCHARGE_PERCENT || serviceCharge > MAXIMUM_SURCHARGE_PERCENT) {
            throw new InvalidFormatException(ParserErrors.getInvalidServiceChargeErrorMessage());
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
        String[] commandTokens =
                commandString.trim().split(ParserUtils.REGEX_WHITESPACES_DELIMITER, COMMAND_WITH_ARGS_TOKEN_COUNT);
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
        String[] commandTokens =
                commandString.trim().split(ParserUtils.REGEX_WHITESPACES_DELIMITER, COMMAND_WITH_ARGS_TOKEN_COUNT);
        
        if (commandTokens.length < DELIMITERED_COMMAND_MIN_TOKEN_COUNT) {
            return commandTokens[0];
        } else if (!commandTokens[1].startsWith(ParserUtils.DELIMITER_INDICATOR)) {
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
        
        String errorMessage = ParserUtils.checkIfCommandIsValid(commandType, remainingArgs);
        if (!errorMessage.isEmpty()) {
            return new InvalidCommand(errorMessage);
        }
        
        try {
            switch (commandType.toLowerCase()) {
            case SessionCreateCommand.COMMAND_TEXT:
                return SessionCreateCommand.prepare(remainingArgs);
            case SessionDeleteCommand.COMMAND_TEXT:
                return SessionDeleteCommand.prepare(remainingArgs);
            case SessionSummaryCommandParser.COMMAND_TEXT:
                return new SessionSummaryCommandParser().getCommand(remainingArgs);
            case SessionListCommandParser.COMMAND_TEXT:
                return new SessionListCommandParser().getCommand(remainingArgs);
            case ActivityCreateCommandParser.COMMAND_TEXT:
                return new ActivityCreateCommandParser().getCommand(remainingArgs);
            case ActivityDeleteCommand.COMMAND_TEXT:
                return ActivityDeleteCommand.prepare(remainingArgs);
            case ActivityListCommand.COMMAND_TEXT:
                return ActivityListCommand.prepare(remainingArgs);
            case ActivityViewCommand.COMMAND_TEXT:
                return ActivityViewCommand.prepare(remainingArgs);
            case GroupCreateCommand.COMMAND_TEXT:
                return GroupCreateCommand.prepare(remainingArgs);
            case GroupDeleteCommand.COMMAND_TEXT:
                return GroupDeleteCommand.prepare(remainingArgs);
            case GroupListCommand.COMMAND_TEXT:
                return new GroupListCommand();
            case GroupViewCommand.COMMAND_TEXT:
                return GroupViewCommand.prepare(remainingArgs);
            case HelpCommandParser.COMMAND_TEXT:
                return new HelpCommandParser().getCommand(remainingArgs);
            case ExitCommand.COMMAND_TEXT:
                return new ExitCommand();
            default:
                return new InvalidCommand(Message.ERROR_PARSER_INVALID_COMMAND);
            }
        } catch (InvalidFormatException exception) {
            return new InvalidCommand(exception.getMessage());
        }
    }
}
