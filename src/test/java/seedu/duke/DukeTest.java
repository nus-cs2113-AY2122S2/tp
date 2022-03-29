package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import seedu.duke.command.itemcommand.AddItemCommand;
import seedu.duke.command.Command;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void commandParser_addCommandWithNonEmptyItemNameAndPax_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Add Item Toilet Roll / 5");
        AddItemCommand addItemCommand = (AddItemCommand) command;
        Item item = addItemCommand.getItem();
        assertEquals("toilet roll", item.getName());
        assertEquals(5, item.getPax());
    }

    @Test
    public void commandParser_addCommandWithEmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new CommandParser().parse("Add Item /5"));
    }

    @Test
    public void commandParser_addCommandWithEmptyItemPax_exceptionThrown() {
        assertThrows(EmptyItemPaxException.class, () -> new CommandParser().parse("Add Item Toilet Roll /"));
    }

    @Test
    public void commandParser_addCommandWithEmptyItemNameAndPax_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> new CommandParser().parse("Add Item "));
    }


    @Test
    public void commandParser_addCommandInvalidView_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> new CommandParser().parse("ViewRecorded Housekeeper"));
    }
}
