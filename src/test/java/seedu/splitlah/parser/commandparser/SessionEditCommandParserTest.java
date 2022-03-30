package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.SessionEditCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionEditCommandParserTest {

    private static final String COMMAND_TYPE = SessionEditCommandParser.COMMAND_TEXT;

    /**
     * Checks if a SessionEditCommand object is correctly returned when the user input is valid.
     */
    @Test
    public void getCommand_validUserInput_SessionEditCommand() {
        // Case 1: Edit a session with /n delimiter only.
        String validUserInputName = "session /edit /sid 1 /n Class Outing";
        String validArgumentsName = Parser.getRemainingArgument(validUserInputName);
        String errorMessageOne = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArgumentsName);
        if (!errorMessageOne.isEmpty()) {
            fail();
        }
        try {
            SessionEditCommandParser sessionEditCommandParser = new SessionEditCommandParser();
            Command sessionEditCommand = sessionEditCommandParser.getCommand(validArgumentsName);
            assertEquals(sessionEditCommand.getClass(), SessionEditCommand.class);
        } catch (InvalidFormatException invalidFormatException) {
            fail();
        }

        // Case 2: Edit a session with /d delimiter only.
        String validUserInputDate = "session /edit /sid 1 /d 20-04-2022";
        String validArgumentsDate = Parser.getRemainingArgument(validUserInputDate);
        String errorMessageTwo = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArgumentsDate);
        if (!errorMessageTwo.isEmpty()) {
            fail();
        }
        try {
            SessionEditCommandParser sessionEditCommandParser = new SessionEditCommandParser();
            Command sessionEditCommand = sessionEditCommandParser.getCommand(validArgumentsDate);
            assertEquals(sessionEditCommand.getClass(), SessionEditCommand.class);
        } catch (InvalidFormatException invalidFormatException) {
            fail();
        }

        // Case 3: Edit a session with /pl delimiter only.
        String validUserInputPersonList = "session /edit /sid 1 /pl Alice Bob";
        String validArgumentsPersonList = Parser.getRemainingArgument(validUserInputPersonList);
        String errorMessageThree = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArgumentsPersonList);
        if (!errorMessageThree.isEmpty()) {
            fail();
        }
        try {
            SessionEditCommandParser sessionEditCommandParser = new SessionEditCommandParser();
            Command sessionEditCommand = sessionEditCommandParser.getCommand(validArgumentsPersonList);
            assertEquals(sessionEditCommand.getClass(), SessionEditCommand.class);
        } catch (InvalidFormatException invalidFormatException) {
            fail();
        }

        // Case 4: Edit a session with /n, /d, /pl delimiters.
        String validUserInput = "session /edit /sid 1 /n Class outing /d 20-04-2022 /pl Alice Bob";
        String validArguments = Parser.getRemainingArgument(validUserInput);
        String errorMessageFour = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArguments);
        if (!errorMessageFour.isEmpty()) {
            fail();
        }
        try {
            SessionEditCommandParser sessionEditCommandParser = new SessionEditCommandParser();
            Command sessionEditCommand = sessionEditCommandParser.getCommand(validArguments);
            assertEquals(sessionEditCommand.getClass(), SessionEditCommand.class);
        } catch (InvalidFormatException invalidFormatException) {
            fail();
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing delimiter is detected in the user input.
     */
    @Test
    public void getCommand_hasMissingDelimiter_InvalidFormatExceptionThrown() {
        String inputMissingSessionIdDelimiter = "session /edit";
        String argsMissingSessionIdDelimiter = Parser.getRemainingArgument(inputMissingSessionIdDelimiter);
        String errorNameMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingSessionIdDelimiter);
        if (!errorNameMessage.isEmpty()) {
            fail();
        }
        SessionEditCommandParser sessionEditCommandParser = new SessionEditCommandParser();
        try {
            sessionEditCommandParser.getCommand(argsMissingSessionIdDelimiter);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/sid"
                    + "\n" + SessionEditCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing arguments are detected in the user input.
     */
    @Test
    public void getCommand_hasMissingArguments_InvalidFormatExceptionThrown() {
        SessionEditCommandParser sessionEditCommandParser = new SessionEditCommandParser();

        // Case 1: Missing argument when /sid delimiter is provided
        String inputMissingIdArgument = "session /edit /sid";
        String argsMissingIdArgument = Parser.getRemainingArgument(inputMissingIdArgument);
        String errorIdMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingIdArgument);
        if (!errorIdMessage.isEmpty()) {
            fail();
        }
        try {
            sessionEditCommandParser.getCommand(argsMissingIdArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/sid"
                    + "\n" + SessionEditCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 2: Missing argument when /n delimiter is provided
        String inputMissingNameArgument = "session /edit /sid 1 /n";
        String argsMissingNameArgument = Parser.getRemainingArgument(inputMissingNameArgument);
        String errorNameMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingNameArgument);
        if (!errorNameMessage.isEmpty()) {
            fail();
        }
        try {
            sessionEditCommandParser.getCommand(argsMissingNameArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/n"
                    + "\n" + SessionEditCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 3: Missing argument when /d delimiter is provided
        String inputMissingDateArgument = "session /edit /sid 1 /d";
        String argsMissingDateArgument = Parser.getRemainingArgument(inputMissingDateArgument);
        String errorDateMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingDateArgument);
        if (!errorDateMessage.isEmpty()) {
            fail();
        }
        try {
            sessionEditCommandParser.getCommand(argsMissingDateArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/d"
                    + "\n" + SessionEditCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 4: Missing argument when /pl delimiter is provided
        String inputMissingPersonListArgument = "session /edit /sid 1 /pl";
        String argsMissingPersonListArgument = Parser.getRemainingArgument(inputMissingPersonListArgument);
        String errorPersonListMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingPersonListArgument);
        if (!errorPersonListMessage.isEmpty()) {
            fail();
        }
        try {
            sessionEditCommandParser.getCommand(argsMissingPersonListArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/pl"
                    + "\n" + SessionEditCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 5: Missing all editable arguments (/n /d /pl).
        String inputMissingAllArgument = "session /edit /sid 1";
        String argsMissingAllArgument = Parser.getRemainingArgument(inputMissingAllArgument);
        String errorAllMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingAllArgument);
        if (!errorAllMessage.isEmpty()) {
            fail();
        }
        try {
            sessionEditCommandParser.getCommand(argsMissingAllArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_SESSIONEDIT_NO_EDIT_DELIMITERS_FOUND
                    + "\n" + SessionEditCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }
    }
}
