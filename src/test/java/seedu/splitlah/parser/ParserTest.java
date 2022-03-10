package seedu.splitlah.parser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParserTest {

    // getCommand()
    /**
     * Checks if an InvalidCommand is returned when an empty String object is provided by the user.
     */
    @Test
    void getCommand_emptyString_InvalidCommand() {
        String emptyString = "";
        Command command = Parser.getCommand(emptyString);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    /**
     * Checks if an InvalidCommand is returned when a String object containing only whitespaces is provided by the user.
     */
    @Test
    void getCommand_whitespaceInput_InvalidCommand() {
        String whitespaceString = "     ";
        Command command = Parser.getCommand(whitespaceString);
        assertEquals(InvalidCommand.class, command.getClass());
    }

    // getCommandType()
    /**
     * Checks if an empty String object is returned as the command string
     * when an empty String object is provided by the user.
     */
    @Test
    void getCommandType_emptyStringInput_emptyString() {
        String emptyString = "";
        String output = Parser.getCommandType(emptyString);
        assertEquals("", output);
    }

    /**
     * Checks if a String object containing identical contents as the input is returned as the command string
     * when a single token of input is provided by the user.
     * Matching command types: "help", "list", "exit"
     */
    @Test
    void getCommandType_singleTokenInput_inputEqualsOutput() {
        String singleTokenString = "randomTest123";
        String output = Parser.getCommandType(singleTokenString);
        assertEquals(singleTokenString, output);
    }

    /**
     * Checks if null is returned when an input, with a second token does not start with a delimiter,
     * is provided by the user.
     */
    @Test
    void getCommandType_doubleTokenNoDelimiterInput_null() {
        String doubleTokenWithNoDelimiterString = "help apple";
        String output = Parser.getCommandType(doubleTokenWithNoDelimiterString);
        assertNull(output);
    }
}
