package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

public class ActivityCreateCommandParser implements CommandParser<ActivityCreateCommand> {

    public static final String COMMAND_TEXT = "activity /create";

    private static final String COMMAND_FORMAT = "Syntax:\n\t";

    public static final String COMMAND_FORMAT_FIRST =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/co <TOTAL_COST> [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    public static final String COMMAND_FORMAT_SECOND =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/cl [COST1 COST2…] [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    public static final String[] COMMAND_DELIMITERS = {
            ParserUtils.SESSION_ID_DELIMITER,
            ParserUtils.NAME_DELIMITER,
            ParserUtils.PAYER_DELIMITER,
            ParserUtils.INVOLVED_DELIMITER,
            ParserUtils.TOTAL_COST_DELIMITER,
            ParserUtils.COST_LIST_DELIMITER,
            ParserUtils.GST_DELIMITER,
            ParserUtils.SERVICE_CHARGE_DELIMITER
    };

    /**
     * Prepares user arguments for the creation of an ActivityCreateCommand object.
     *
     * @param commandArgs A String object representing the user's arguments.
     * @return An ActivityCreateCommand object if necessary parameters were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    @Override
    public ActivityCreateCommand getCommand(String commandArgs) throws InvalidFormatException {
        int sessionId;
        String activityName;
        String payer;
        String[] involvedList;
        double totalCost = 0;
        double[] costList = null;
        double gst;
        double serviceCharge;

        try {
            sessionId = Parser.parseSessionId(commandArgs);
            activityName = Parser.parseName(commandArgs);
            payer = Parser.parsePayer(commandArgs);
            involvedList = Parser.parseInvolved(commandArgs);
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t"
                    + COMMAND_FORMAT_SECOND);
        }

        boolean isMissingCost = false;
        boolean isMissingCostList = false;
        boolean hasDifferentLength = false;

        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException e) {
            if (!e.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + ParserUtils.TOTAL_COST_DELIMITER)) {
                throw new InvalidFormatException(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                        + "\n\t" + COMMAND_FORMAT_SECOND);
            }
            isMissingCost = true;
        }

        try {
            costList = Parser.parseCostList(commandArgs);
        } catch (InvalidFormatException e) {
            if (!e.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + ParserUtils.COST_LIST_DELIMITER)) {
                throw new InvalidFormatException(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                        + "\n\t" + COMMAND_FORMAT_SECOND);
            }
            isMissingCostList = true;
        }

        boolean hasMissingCostAndMissingCostList = isMissingCostList && isMissingCost;
        if (hasMissingCostAndMissingCostList) {
            throw new InvalidFormatException(Message.ERROR_ACTIVITYCREATE_MISSING_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        boolean hasBothCostAndCostList = !isMissingCostList && !isMissingCost;
        if (hasBothCostAndCostList) {
            throw new InvalidFormatException(Message.ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        if (isMissingCost) {
            hasDifferentLength = involvedList.length != costList.length;
        }
        if (hasDifferentLength) {
            throw new InvalidFormatException(Message.ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        try {
            gst = Parser.parseGst(commandArgs);
            serviceCharge = Parser.parseServiceCharge(commandArgs);
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        return new ActivityCreateCommand(sessionId, activityName, totalCost, payer, involvedList, costList, gst,
                serviceCharge);
    }
}
