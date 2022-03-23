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

    @Override
    public ActivityEditCommand getCommand(String commandArgs) throws InvalidFormatException {

        int sessionId = -1;
        int activityId = -1;
        String activityName = null;
        String payer = null;
        String[] involvedList = null;
        Double totalCost = null;
        assert commandArgs != null : Message.ASSERT_ACTIVITYEDIT_COMMAND_ARGS_NULL;

        try {
            activityId = Parser.parseActivityId(commandArgs);
            sessionId = Parser.parseSessionId(commandArgs);
        } catch (InvalidFormatException exception) {
            String invalidCommandMessage = exception.getMessage();
        }

        try {
            activityName = Parser.parseName(commandArgs);
        } catch (InvalidFormatException exception) {
            // do nothing...
        }

        try {
            payer = Parser.parsePayer(commandArgs);
        } catch (InvalidFormatException exception) {
            // do nothing...
        }

        try {
            involvedList = Parser.parseInvolved(commandArgs);
        } catch (InvalidFormatException exception) {
            // do nothing...
        }

        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException exception) {
            // do nothing...
        }

        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException exception) {
            // do nothing...
        }

        return new ActivityEditCommand(sessionId, activityId, activityName, payer, involvedList, totalCost);

    }
}
