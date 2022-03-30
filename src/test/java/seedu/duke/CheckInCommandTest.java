package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.roomcommand.CheckInCommand;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckInCommandTest {
    // format
    // methodBeingTested_InputCondition_expectedOutcome
    // test for successful and fail and how it fails

    //Happy path
    @Test
    void execute_validRoomNumber_expectRoomBeingOccupied() throws HotelLiteManagerException {
        ListContainer listContainer = null;
        try {
            listContainer = new ListContainer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RoomList inputRoomList = listContainer.getRoomList();
        Ui ui = new Ui();
        String inputCommand = "301";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        try {
            checkInCommand.execute(listContainer, ui);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Room expectedRoom = null;
        for (Room room : inputRoomList.getRoomList()) {
            if (room.getRoomId() == 301) {
                expectedRoom = room;
            }
        }
        assertEquals("Queen\t\t301\t\t\t 3\t\t\tOccupied", expectedRoom.toString());

    }

    //unhappy
    @Test
    void execute_inValidRoomNumber_throwException() throws IOException, HotelLiteManagerException {
        ListContainer listContainer = new ListContainer();
        Ui ui = new Ui();
        String inputCommand = "601";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        Room expectedRoom = null;
        assertThrows(InvalidRoomNumberException.class,
            () -> checkInCommand.execute(listContainer,ui));
    }
}
