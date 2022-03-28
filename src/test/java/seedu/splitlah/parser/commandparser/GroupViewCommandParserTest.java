package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.GroupViewCommand;
import seedu.splitlah.data.Manager;
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

    /**
     * Checks if an InvalidFormatException with the correct message is thrown
     * when missing group argument is detected in the user input.
     */
    @Test
    public void getCommand_hasMissingGroupIdArgument_InvalidFormatExceptionThrown() {
        String inputMissingGroupIdArgument = "group /view /gid";
        String argsMissingGroupIdArgument = Parser.getRemainingArgument(inputMissingGroupIdArgument);
        String errorMessageOne = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, argsMissingGroupIdArgument);
        if (!errorMessageOne.isEmpty()) {
            fail();
        }

        GroupViewCommandParser groupViewCommandParser = new GroupViewCommandParser();
        try {
            groupViewCommandParser.getCommand(argsMissingGroupIdArgument);
            fail();
        } catch (InvalidFormatException e) {
            String messageToTest = Message.ERROR_PARSER_MISSING_ARGUMENT + "/gid"
                    + "\n" + GroupViewCommandParser.COMMAND_FORMAT;
            assertEquals(messageToTest, e.getMessage());
        }
    }

    /**
     * Checks if a GroupViewCommand object is correctly returned when the command is correctly entered.
     */
    @Test
    public void getCommand_validUserInput_ActivityCreateCommand() {
        Manager manager = new Manager();

        String groupInput = "group /create /n Group1 /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupInput);
        createGroup.run(manager);

        String validUserInput = "group /view /gid 1";
        String validArguments = Parser.getRemainingArgument(validUserInput);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, validArguments);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        GroupViewCommandParser groupViewCommandParser = new GroupViewCommandParser();
        try {
            Command command = groupViewCommandParser.getCommand(validArguments);
            assertEquals(GroupViewCommand.class, command.getClass());
        } catch (InvalidFormatException e) {
            fail();
        }
    }

}
