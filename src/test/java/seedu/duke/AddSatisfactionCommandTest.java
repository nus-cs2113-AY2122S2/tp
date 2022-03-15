package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddSatisfactionCommandTest {
    @Test
    public void commandParser_addCommandWithNonEmptySatisfactionCustomerAndSatisfactionValue_success()
            throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Add Satisfaction Bob 3");
        AddSatisfactionCommand addSatisfactionCommand = (AddSatisfactionCommand) command;
        Satisfaction satisfaction = addSatisfactionCommand.getSatisfaction();
        assertEquals("Bob", satisfaction.getCustomerName());
        assertEquals(3, satisfaction.getSatisfactionValue());
    }

    @Test
    public void commandParser_addCommandWithEmptySatisfactionCustomer_exceptionThrown() {
        assertThrows(EmptySatisfactionCustomerException.class, () -> new CommandParser().parse("Add Satisfaction"));
    }

    @Test
    public void commandParser_addCommandWithEmptySatisfactionValue_exceptionThrown() {
        assertThrows(EmptySatisfactionValueException.class, () -> new CommandParser().parse("Add Satisfaction Bob"));
    }

    @Test
    public void commandParser_addCommandWithInvalidSatisfactionValue_exceptionThrown() {
        assertThrows(InvalidSatisfactionValueException.class, () ->
                new CommandParser().parse("Add Satisfaction Bob -1"));
    }
}
