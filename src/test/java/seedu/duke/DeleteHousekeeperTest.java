package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.AgeIncreaseCommand;
import seedu.duke.command.housekeepercommands.DeleteHousekeeperCommand;
import seedu.duke.exceptions.EmptyNameException;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.UserDoesNotExistException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.housekeeperlists.HousekeeperList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeleteHousekeeperTest {
    private ListContainer listContainer;
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
        Housekeeper housekeeperMan = new Housekeeper("Man", 60);
        housekeeperList.addHousekeeperInList(housekeeperMan);
    }

    @Test
    public void deleteHousekeeper_success() throws HotelLiteManagerException, IOException {
        DeleteHousekeeperCommand deleteHousekeeperCommand =
                new DeleteHousekeeperCommand("Man");
        deleteHousekeeperCommand.execute(listContainer, ui);
        HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        assertEquals(2, housekeeperList.getTotalHousekeeper());
    }

    @Test
    public void deleteHousekeeperNameDoesNotExist_exceptionThrown() throws HotelLiteManagerException {
        DeleteHousekeeperCommand deleteHousekeeperCommand =
                new DeleteHousekeeperCommand("jerry");
        assertThrows(UserDoesNotExistException.class, () -> deleteHousekeeperCommand.execute(listContainer, ui));
    }


    @Test
    public void commandParser_DeleteCommandName_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("delete housekeeper Susan");
        DeleteHousekeeperCommand deleteHousekeeperCommand = (DeleteHousekeeperCommand) command;
        assertEquals("susan", deleteHousekeeperCommand.getName());
    }

    @Test
    public void emptyName_exceptionThrown() {
        assertThrows(EmptyNameException.class, () -> new CommandParser().parse("Delete Housekeeper "));
    }

    @Test
    public void duplicateCommand_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, () ->
                new CommandParser().parse("Delete Housekeeper delete housekeeper "));
    }

}
