package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateItemCommandTest {
    @Test
    public void commandParser_updateCommandWithNonEmptyItemNameAndItemPax_success()
            throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Update Item Pax /Name:Toilet Roll /New Pax:5");
        UpdateItemPaxCommand updateItemPaxCommand = (UpdateItemPaxCommand) command;
        Item item = updateItemPaxCommand.getItem();
        assertEquals("Toilet Roll", item.getName());
        assertEquals(5, item.getPax());
    }

    @Test
    public void commandParser_updateItemPaxCommandWithEmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new CommandParser().parse("Update Item Pax " +
                "/Name: /New Pax:5"));
    }

    @Test
    public void commandParser_updateItemPaxCommandWithEmptyItemPax_exceptionThrown() {
        assertThrows(EmptyItemPaxException.class, () -> new CommandParser().parse("Update Item Pax " +
                "/Name:Toilet Roll /New Pax:"));
    }

    @Test
    public void commandParser_updateItemPaxCommandWithEmptyItemNameAndPax_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> new CommandParser().parse("Update Item Pax"));
    }

    @Test
    public void commandParser_updateItemPaxCommandWithInvalidItemPax_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new CommandParser().parse("Update Item Pax " +
                "/Name:Toilet Roll /New Pax:-1"));
    }

}
