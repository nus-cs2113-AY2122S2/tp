package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.itemcommand.UpdateItemPaxCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateItemCommandTest {
    @Test
    public void commandParser_updateCommandWithNonEmptyItemNameAndItemPax_success()
            throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Update Item Pax Toilet Roll / 5");
        UpdateItemPaxCommand updateItemPaxCommand = (UpdateItemPaxCommand) command;
        Item item = updateItemPaxCommand.getItem();
        assertEquals("toilet roll", item.getName());
        assertEquals(5, item.getPax());
    }

    @Test
    public void commandParser_updateItemPaxCommandWithEmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new CommandParser().parse("Update Item Pax / 5"));
    }

    @Test
    public void commandParser_updateItemPaxCommandWithEmptyItemPax_exceptionThrown() {
        assertThrows(EmptyItemPaxException.class, () -> new CommandParser().parse("Update Item Pax "
                + "Toilet Roll /"));
    }

    @Test
    public void commandParser_updateItemPaxCommandWithEmptyItemNameAndPax_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> new CommandParser().parse("Update Item Pax "));
    }

    @Test
    public void commandParser_updateItemPaxCommandWithInvalidItemPax_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new CommandParser().parse("Update Item Pax "
                + "Toilet Roll /-1"));
    }

}
