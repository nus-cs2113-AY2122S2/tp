package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.ActivityEditCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

/**
 * Represents a command parser that is able to parse user arguments into a ActivityEditCommand object.
 *
 * @author Saurav
 */
public class ActivityEditCommandParser implements CommandParser<ActivityEditCommand> {

    public static final String COMMAND_TEXT = "activity /edit";

    private static final String COMMAND_FORMAT = "Syntax:\n\t";

    private static final String COMMAND_FORMAT_FIRST =
            "activity /edit /sid [SESSION_ID] /aid [ACTIVITY_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/co <TOTAL_COST> [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    private static final String COMMAND_FORMAT_SECOND =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/cl [COST1 COST2…] [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

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
        int sessionId;
        int activityId;
        String activityName;
        String payer;
        String[] involvedList;
        double totalCost;
        double[] costList;
        double gst;
        double serviceCharge;
        assert commandArgs != null : Message.ASSERT_ACTIVITYEDIT_COMMAND_ARGS_NULL;

        try {
            activityId = Parser.parseActivityId(commandArgs);
            sessionId = Parser.parseSessionId(commandArgs);
            activityName = Parser.parseName(commandArgs);
            payer = Parser.parsePayer(commandArgs);
            involvedList = Parser.parseInvolved(commandArgs);
        } catch (InvalidFormatException exception) {
            throw (new InvalidFormatException(exception.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND));
        }

        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException e) {
            totalCost = -1;
        }

        try {
            costList = Parser.parseCostList(commandArgs);
        } catch (InvalidFormatException e) {
            costList = null;
        }

        try {
            gst = Parser.parseGst(commandArgs);
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        try {
            serviceCharge = Parser.parseServiceCharge(commandArgs);
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        return new ActivityEditCommand(sessionId, activityId, activityName, payer, involvedList, totalCost,
                costList, gst, serviceCharge);
    }
}
