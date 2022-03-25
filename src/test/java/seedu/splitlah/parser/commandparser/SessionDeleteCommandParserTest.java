package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionDeleteCommandParserTest {

    private static final String COMMAND_TYPE = SessionDeleteCommandParser.COMMAND_TEXT;

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing delimiter is detected in the user input.
     */
    @Test
    public void getCommand_hasMissingDelimiter_InvalidFormatExceptionThrown() {
        String inputMissingSidDelimiter = "session /delete";
        String argsWithMissingSidDelimiter = Parser.getRemainingArgument(inputMissingSidDelimiter);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsWithMissingSidDelimiter);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionDeleteCommandParser sessionDeleteCommandParser = new SessionDeleteCommandParser();
        try {
            sessionDeleteCommandParser.getCommand(argsWithMissingSidDelimiter);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/sid"
                    + "\n" + SessionDeleteCommandParser.COMMAND_FORMAT;
            assertEquals(messageTest, invalidFormatException.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing argument is detected in the user input.
     */
    @Test
    public void getCommand_hasMissingArgument_InvalidFormatExceptionThrown() {
        String inputMissingSidDelimiter = "session /delete /sid";
        String argsWithMissingSidDelimiter = Parser.getRemainingArgument(inputMissingSidDelimiter);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsWithMissingSidDelimiter);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionDeleteCommandParser sessionDeleteCommandParser = new SessionDeleteCommandParser();
        try {
            sessionDeleteCommandParser.getCommand(argsWithMissingSidDelimiter);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/sid"
                    + "\n" + SessionDeleteCommandParser.COMMAND_FORMAT;
            assertEquals(messageTest, invalidFormatException.getMessage());
        }
    }
}
