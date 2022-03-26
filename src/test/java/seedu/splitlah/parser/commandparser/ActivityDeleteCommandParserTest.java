package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
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

        // Case 2: Missing /aid delimiter
        String inputMissingActivityIdDelimiter = "activity /delete /sid 1";
        String argsMissingActivityIdDelimiter = Parser.getRemainingArgument(inputMissingActivityIdDelimiter);
        String errorMessageTwo = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingActivityIdDelimiter);
        if (!errorMessageTwo.isEmpty()) {
            fail();
        }

        try {
            activityDeleteCommandParser.getCommand(argsMissingActivityIdDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/aid"
                    + "\n" + ActivityDeleteCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, e.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing arguments are detected in the user input.
     */
    @Test
    public void getCommand_hasMissingArguments_InvalidFormatExceptionThrown() {
        ActivityDeleteCommandParser activityDeleteCommandParser = new ActivityDeleteCommandParser();

        // Case 1: Missing session ID
        String inputMissingSessionIdArgument = "activity /delete /sid /aid 1";
        String argsMissingSessionIdArgument = Parser.getRemainingArgument(inputMissingSessionIdArgument);
        String errorMessageOne = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingSessionIdArgument);
        if (!errorMessageOne.isEmpty()) {
            fail();
        }

        try {
            activityDeleteCommandParser.getCommand(argsMissingSessionIdArgument);
            fail();
        } catch (InvalidFormatException e) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/aid"
                    + "\n" + ActivityDeleteCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, e.getMessage());
        }
    }

}