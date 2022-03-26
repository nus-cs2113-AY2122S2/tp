package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckInCommandTest {
    // format
    // methodBeingTested_InputCondition_expectedOutcome
    // test for successful and fail and how it fails

    //Happy path
    @Test
    void execute_validRoomNumber_expectRoomBeingOccupied() throws HotelLiteManagerException {
        ListContainer listContainer = new ListContainer();
        RoomList inputRoomList = listContainer.getRoomList();
        Ui ui = new Ui();
        String inputCommand = "301";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        checkInCommand.execute(listContainer, ui);
        Room expectedRoom = null;
        for (Room room : inputRoomList.getRoomList()) {
            if (room.getRoomId() == 301) {
                expectedRoom = room;
            }
        }
        assertEquals("Queen\t\t301\t\t\t\t3\t\tOccupied", expectedRoom.toString());

    }

    //unhappy
    @Test
    void execute_inValidRoomNumber_throwException() {
        ListContainer listContainer = new ListContainer();
        Ui ui = new Ui();
        String inputCommand = "601";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        Room expectedRoom = null;
        assertThrows(InvalidRoomNumberException.class,
            () -> checkInCommand.execute(listContainer,ui));
    }
}
