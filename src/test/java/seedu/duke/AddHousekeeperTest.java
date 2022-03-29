package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;


public class AddHousekeeperTest {

    @Test
    public void commandParser_addCommandNameAge_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("add housekeeper Susan / 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("susan", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(23, addHousekeeperCommand.getHousekeeper().getAge());
    }

    @Test
    public void commandParser_addCommandInvalidAge_exceptionThrown() {
        assertThrows(InvalidAgeException.class, () ->
                new CommandParser().parse("add housekeeper Susan / fifty"));
    }

    @Test
    public void commandParser_addCommandEmptyDescription_exceptionThrown() {
        assertThrows(InvalidHousekeeperProfile.class, () ->
                new CommandParser().parse("add housekeeper / "));
    }

    @Test
    public void commandParser_addCommandUnderage_exceptionThrown() {
        assertThrows(UnderAgeException.class, () -> new CommandParser().parse("add housekeeper Jane / 12"));
    }

    @Test
    public void commandParser_addCommandOverage_exceptionThrown() {
        assertThrows(OverAgeException.class, () -> new CommandParser().parse("add housekeeper Sally / 81"));
    }

    @Test
    public void commandParser_nameCorrect() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("add housekeeper Susan / 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("susan", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(23, addHousekeeperCommand.getHousekeeper().getAge());
        command = parser.parse("delete housekeeper susan");
        DeleteHousekeeperCommand deleteHousekeeperCommand = (DeleteHousekeeperCommand) command;
        assertEquals("susan", deleteHousekeeperCommand.getName());
    }

}
