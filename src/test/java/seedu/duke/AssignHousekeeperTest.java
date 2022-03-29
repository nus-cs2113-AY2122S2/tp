package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.duke.command.Command;
import seedu.duke.command.assigncommand.AssignHousekeeperCommand;


public class AssignHousekeeperTest {

    @Test
    public void commandParser_addCommandNameAvailability_success() throws Exception {
        ListContainer listContainer = new ListContainer();
        Ui ui = new Ui();
        String inputCommand = "301";
        CheckInCommand checkInCommand = new CheckInCommand(inputCommand);
        checkInCommand.execute(listContainer, ui);
        RoomList inputRoomList = listContainer.getRoomList();
        Room expectedRoom = null;
        for (Room room : inputRoomList.getRoomList()) {
            if (room.getRoomId() == 301) {
                expectedRoom = room;
            }
        }

        CommandParser parser = new CommandParser();
        Command command1 = parser.parse("add housekeeper Susan / 23");
        AddHousekeeperCommand addHousekeeperCommand = (AddHousekeeperCommand) command1;


        Command command = parser.parse("Assign Susan ## 301");
        AssignHousekeeperCommand assignHousekeeperCommand = (AssignHousekeeperCommand) command;
        assertEquals("301", assignHousekeeperCommand.getroomID());
        assertEquals("susan", assignHousekeeperCommand.getName());
    }
}
