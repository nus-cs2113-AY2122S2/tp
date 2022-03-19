package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddSatisfactionCommandTest {
    @Test
    public void commandParser_addCommandWithNonEmptySatisfactionCustomerAndSatisfactionValue_success()
            throws Exception {
        CommandParser parser = new CommandParser();

        Command command1 = parser.parse("Add Satisfaction Bob Jones 3");
        AddSatisfactionCommand addSatisfactionCommand1 = (AddSatisfactionCommand) command1;
        Satisfaction satisfaction1 = addSatisfactionCommand1.getSatisfaction();
        assertEquals("Bob Jones", satisfaction1.getCustomerName());
        assertEquals(3, satisfaction1.getSatisfactionValue());

        Command command2 = parser.parse("Add Satisfaction Fred 4");
        AddSatisfactionCommand addSatisfactionCommand2 = (AddSatisfactionCommand) command2;
        Satisfaction satisfaction2 = addSatisfactionCommand2.getSatisfaction();
        assertEquals("Fred", satisfaction2.getCustomerName());
        assertEquals(4, satisfaction2.getSatisfactionValue());
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
                new CommandParser().parse("Add Satisfaction Joe -1"));
    }

}
