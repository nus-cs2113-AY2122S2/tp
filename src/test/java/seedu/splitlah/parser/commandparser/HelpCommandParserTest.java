package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandParserTest {

    /**
     * Checks if a HelpCommand object is correctly returned when the command is correctly entered.
     */
    @Test
    void getCommand_validUserInput_HelpCommand() {
        String validUserInput = "help";
        Command command = Parser.getCommand(validUserInput);
        assertEquals(HelpCommand.class, command.getClass());
    }

    /**
     * Checks if an InvalidCommand object is returned when additional irrelevant argument tokens are appended to the 
     * syntax of HelpCommand as input.
     */
    @Test
    void getCommand_withIrrelevantTokens_InvalidCommand() {
        // Single additional token, no delimiters
        String helpWithIrrelevantArguments = "help apple";
        Command command = Parser.getCommand(helpWithIrrelevantArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Single additional token, with delimiters
        helpWithIrrelevantArguments = "help /apple";
        command = Parser.getCommand(helpWithIrrelevantArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Two additional tokens, without delimiters
        helpWithIrrelevantArguments = "help apple orange";
        command = Parser.getCommand(helpWithIrrelevantArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Two additional tokens, one delimiters
        helpWithIrrelevantArguments = "help /apple orange";
        command = Parser.getCommand(helpWithIrrelevantArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Three additional tokens, two delimiters
        helpWithIrrelevantArguments = "help /apple /sid 1";
        command = Parser.getCommand(helpWithIrrelevantArguments);
        assertEquals(InvalidCommand.class, command.getClass());
    }
}
