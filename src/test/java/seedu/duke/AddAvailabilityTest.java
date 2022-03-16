package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddAvailabilityTest {

    @Test
    public void commandParser_addCommandNameAvailability_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Availability Susan @ Monday");
        AddAvailabilityCommand addAvailabilityCommand = (AddAvailabilityCommand) command;
        assertEquals("Monday", addAvailabilityCommand.getAvailability());
        assertEquals("Susan", addAvailabilityCommand.getName());
    }

    @Test
    public void commandParser_addCommandInvalidAvailability_exceptionThrown() {
        assertThrows(InvalidAvailabilityException.class, () -> new CommandParser().parse("Availability Susan @ "));
    }
}
