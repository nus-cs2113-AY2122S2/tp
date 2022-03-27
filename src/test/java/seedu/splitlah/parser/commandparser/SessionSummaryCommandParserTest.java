package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.SessionSummaryCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserErrors;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SessionSummaryCommandParserTest {

    private static final String COMMAND_TYPE = SessionSummaryCommandParser.COMMAND_TEXT;

    /**
     * Checks if a SessionSummaryCommand object is correctly returned when the command is correctly entered.
     */
    @Test
    void getCommand_validUserInput_SessionSummaryCommand() {
        String validUserInput = "session /summary /sid 1";
        String remainingArgs = Parser.getRemainingArgument(validUserInput);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionSummaryCommandParser sessionSummaryCommandParser = new SessionSummaryCommandParser();
        try {
            Command command = sessionSummaryCommandParser.getCommand(remainingArgs);
            assertEquals(SessionSummaryCommand.class, command.getClass());
        } catch (InvalidFormatException exception) {
            fail();
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when the Session ID delimiter is not provided by the user.
     */
    @Test
    void getCommand_missingSessionIdDelimiter_InvalidFormatExceptionThrown() {
        String userInputMissingSessionIdDelimiter = "session /summary";
        String remainingArgs = Parser.getRemainingArgument(userInputMissingSessionIdDelimiter);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionSummaryCommandParser sessionSummaryCommandParser = new SessionSummaryCommandParser();
        try {
            Command command = sessionSummaryCommandParser.getCommand(remainingArgs);
            fail();
        } catch (InvalidFormatException exception) {
            String exceptionMessage = ParserErrors.getMissingDelimiterErrorMessage(ParserUtils.SESSION_ID_DELIMITER)
                    + "\n" + SessionSummaryCommandParser.COMMAND_FORMAT;
            assertEquals(exceptionMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when the session unique identifier argument is not provided by the user.
     */
    @Test
    void getCommand_missingSessionIdArgument_InvalidFormatExceptionThrown() {
        String userInputMissingSessionIdArgument = "session /summary /sid";
        String remainingArgs = Parser.getRemainingArgument(userInputMissingSessionIdArgument);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionSummaryCommandParser sessionSummaryCommandParser = new SessionSummaryCommandParser();
        try {
            Command command = sessionSummaryCommandParser.getCommand(remainingArgs);
            fail();
        } catch (InvalidFormatException exception) {
            String exceptionMessage = ParserErrors.getMissingArgumentErrorMessage(ParserUtils.SESSION_ID_DELIMITER)
                    + "\n" + SessionSummaryCommandParser.COMMAND_FORMAT;
            assertEquals(exceptionMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when the session unique identifier argument provided by the user is non-numeric.
     */
    @Test
    void getCommand_sessionIdArgumentNonNumeric_InvalidFormatExceptionThrown() {
        String userInputSessionIdArgumentNonNumeric = "session /summary /sid apple";
        String remainingArgs = Parser.getRemainingArgument(userInputSessionIdArgumentNonNumeric);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionSummaryCommandParser sessionSummaryCommandParser = new SessionSummaryCommandParser();
        try {
            Command command = sessionSummaryCommandParser.getCommand(remainingArgs);
            fail();
        } catch (InvalidFormatException exception) {
            String exceptionMessage = ParserErrors.getNonIntegerErrorMessage(ParserUtils.SESSION_ID_DELIMITER)
                    + "\n" + SessionSummaryCommandParser.COMMAND_FORMAT;
            assertEquals(exceptionMessage, exception.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when the session unique identifier argument provided by the user is not positive.
     */
    @Test
    void getCommand_sessionIdArgumentNotPositive_InvalidFormatExceptionThrown() {
        // sessionId = 0
        String userInputSessionIdArgumentZero = "session /summary /sid 0";
        String remainingArgs = Parser.getRemainingArgument(userInputSessionIdArgumentZero);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        SessionSummaryCommandParser sessionSummaryCommandParser = new SessionSummaryCommandParser();
        try {
            Command command = sessionSummaryCommandParser.getCommand(remainingArgs);
            fail();
        } catch (InvalidFormatException exception) {
            String exceptionMessage = Message.ERROR_PARSER_ID_VALUE_NOT_POSITIVE
                    + "\n" + SessionSummaryCommandParser.COMMAND_FORMAT;
            assertEquals(exceptionMessage, exception.getMessage());
        }

        // sessionId < 0
        String userInputSessionIdArgumentNegative = "session /summary /sid -1";
        remainingArgs = Parser.getRemainingArgument(userInputSessionIdArgumentNegative);
        errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        try {
            Command command = sessionSummaryCommandParser.getCommand(remainingArgs);
            fail();
        } catch (InvalidFormatException exception) {
            String exceptionMessage = Message.ERROR_PARSER_ID_VALUE_NOT_POSITIVE
                    + "\n" + SessionSummaryCommandParser.COMMAND_FORMAT;
            assertEquals(exceptionMessage, exception.getMessage());
        }
    }
}
