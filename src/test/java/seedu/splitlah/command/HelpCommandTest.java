package seedu.splitlah.command;

import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {
    
    Manager manager = new Manager();

    /**
     * Checks if a HelpCommand object is correctly returned when a valid input is provided,
     * without any exceptions thrown. 
     */
    @Test
    void run_validInput_noExceptionsThrown() {
        String validInput = "help";
        Command command = Parser.getCommand(validInput);
        assertEquals(HelpCommand.class, command.getClass());
        command.run(manager);
    }
}
