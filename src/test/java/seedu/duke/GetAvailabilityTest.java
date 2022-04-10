package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.AgeIncreaseCommand;
import seedu.duke.command.housekeepercommands.GetAvailableHousekeeperCommand;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.EmptyDayException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidDayException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAvailabilityTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_MAN_AVAILABLE_ON_TWO = 1;
    private static final int INDEX_OF_SALLY_AVAILABLE_ON_TWO = 0;
    private static final int DAY_TWO_AVAILABILITY = 2;

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
    public void getAvailability_success() throws HotelLiteManagerException {
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        ArrayList<Housekeeper> listOfHousekeeperAvailable =
                housekeeperList.getAvailableHousekeeperByDay(DAY_TWO_AVAILABILITY);
        assertEquals("Man", listOfHousekeeperAvailable.get(INDEX_OF_MAN_AVAILABLE_ON_TWO).getName());
        assertEquals("Sally", listOfHousekeeperAvailable.get(INDEX_OF_SALLY_AVAILABLE_ON_TWO).getName());
    }

    @Test
    public void commandParser_getAvailable_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("get available on 1");
        GetAvailableHousekeeperCommand getAvailableHousekeeperCommand = (GetAvailableHousekeeperCommand) command;
        assertEquals(1, getAvailableHousekeeperCommand.getSearchDay());
    }

    @Test
    public void duplicateAvailableDayCommand_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, () ->
                new CommandParser().parse("get available on get available on  "));
    }

    @Test
    public void emptyDay_exceptionThrown() {
        assertThrows(EmptyDayException.class, () ->
                new CommandParser().parse("get available on "));
    }

    @Test
    public void dayGivenNotInteger_exceptionThrown() {
        assertThrows(InvalidDayException.class, () ->
                new CommandParser().parse("get available on x"));
    }

    @Test
    public void dayGivenNotInLimitAboveBoundary_exceptionThrown() {
        assertThrows(InvalidDayException.class, () ->
                new CommandParser().parse("get available on 100"));
    }

    @Test
    public void dayGivenNotInLimitBelowBoundary_exceptionThrown() {
        assertThrows(InvalidDayException.class, () ->
                new CommandParser().parse("get available on 0"));
    }
}
