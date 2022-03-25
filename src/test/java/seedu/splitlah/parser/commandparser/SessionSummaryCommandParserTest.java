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

    @Test
    void getCommand_missingSessionIdDelimiter_exceptionThrown() {
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

    @Test
    void getCommand_missingSessionIdArgument_exceptionThrown() {
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

    @Test
    void getCommand_sessionIdArgumentNonNumeric_exceptionThrown() {
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

    @Test
    void getCommand_sessionIdArgumentNotPositive_exceptionThrown() {
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
