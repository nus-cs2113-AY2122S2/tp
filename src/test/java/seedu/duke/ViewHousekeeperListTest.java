package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.housekeepercommands.ResetAvailabilityCommand;
import seedu.duke.command.housekeepercommands.ViewHousekeeperListCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidViewHousekeeperException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewHousekeeperListTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_MAN = 2;
    private static final int INDEX_OF_SALLY = 1;
    private static final int INDEX_OF_JAMES = 0;

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        Housekeeper housekeeperJame = new Housekeeper("James", 22);
        housekeeperList.addHousekeeperInList(housekeeperJame);
        Housekeeper housekeeperSally = new Housekeeper("Sally", 30);
        housekeeperList.addHousekeeperInList(housekeeperSally);
        Housekeeper housekeeperMan = new Housekeeper("Man", 60);
        housekeeperList.addHousekeeperInList(housekeeperMan);
        housekeeperJame.setAvailability("1,7,");
        housekeeperSally.setAvailability("5,2");
    }

    @Test
    public void viewHousekeeperList_success() throws HotelLiteManagerException, IOException {
        ViewHousekeeperListCommand viewHousekeeperListCommand =
                new ViewHousekeeperListCommand("view recorded housekeepers");
        viewHousekeeperListCommand.execute(listContainer, ui);
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();

        assertEquals("James", housekeeperList.getHousekeeper(INDEX_OF_JAMES).getName());
        assertEquals("Man", housekeeperList.getHousekeeper(INDEX_OF_MAN).getName());
        assertEquals("Sally", housekeeperList.getHousekeeper(INDEX_OF_SALLY).getName());

        assertEquals(22, housekeeperList.getHousekeeper(INDEX_OF_JAMES).getAge());
        assertEquals(60, housekeeperList.getHousekeeper(INDEX_OF_MAN).getAge());
        assertEquals(30, housekeeperList.getHousekeeper(INDEX_OF_SALLY).getAge());

        assertEquals("Monday Sunday", housekeeperList.getHousekeeper(INDEX_OF_JAMES).getAvailability());
        assertEquals("N/A", housekeeperList.getHousekeeper(INDEX_OF_MAN).getAvailability());
        assertEquals("Friday Tuesday", housekeeperList.getHousekeeper(INDEX_OF_SALLY).getAvailability());
    }

    @Test
    public void viewListCommand_exceptionThrown() {
        assertThrows(InvalidViewHousekeeperException.class, () ->
                new CommandParser().parse("view Recorded Housekeepers xx "));
    }
}
