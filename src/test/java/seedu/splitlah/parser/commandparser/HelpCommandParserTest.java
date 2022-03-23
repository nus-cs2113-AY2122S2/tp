package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.command.InvalidCommand;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandParserTest {
    
    Manager manager = new Manager();

    @Test
    void getCommand_validUserInput_HelpCommand() {
        String validUserInput = "help";
        Command command = Parser.getCommand(validUserInput);
        assertEquals(HelpCommand.class, command.getClass());
    }

    @Test
    void getCommand_additionalArguments_InvalidCommand() {
        // Single additional token, no delimiters
        String helpWithAdditionalArguments = "help apple";
        Command command = Parser.getCommand(helpWithAdditionalArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Single additional token, with delimiters
        helpWithAdditionalArguments = "help /apple";
        command = Parser.getCommand(helpWithAdditionalArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Two additional tokens, without delimiters
        helpWithAdditionalArguments = "help apple orange";
        command = Parser.getCommand(helpWithAdditionalArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Two additional tokens, one delimiters
        helpWithAdditionalArguments = "help /apple orange";
        command = Parser.getCommand(helpWithAdditionalArguments);
        assertEquals(InvalidCommand.class, command.getClass());

        // Three additional tokens, two delimiters
        helpWithAdditionalArguments = "help /apple /sid 1";
        command = Parser.getCommand(helpWithAdditionalArguments);
        assertEquals(InvalidCommand.class, command.getClass());
    }
}