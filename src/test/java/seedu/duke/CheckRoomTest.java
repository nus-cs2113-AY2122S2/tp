package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.roomcommand.CheckOutCommand;
import seedu.duke.command.roomcommand.CheckRoomCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidRoomNumberException;
import seedu.duke.exceptions.RoomAlrVacantException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckRoomTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
    }

    @Test
    public void execute_CheckRoomNotExist_ExceptionThrown() throws HotelLiteManagerException {
        CheckRoomCommand checkRoomCommand = new CheckRoomCommand("105");
        assertThrows(InvalidRoomNumberException.class, () -> checkRoomCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_CheckOutRoomNotNumber_ExceptionThrown() throws HotelLiteManagerException {
        assertThrows(InvalidRoomNumberException.class, () -> new CheckRoomCommand("abc"));
    }
}
