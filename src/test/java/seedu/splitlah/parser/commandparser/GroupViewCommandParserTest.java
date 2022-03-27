package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GroupViewCommandParserTest {

    private static final String COMMAND_TYPE = GroupViewCommandParser.COMMAND_TEXT;

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing group delimiter is detected in the user input.
     */
    @Test
    public void getCommand_hasMissingGroupIdDelimiter_InvalidFormatExceptionThrown() {
        String inputMissingGroupIdDelimiter = "group /view";
        String argsMissingGroupIdDelimiter = Parser.getRemainingArgument(inputMissingGroupIdDelimiter);
        String errorMessageOne = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingGroupIdDelimiter);
        if (!errorMessageOne.isEmpty()) {
            fail();
        }

        GroupViewCommandParser groupViewCommandParser = new GroupViewCommandParser();
        try {
            groupViewCommandParser.getCommand(argsMissingGroupIdDelimiter);
            fail();
        } catch (InvalidFormatException e) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/gid"
                    + "\n" + GroupViewCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, e.getMessage());
        }
    }

}
