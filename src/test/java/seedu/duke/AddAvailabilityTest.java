package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.AddAvailabilityCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidAvailabilityException;
import seedu.duke.exceptions.UserDoesNotExistException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;

import java.io.IOException;


public class AddAvailabilityTest {
    private ListContainer listContainer;
    private static final int INDEX_OF_SALLY = 1;
    private Ui ui = new Ui();

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        Housekeeper housekeeperJame = new Housekeeper("James", 22);
        housekeeperList.addHousekeeperInList(housekeeperJame);
        Housekeeper housekeeperSally = new Housekeeper("Sally", 30);
        housekeeperList.addHousekeeperInList(housekeeperSally);
    }

    @Test
    public void commandParser_addAvailability_Success() throws HotelLiteManagerException, IOException {
        AddAvailabilityCommand addAvailabilityCommand =
                new AddAvailabilityCommand("sally / 1,2");
        addAvailabilityCommand.execute(listContainer, ui);
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        Housekeeper housekeeper = housekeeperList.getHousekeeper(INDEX_OF_SALLY);
        assertEquals("Monday Tuesday", housekeeper.getAvailability());
    }

    @Test
    public void commandParser_UserDoesNotExist_exceptionThrown() throws HotelLiteManagerException, IOException {
        AddAvailabilityCommand addAvailabilityCommand =
                new AddAvailabilityCommand("han / 1,2");
        assertThrows(UserDoesNotExistException.class, () -> addAvailabilityCommand.execute(listContainer, ui));

    }

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
        assertThrows(InvalidAvailabilityException.class, () -> new CommandParser()
                .parse("availability Susan / "));
    }

    @Test
    public void commandParser_addCommandExtraSlash_exceptionThrown() {
        assertThrows(InvalidAvailabilityException.class, () -> new CommandParser()
                .parse("availability Susan /3/"));
    }
}