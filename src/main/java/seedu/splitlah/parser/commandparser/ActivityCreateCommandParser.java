package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.ActivityCreateCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

/**
 * Represents a command parser that is able to parse user arguments into an ActivityCreateCommand object.
 *
 * @author Ivan
 */
public class ActivityCreateCommandParser implements CommandParser<ActivityCreateCommand> {

    public static final String COMMAND_TEXT = "activity /create";

    public static final String COMMAND_FORMAT = "Syntax:\n\t";

    public static final String COMMAND_FORMAT_FIRST =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] "
                    + "/co [TOTAL_COST] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]";

    public static final String COMMAND_FORMAT_SECOND =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] "
                    + "/cl [COST1 COST2...] [</gst [GST_PERCENTAGE]>] [</sc [SERVICE_CHARGE]>]";

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

    private String[] involvedList;
    private double totalCost = 0;
    private double[] costList = null;

    /**
     * Returns a ActivityCreateCommand object after parsing the input arguments from the user.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return An ActivityCreateCommand object if all necessary parameters required are found in the input arguments.
     * @throws InvalidFormatException If at least one of the necessary parameters cannot be found
     *                                in the input arguments.
     */
    @Override
    public ActivityCreateCommand getCommand(String commandArgs) throws InvalidFormatException {
        int sessionId;
        String activityName;
        String payer;
        double gst;
        double serviceCharge;

        try {
            sessionId = Parser.parseSessionId(commandArgs);
            activityName = Parser.parseName(commandArgs);
            payer = Parser.parsePayer(commandArgs);
            involvedList = Parser.parseInvolved(commandArgs);
        } catch (InvalidFormatException e) {
            String invalidMessage = e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t"
                    + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }

        boolean isMissingCost = isMissingCost(commandArgs);
        boolean isMissingCostList = isMissingCostList(commandArgs);
        boolean hasDifferentLength = false;
        checkIfMissingBothCostAndCostList(isMissingCost, isMissingCostList);
        checkIfHasBothCostAndCostList(isMissingCost, isMissingCostList);
        checkIfHasDifferentLength(isMissingCost, hasDifferentLength);

        try {
            gst = Parser.parseGst(commandArgs);
            serviceCharge = Parser.parseServiceCharge(commandArgs);
        } catch (InvalidFormatException e) {
            String invalidMessage = e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }

        return new ActivityCreateCommand(sessionId, activityName, totalCost, payer, involvedList, costList, gst,
                serviceCharge);
    }

    private void checkIfHasDifferentLength(boolean isMissingCost, boolean hasDifferentLength)
            throws InvalidFormatException {
        if (isMissingCost) {
            hasDifferentLength = involvedList.length != costList.length;
        }
        if (hasDifferentLength) {
            String invalidMessage = Message.ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }
    }

    private void checkIfHasBothCostAndCostList(boolean isMissingCost, boolean isMissingCostList)
            throws InvalidFormatException {
        boolean hasBothCostAndCostList = !isMissingCostList && !isMissingCost;
        if (hasBothCostAndCostList) {
            String invalidMessage = Message.ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }
    }

    private void checkIfMissingBothCostAndCostList(boolean isMissingCost, boolean isMissingCostList)
            throws InvalidFormatException {
        boolean hasMissingCostAndMissingCostList = isMissingCostList && isMissingCost;
        if (hasMissingCostAndMissingCostList) {
            String invalidMessage = Message.ERROR_ACTIVITYCREATE_MISSING_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }
    }

    private boolean isMissingCostList(String commandArgs) throws InvalidFormatException {
        try {
            costList = Parser.parseCostList(commandArgs);
            return false;
        } catch (InvalidFormatException e) {
            if (!e.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + ParserUtils.COST_LIST_DELIMITER)) {
                String invalidMessage = e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                        + "\n\t" + COMMAND_FORMAT_SECOND;
                throw new InvalidFormatException(invalidMessage);
            }
            return true;
        }
    }

    private boolean isMissingCost(String commandArgs) throws InvalidFormatException {
        try {
            totalCost = Parser.parseTotalCost(commandArgs);
            return false;
        } catch (InvalidFormatException e) {
            if (!e.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + ParserUtils.TOTAL_COST_DELIMITER)) {
                String invalidMessage = e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                        + "\n\t" + COMMAND_FORMAT_SECOND;
                throw new InvalidFormatException(invalidMessage);
            }
            return true;
        }
    }
}
