package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.roomcommand.CheckOutCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidRoomNumberException;
import seedu.duke.exceptions.RoomAlrVacantException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckOutCommandTest {

    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_201 = 3;

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
    }

    @Test
    public void commandParser_CheckOutRoomOccupied_success() throws Exception {
        CommandParser commandParser = new CommandParser();
        Command command1 = commandParser.parse("check in 201");
        command1.execute(listContainer, ui);
        Command command2 = commandParser.parse("check out 201");
        command2.execute(listContainer, ui);
        assertEquals(true, listContainer.getRoomList().getRoomList().get(INDEX_OF_201).getIsVacant());
    }

    @Test
    public void commandParser_CheckOutRoomVacant_ExceptionThrown() throws HotelLiteManagerException, IOException {
        CheckOutCommand checkOutCommand = new CheckOutCommand("202");
        assertThrows(RoomAlrVacantException.class, () -> checkOutCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_CheckOutRoomNotExist_ExceptionThrown() throws HotelLiteManagerException {
        CheckOutCommand checkOutCommand = new CheckOutCommand("105");
        assertThrows(InvalidRoomNumberException.class, () -> checkOutCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_CheckOutRoomNotNumber_ExceptionThrown() throws HotelLiteManagerException {
        assertThrows(InvalidRoomNumberException.class, () -> new CheckOutCommand("abc"));
    }


}
