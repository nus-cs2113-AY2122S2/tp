package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Rect;
import seedu.duke.command.housekeepercommands.GetAvailableHousekeeperCommand;
import seedu.duke.command.housekeepercommands.ResetAvailabilityCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidNewWeekException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResetAvailabilityTest {
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
        housekeeperJame.setAvailability("1,7,3");
        housekeeperSally.setAvailability("5,2,7");
        housekeeperMan.setAvailability("2,3");
    }

    @Test
    public void commandParser_resetAvailability_success() throws HotelLiteManagerException, IOException {
        ResetAvailabilityCommand resetAvailabilityCommand =
                new ResetAvailabilityCommand("is a new week");
        resetAvailabilityCommand.execute(listContainer, ui);
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        assertEquals("N/A", housekeeperList.getHousekeeper(INDEX_OF_JAMES).getAvailability());
        assertEquals("N/A", housekeeperList.getHousekeeper(INDEX_OF_MAN).getAvailability());
        assertEquals("N/A", housekeeperList.getHousekeeper(INDEX_OF_SALLY).getAvailability());
    }

    @Test
    public void extraWords_exceptionThrown() {
        assertThrows(InvalidNewWeekException.class, () -> new CommandParser().parse("is a new week yay "));
    }
}
