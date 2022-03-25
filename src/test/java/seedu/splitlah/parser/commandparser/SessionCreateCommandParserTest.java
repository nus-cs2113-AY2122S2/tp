package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.SessionCreateCommand;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionCreateCommandParserTest {

    /**
     * Checks if a SessionCreateCommand object is correctly returned when the user input is valid.
     */
    @Test
    public void getCommand_validUserInput_SessionCreateCommand() {
        // Case 1: Create a session with /pl to indicate people involved in a session.
        String validUserInput = "session /create /n Class gathering /d 15-02-2022 /pl Alice Bob";
        String validArguments = Parser.getRemainingArgument(validUserInput);

        try {
            SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();
            Command sessionCreateCommand = sessionCreateCommandParser.getCommand(validArguments);
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
     * Checks if an InvalidFormatException is thrown when missing delimiters are detected in the user input
     * and if the exception message is correct.
     */
    @Test
    public void getCommand_hasMissingDelimiter_exceptionThrown() {
        SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();

        // Case 1: Missing /n delimiter.
        String inputMissingNameDelimiter = "session /create /d 15-02-2022 /pl Alice Bob";
        String argsMissingNameDelimiter = Parser.getRemainingArgument(inputMissingNameDelimiter);
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
     * Checks if an InvalidFormatException is thrown when missing arguments are detected in the user input
     * and if the exception message is correct.
     */
    @Test
    public void getCommand_hasMissingArguments_exceptionThrown() {
        SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();

        // Case 1: Missing Session name.
        String inputMissingNameArgument = "session /create /n /d 15-02-2022 /pl Alice Bob";
        String argsMissingNameArgument = Parser.getRemainingArgument(inputMissingNameArgument);
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
     * Checks if an InvalidFormatException is thrown when missing person list and groupId delimiters
     * are detected in the user input and if the exception message is correct.
     */
    @Test
    public void getCommand_hasMissingPersonListAndGidDelimiter_exceptionThrown() {
        SessionCreateCommandParser sessionCreateCommandParser = new SessionCreateCommandParser();

        String inputMissingPersonListAndGidDelimiters = "session /create /n Class gathering /d 15-02-2022";
        String argsWithMissingPersonListAndGidDelimiter =
                Parser.getRemainingArgument(inputMissingPersonListAndGidDelimiters);
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
