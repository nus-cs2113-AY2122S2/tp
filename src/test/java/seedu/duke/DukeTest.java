package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void commandParser_addCommandWithNonEmptyItemNameAndPax_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Add Item Name:Toilet Roll Pax:5");
        AddItemCommand addItemCommand = (AddItemCommand) command;
        Item item = addItemCommand.getItem();
        assertEquals("Toilet Roll", item.getName());
        assertEquals(5, item.getPax());
    }

    @Test
    public void commandParser_addCommandWithEmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new CommandParser().parse("Add Item Name: Pax:5"));
    }

    @Test
    public void commandParser_addCommandWithEmptyItemPax_exceptionThrown() {
        assertThrows(EmptyItemPaxException.class, () -> new CommandParser().parse("Add Item Name:Toilet Roll Pax:"));
    }

    @Test
    public void commandParser_addCommandWithEmptyItemNameAndPax_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> new CommandParser().parse("Add Item"));
    }

    @Test
    public void commandParser_addCommandNameAge_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("Add Housekeeper Susan ~ 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("Susan", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(23, addHousekeeperCommand.getHousekeeper().getAge());
    }

    @Test
    public void commandParser_addCommandInvalidAge_exceptionThrown() {
        assertThrows(InvalidAgeException.class, () -> new CommandParser().parse("Add Housekeeper Susan ~ fifty"));
    }

}
