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

}
