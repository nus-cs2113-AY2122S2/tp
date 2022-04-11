package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.AddHousekeeperCommand;
import seedu.duke.command.housekeepercommands.AddHousekeeperPerformanceCommand;
import seedu.duke.command.housekeepercommands.DeleteHousekeeperCommand;
import seedu.duke.exceptions.InvalidUserException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.UnderAgeException;
import seedu.duke.exceptions.InvalidHousekeeperProfileException;
import seedu.duke.exceptions.NameNotStringException;
import seedu.duke.exceptions.InvalidAgeException;
import seedu.duke.exceptions.OverAgeException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformance;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformanceList;


import java.io.IOException;


public class AddHousekeeperTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_JON = 2;
    private static final int INDEX_OF_JAMES = 0;
    private static final int INDEX_OF_SALLY = 0;

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();

        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        housekeeperList.clearHousekeeperList();
        Housekeeper housekeeperJame = new Housekeeper("James", 22);
        housekeeperList.addHousekeeperInList(housekeeperJame);
        Housekeeper housekeeperSally = new Housekeeper("Sally", 30);
        housekeeperList.addHousekeeperInList(housekeeperSally);

        HousekeeperPerformanceList housekeeperPerformanceList = listContainer.getHousekeeperPerformanceList();
        housekeeperPerformanceList.clearHousekeeperPerformanceList();
    }

    @Test
    public void execute_addHousekeeper_success() throws HotelLiteManagerException {
        AddHousekeeperCommand addHousekeeperCommand = new AddHousekeeperCommand("jon / 30");
        addHousekeeperCommand.execute(listContainer, ui);
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        Housekeeper housekeeper = housekeeperList.getHousekeeper(INDEX_OF_JON);
        assertEquals("jon", housekeeper.getName());
        assertEquals(30, housekeeper.getAge());
    }

    @Test
    public void execute_addHousekeeperPerformanceLowerBoundaryRating_success()
            throws HotelLiteManagerException, IOException {
        AddHousekeeperPerformanceCommand addHousekeeperPerformanceCommand
                = new AddHousekeeperPerformanceCommand("James / 1");
        addHousekeeperPerformanceCommand.execute(listContainer, ui);
        HousekeeperPerformanceList housekeeperPerformanceList = listContainer.getHousekeeperPerformanceList();
        HousekeeperPerformance housekeeperPerformance = housekeeperPerformanceList.getPerformance(INDEX_OF_JAMES);
        assertEquals("James", housekeeperPerformance.getName());
        assertEquals(1, housekeeperPerformance.getRating());
        housekeeperPerformanceList.clearHousekeeperPerformanceList();
    }

    @Test
    public void execute_addHousekeeperPerformanceUpperBoundaryRating_success()
            throws HotelLiteManagerException, IOException {
        AddHousekeeperPerformanceCommand addHousekeeperPerformanceCommand
                = new AddHousekeeperPerformanceCommand("Sally / 5");
        addHousekeeperPerformanceCommand.execute(listContainer, ui);
        HousekeeperPerformanceList housekeeperPerformanceList = listContainer.getHousekeeperPerformanceList();
        HousekeeperPerformance housekeeperPerformance = housekeeperPerformanceList.getPerformance(INDEX_OF_SALLY);
        assertEquals("Sally", housekeeperPerformance.getName());
        assertEquals(5, housekeeperPerformance.getRating());
        housekeeperPerformanceList.clearHousekeeperPerformanceList();
    }

    @Test
    public void addCommandUserExist_exceptionThrown() throws HotelLiteManagerException {
        AddHousekeeperCommand addHousekeeperCommand = new AddHousekeeperCommand("sally / 30");
        assertThrows(InvalidUserException.class, () -> addHousekeeperCommand.execute(listContainer, ui));
    }

    @Test
    public void commandParser_addCommandNameAge_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("add housekeeper Susan / 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("susan", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(23, addHousekeeperCommand.getHousekeeper().getAge());
    }

    @Test
    public void commandParser_addCommandNameAgeUpperBound_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("add housekeeper Susan / 60");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("susan", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(60, addHousekeeperCommand.getHousekeeper().getAge());
    }

    @Test
    public void commandParser_addCommandNameAgeLowerBound_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("add housekeeper kelly / 21");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command;
        assertEquals("kelly", addHousekeeperCommand.getHousekeeper().getName());
        assertEquals(21, addHousekeeperCommand.getHousekeeper().getAge());
    }

    @Test
    public void commandParser_addCommandInvalidAge_exceptionThrown() {
        assertThrows(InvalidAgeException.class, () ->
                new CommandParser().parse("add housekeeper Susan / fifty"));
    }

    @Test
    public void commandParser_addCommandEmptyDescription_exceptionThrown() {
        assertThrows(InvalidHousekeeperProfileException.class, () ->
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
    public void commandParser_extraSlash_exceptionThrown() {
        assertThrows(InvalidHousekeeperProfileException.class, () -> new CommandParser()
                .parse("add housekeeper Sally / 81/"));
    }

    @Test
    public void commandParser_addCommandInvalidName_exceptionThrown() {
        assertThrows(NameNotStringException.class, () ->
                new CommandParser().parse("add housekeeper Susan12 / fifty"));
    }

    @Test
    public void commandParser_addCommandNoName_exceptionThrown() {
        assertThrows(InvalidHousekeeperProfileException.class, () ->
                new CommandParser().parse("add housekeeper  / 30"));
    }

    @Test
    public void commandParser_addCommandInvalidNameSymbol_exceptionThrown() {
        assertThrows(NameNotStringException.class, () ->
                new CommandParser().parse("add housekeeper @@@@$S / fifty"));
    }


    @Test
    public void commandParser_addCommandInvalidSymbol_exceptionThrown() {
        assertThrows(InvalidHousekeeperProfileException.class, () ->
                new CommandParser().parse("add housekeeper jane ^ 60"));
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
