package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionCreateCommandParserTest {

    private static final String COMMAND_TYPE = SessionCreateCommandParser.COMMAND_TEXT;

    /**
     * Checks if a SessionCreateCommand object is correctly returned when the user input is valid.
     */
    @Test
    public void getCommand_validUserInput_SessionCreateCommand() {
        // Case 1: Create a session with /pl to indicate people involved in a session.
        String validUserInputOne = "session /create /n Class gathering /d 15-02-2022 /pl Alice Bob";
        String validArgumentsOne = Parser.getRemainingArgument(validUserInputOne);
        String errorMessageOne = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArgumentsOne);
        if (!errorMessageOne.isEmpty()) {
            fail();
        }
        try {
            SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();
            Command sessionCreateCommand = sessionCreateCommandParser.getCommand(validArgumentsOne);
            assertEquals(sessionCreateCommand.getClass(), SessionCreateCommand.class);
        } catch (InvalidFormatException invalidFormatException) {
            fail();
        }

        // Case 2: Create a session with /gid to indicate people involved in a session.
        Manager manager = new Manager();
        String groupInput = "group /create /n Class1 /pl Alice Bob";
        Command createGroup = Parser.getCommand(groupInput);
        createGroup.run(manager);

        String validUserInputTwo = "session /create /n Class gathering /d 15-02-2022 /gid 1";
        String validArgumentsTwo = Parser.getRemainingArgument(validUserInputTwo);
        String errorMessageTwo = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArgumentsTwo);
        if (!errorMessageTwo.isEmpty()) {
            fail();
        }
        try {
            SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();
            Command sessionCreateCommand = sessionCreateCommandParser.getCommand(validArgumentsTwo);
            sessionCreateCommand.run(manager);
            assertEquals(SessionCreateCommand.class, sessionCreateCommand.getClass());
        } catch (InvalidFormatException invalidFormatException) {
            fail();
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing delimiters are detected in the user input.
     */
    @Test
    public void getCommand_hasMissingDelimiter_InvalidFormatExceptionThrown() {
        SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();

        // Case 1: Missing /n delimiter.
        String inputMissingNameDelimiter = "session /create /d 15-02-2022 /pl Alice Bob";
        String argsMissingNameDelimiter = Parser.getRemainingArgument(inputMissingNameDelimiter);
        String errorNameMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingNameDelimiter);
        if (!errorNameMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsMissingNameDelimiter);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/n"
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 2: Missing /d delimiter.
        String inputMissingDateDelimiter = "session /create /n Class gathering /pl Alice Bob";
        String argsMissingDateDelimiter = Parser.getRemainingArgument(inputMissingDateDelimiter);
        String errorDateMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingDateDelimiter);
        if (!errorDateMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsMissingDateDelimiter);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_DELIMITER_NOT_FOUND + "/d"
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing arguments are detected in the user input.
     */
    @Test
    public void getCommand_hasMissingArguments_InvalidFormatExceptionThrown() {
        SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();

        // Case 1: Missing Session name.
        String inputMissingNameArgument = "session /create /n /d 15-02-2022 /pl Alice Bob";
        String argsMissingNameArgument = Parser.getRemainingArgument(inputMissingNameArgument);
        String errorNameMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingNameArgument);
        if (!errorNameMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsMissingNameArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/n"
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 2: Missing Session Date.
        String inputMissingDateArgument = "session /create /n Class gathering /d /pl Alice Bob";
        String argsMissingDateArgument = Parser.getRemainingArgument(inputMissingDateArgument);
        String errorDateMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingDateArgument);
        if (!errorDateMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsMissingDateArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/d"
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 3: Missing List of persons.
        String inputMissingPersonListArgument = "session /create /n Class gathering /d 15-02-2022 /pl";
        String argsMissingPersonListArgument = Parser.getRemainingArgument(inputMissingPersonListArgument);
        String errorPersonListMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingPersonListArgument);
        if (!errorPersonListMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsMissingPersonListArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/pl"
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }

        // Case 4: Missing Group unique identifier.
        String inputMissingGidArgument = "session /create /n Class gathering /d 15-02-2022 /gid";
        String argsMissingGidArgument = Parser.getRemainingArgument(inputMissingGidArgument);
        String errorGidMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingGidArgument);
        if (!errorGidMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsMissingGidArgument);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/gid"
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }
    }

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing person list and groupId delimiters are detected in the user input.
     */
    @Test
    public void getCommand_hasMissingPersonListAndGidDelimiter_InvalidFormatExceptionThrown() {
        SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();

        String inputMissingPersonListAndGidDelimiters = "session /create /n Class gathering /d 15-02-2022";
        String argsWithMissingPersonListAndGidDelimiter =
                Parser.getRemainingArgument(inputMissingPersonListAndGidDelimiters);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsWithMissingPersonListAndGidDelimiter);
        if (!errorMessage.isEmpty()) {
            fail();
        }
        try {
            sessionCreateCommandParser.getCommand(argsWithMissingPersonListAndGidDelimiter);
            fail();
        } catch (InvalidFormatException invalidFormatException) {
            String messageToTest = Message.ERROR_SESSIONCREATE_MISSING_PERSONLIST_AND_GROUP_DELIMITERS
                    + "\n" + SessionCreateCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, invalidFormatException.getMessage());
        }
    }
}
