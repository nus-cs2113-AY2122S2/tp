package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;


public class AddAvailabilityTest {

    @Test
    public void commandParser_addCommandNameAvailability_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("availability Susan / 1,3");
        AddAvailabilityCommand addAvailabilityCommand = (AddAvailabilityCommand) command;
        assertEquals("1,3", addAvailabilityCommand.getAvailability());
        assertEquals("susan", addAvailabilityCommand.getName());
    }

    @Test
    public void commandParser_addCommandInvalidAvailability_exceptionThrown() {
        assertThrows(InvalidAvailabilityException.class, () -> new CommandParser().parse("availability Susan / "));
    }
}