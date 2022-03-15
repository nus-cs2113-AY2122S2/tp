package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckInCommandTest {
    // format
    // methodBeingTested_InputCondition_expectedOutcome
    // test for successful and fail and how it fails

    //Happy path
    @Test
    void execute_validRoomNumber_expectRoomBeingOccupied() throws HotelLiteManagerException {
        RoomList inputRoomList = new RoomList();
        ItemList itemList = new ItemList();
        Ui ui = new Ui();
        String inputCommand = "301";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        checkInCommand.execute(inputRoomList, itemList, ui);
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
        RoomList inputRoomList = new RoomList();
        ItemList itemList = new ItemList();
        Ui ui = new Ui();
        String inputCommand = "601";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        Room expectedRoom = null;
        assertThrows(InvalidRoomNumberException.class,
            () -> checkInCommand.execute(inputRoomList,itemList,ui));
    }
}
