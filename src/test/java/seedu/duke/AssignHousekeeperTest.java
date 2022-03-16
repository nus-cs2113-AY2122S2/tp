package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AssignHousekeeperTest {

    @Test
    public void commandParser_addCommandNameAvailability_success() throws Exception {
        RoomList inputRoomList = new RoomList();
        ItemList itemList = new ItemList();
        AssignmentMap assignmentMap = new AssignmentMap();
        Ui ui = new Ui();
        SatisfactionList satisfactionList = new SatisfactionList();
        HousekeeperList housekeeperList = new HousekeeperList();
        String inputCommand = "301";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        checkInCommand.execute(housekeeperList, satisfactionList, assignmentMap, inputRoomList, itemList, ui);
        Room expectedRoom = null;
        for (Room room : inputRoomList.getRoomList()) {
            if (room.getRoomId() == 301) {
                expectedRoom = room;
            }
        }

        CommandParser parser = new CommandParser();
        Command command1 = parser.parse("Add Housekeeper Susan ~ 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command1;


        Command command = parser.parse("Assign Susan ## 301");
        AssignHousekeeperCommand assignHousekeeperCommand = (AssignHousekeeperCommand) command;
        assertEquals("301", assignHousekeeperCommand.getroomID());
        assertEquals("Susan", assignHousekeeperCommand.getName());
    }
}
