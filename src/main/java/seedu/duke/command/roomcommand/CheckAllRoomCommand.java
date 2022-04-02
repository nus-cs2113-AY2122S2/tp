package seedu.duke.command.roomcommand;

import seedu.duke.AssignmentMap;
import seedu.duke.ListContainer;
import seedu.duke.roomlists.Room;
import seedu.duke.roomlists.RoomList;
import seedu.duke.Ui;
import seedu.duke.command.Command;


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
     * type, room number, level, status and assigned housekeeper.
     * @param listContainer The object containing the necessary data structure.
     * @param ui The object that deals with user interface for the program.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        this.roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        ui.printTableHeader();
        for (Room room : roomList.getRoomList()) {
            System.out.println(room.toString()
                    + String.format("%-30s", assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()))
            );
        }
    }

}
