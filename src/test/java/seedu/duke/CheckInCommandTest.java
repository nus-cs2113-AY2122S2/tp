package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.roomcommand.CheckInCommand;
import seedu.duke.command.roomcommand.CheckOutCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidRoomNumberException;
import seedu.duke.exceptions.RoomAlrOccupiedException;
import seedu.duke.exceptions.RoomAlrVacantException;
import seedu.duke.roomlists.Room;
import seedu.duke.roomlists.RoomList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckInCommandTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_201 = 3;

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
    }

    @Test
    public void commandParser_CheckInRoomVacant_RoomBeingOccupied() throws Exception {
        CommandParser commandParser = new CommandParser();
        Command command2 = commandParser.parse("check in 201");
        command2.execute(listContainer, ui);
        assertEquals(false, listContainer.getRoomList().getRoomList().get(INDEX_OF_201).getIsVacant());
    }

    @Test
    public void commandParser_CheckInRoomOccupied_ExceptionThrown() throws HotelLiteManagerException, IOException {
        CheckInCommand checkInCommand1 = new CheckInCommand("202");
        checkInCommand1.execute(listContainer,ui);
        CheckInCommand checkInCommand2 = new CheckInCommand("202");
        assertThrows(RoomAlrOccupiedException.class, () -> checkInCommand2.execute(listContainer, ui));
    }

    @Test
    public void execute_CheckInRoomNotExist_ExceptionThrown() throws HotelLiteManagerException {
        CheckInCommand checkInCommand = new CheckInCommand("105");
        assertThrows(InvalidRoomNumberException.class, () -> checkInCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_CheckInRoomNotNumber_ExceptionThrown() throws HotelLiteManagerException {
        assertThrows(InvalidRoomNumberException.class, () -> new CheckInCommand("abc"));
    }
}
