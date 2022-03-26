package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ActivityDeleteCommandParserTest {

    private static final String COMMAND_TYPE = ActivityDeleteCommandParser.COMMAND_TEXT;

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing delimiters are detected in the user input.
     */
    @Test
    public void getCommand_hasMissingDelimiter_InvalidFormatExceptionThrown() {
        ActivityDeleteCommandParser activityDeleteCommandParser = new ActivityDeleteCommandParser();

        // Case 1: Missing /sid delimiter
        String inputMissingSessionIdDelimiter = "activity /delete /aid 1";
        String argsMissingSessionIdDelimiter = Parser.getRemainingArgument(inputMissingSessionIdDelimiter);
        String errorMessageOne = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingSessionIdDelimiter);
        if (!errorMessageOne.isEmpty()) {
            fail();
        }

        try {
            activityDeleteCommandParser.getCommand(argsMissingSessionIdDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/sid"
                    + "\n" + ActivityDeleteCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, e.getMessage());
        }
    }

    /**
     * Checks if an InvalidCommand object is returned when there are arguments not provided by the user.
     */
    @Test
    public void prepare_hasMissingArgument_InvalidCommand() {
        // Case 1: Missing both Session Id and Activity Id arguments
        String argsMissingBothArguments = "activity /delete /sid /aid";
        Command activityWithMissingBothArguments = Parser.getCommand(argsMissingBothArguments);
        assertEquals(InvalidCommand.class, activityWithMissingBothArguments.getClass());

        // Case 2: Missing Session Id argument only
        String argsMissingSessionIdArgument = "activity /delete /sid /aid 1";
        Command activityWithMissingSessionIdArgument = Parser.getCommand(argsMissingSessionIdArgument);
        assertEquals(InvalidCommand.class, activityWithMissingSessionIdArgument.getClass());

        // Case 3: Missing Activity Id argument only
        String argsMissingActivityIdArgument = "activity /delete /sid 1 /aid";
        Command activityWithMissingActivityIdArgument = Parser.getCommand(argsMissingActivityIdArgument);
        assertEquals(InvalidCommand.class, activityWithMissingActivityIdArgument.getClass());
    }

}