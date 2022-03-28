package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;


public class AddSatisfactionCommandTest {
    @Test
    public void commandParser_addCommandWithNonEmptySatisfactionCustomerAndSatisfactionValue_success()
            throws Exception {
        CommandParser parser = new CommandParser();

        Command command1 = parser.parse("add satisfaction Bob Jones / 3");
        AddSatisfactionCommand addSatisfactionCommand1 = (AddSatisfactionCommand) command1;
        Satisfaction satisfaction1 = addSatisfactionCommand1.getSatisfaction();
        assertEquals("bob jones", satisfaction1.getCustomerName());
        assertEquals(3, satisfaction1.getSatisfactionValue());

        Command command2 = parser.parse("add satisfaction Fred / 4");
        AddSatisfactionCommand addSatisfactionCommand2 = (AddSatisfactionCommand) command2;
        Satisfaction satisfaction2 = addSatisfactionCommand2.getSatisfaction();
        assertEquals("fred", satisfaction2.getCustomerName());
        assertEquals(4, satisfaction2.getSatisfactionValue());
    }

    @Test
    public void commandParser_addCommandWithEmptySatisfactionCustomer_exceptionThrown() {
        assertThrows(EmptySatisfactionCustomerException.class, ()
            -> new CommandParser().parse("add satisfaction / 4"));
    }

    @Test
    public void commandParser_addCommandWithEmptySatisfactionValue_exceptionThrown() {
        assertThrows(EmptySatisfactionValueException.class, ()
            -> new CommandParser().parse("add satisfaction Bob / "));
    }

    @Test
    public void commandParser_addCommandWithNoSlash_exceptionThrown() {
        assertThrows(InvalidCommandException.class, ()
            -> new CommandParser().parse("add satisfaction Bob 5"));
    }

    @Test
    public void commandParser_addCommandWithInvalidSatisfactionValue_exceptionThrown() {
        assertThrows(InvalidSatisfactionValueException.class, ()
            -> new CommandParser().parse("add satisfaction Joe / -1"));
    }

}
