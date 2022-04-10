package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.housekeepercommands.AgeIncreaseCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidNewYearException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgeIncreaseTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_MAN_EXCEEDING_AGE = 0;
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
    }

    @Test
    public void commandParser_addAvailability_Success() throws HotelLiteManagerException, IOException {
        AgeIncreaseCommand ageIncreaseCommand =
                new AgeIncreaseCommand("is a new year");
        ageIncreaseCommand.execute(listContainer, ui);
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        Housekeeper housekeeperSally = housekeeperList.getHousekeeper(INDEX_OF_SALLY);
        Housekeeper housekeeperJames = housekeeperList.getHousekeeper(INDEX_OF_JAMES);
        assertEquals(31, housekeeperSally.getAge());
        assertEquals(23, housekeeperJames.getAge());
        ArrayList<Housekeeper> exceedAgeLimit = housekeeperList.getHousekeeperExceedValidAgeList();
        assertEquals("Man", exceedAgeLimit.get(INDEX_OF_MAN_EXCEEDING_AGE).getName());
    }


    @Test
    public void extraWordIncreaseAgeCommand_exceptionThrown() {
        assertThrows(InvalidNewYearException.class, () ->
                new CommandParser().parse("is a new year hello  "));
    }
}
