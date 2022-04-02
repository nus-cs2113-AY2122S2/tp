package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.ActivityEditCommand;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.data.Activity;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserErrors;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

/**
 * Represents a command parser that is able to parse user arguments into a ActivityEditCommand object.
 *
 * @author Saurav
 */
public class ActivityEditCommandParser implements CommandParser<ActivityEditCommand> {

    public static final String COMMAND_TEXT = "activity /edit";

    private static final String COMMAND_FORMAT = "Syntax:\n\t";

    public static final String COMMAND_FORMAT_FIRST =
            "activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] "
                    + "/co <TOTAL_COST> [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    public static final String COMMAND_FORMAT_SECOND =
            "activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2...] "
                    + "/cl [COST1 COST2...] [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.SESSION_ID_DELIMITER,
        ParserUtils.ACTIVITY_ID_DELIMITER,
        ParserUtils.NAME_DELIMITER,
        ParserUtils.PAYER_DELIMITER,
        ParserUtils.INVOLVED_DELIMITER,
        ParserUtils.TOTAL_COST_DELIMITER,
        ParserUtils.COST_LIST_DELIMITER,
        ParserUtils.GST_DELIMITER,
        ParserUtils.SERVICE_CHARGE_DELIMITER
    };

    /**
     * Returns an ActivityEditCommand object from the supplied command arguments.
     *
     * @param commandArgs A String object representing arguments provided by the user.
     * @return An ActivityEditCommand object if all necessary parameters required are found in the input arguments.
     * @throws InvalidFormatException If at least one of the necessary parameters cannot be found
     *                                in the input arguments.
     */
    @Override
    public ActivityEditCommand getCommand(String commandArgs) throws InvalidFormatException {
        assert commandArgs != null : Message.ASSERT_ACTIVITYEDIT_COMMAND_ARGS_NULL;
        int sessionId;
        int activityId;
        String activityName = null;
        String payer = null;
        String[] involvedList = null;
        double totalCost = -1;
        double[] costList = null;
        double gst = -1;
        double serviceCharge = -1;

        try {
            sessionId = Parser.parseSessionId(commandArgs);
            activityId = Parser.parseActivityId(commandArgs);
        } catch (InvalidFormatException exception) {
            String invalidMessage = exception.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }

        try {
            activityName = Parser.parseName(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/n"))) {
                // activityName not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }

        try {
            payer = Parser.parsePayer(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/p"))) {
                // payer not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }

        try {
            involvedList = Parser.parseInvolved(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/i"))) {
                // involvedList not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }


        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/co"))) {
                // totalCost not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }

        try {
            costList = Parser.parseCostList(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/cl"))) {
                // costList not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }

        if (costList != null && totalCost != -1) {
            String invalidMessage = Message.ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND;
            throw new InvalidFormatException(invalidMessage);
        }

        try {
            gst = Parser.parseGstIncludingZero(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/gst"))) {
                // gst not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }

        try {
            serviceCharge = Parser.parseServiceChargeIncludingZero(commandArgs);
        } catch (InvalidFormatException exception) {
            if (exception.getMessage().equals(ParserErrors.getMissingDelimiterErrorMessage("/sc"))) {
                // serviceCharge not provided by user.
            } else {
                throw new InvalidFormatException(exception.getMessage());
            }
        }

        return new ActivityEditCommand(sessionId, activityId, activityName, payer, involvedList, totalCost,
                costList, gst, serviceCharge);
    }
}
