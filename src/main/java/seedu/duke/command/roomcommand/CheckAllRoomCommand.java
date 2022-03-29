package seedu.duke.command.roomcommand;

import seedu.duke.AssignmentMap;
import seedu.duke.RoomList;
import seedu.duke.command.Command;
import seedu.duke.Ui;
import seedu.duke.Room;
import seedu.duke.ListContainer;


/**
 * Class that implements execution behavior for listing all room.
 * information with its type, room id, level and vacancy status.
 */
public class CheckAllRoomCommand extends Command {
    private RoomList roomList;

    public CheckAllRoomCommand() {
    }

    /**
     * Override of execute command in Command class.
     * Print out all room information including:
     * type, room number, level and status.
     *
     * @param listContainer dfajdfkfl
     * @param ui            The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        this.roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        ui.printTableHeader();
        for (Room room : roomList.getRoomList()) {
            System.out.println(room.toString() + "\t\t\t"
                    + assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()));
        }
    }

}
