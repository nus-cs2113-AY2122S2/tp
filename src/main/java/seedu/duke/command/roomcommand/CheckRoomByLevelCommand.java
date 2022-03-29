package seedu.duke.command.roomcommand;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.Room;
import seedu.duke.AssignmentMap;
import seedu.duke.RoomList;
import seedu.duke.InvalidLevelException;



/**
 * Class that implements execution behavior for listing the information
 * of all rooms with corresponding category.
 * Information contains its type, room id, level and vacancy status
 */
public class CheckRoomByLevelCommand extends Command {
    private int level;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus\t\t\tHouse Keeper Name";

    /**
     * Extracts the room level from user input.
     *
     * @param commandStringWithoutCommand contains the information of the level.
     */
    public CheckRoomByLevelCommand(String commandStringWithoutCommand) {
        level = Integer.parseInt(commandStringWithoutCommand.trim());
    }


    /**
     * Override of execute command in Command class.
     * Print out all room information with corresponding level
     * including the information of:
     * type, room number, level and status.
     *
     * @param listContainer asdd
     * @param ui            The user interface for this execution method.
     * @throws InvalidLevelException if the level is invalid.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui)
            throws InvalidLevelException {
        RoomList roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        boolean isValidLevel = isValidLevel(level, roomList);
        if (!isValidLevel) {
            throw new InvalidLevelException();
        }
        printRoom(level, roomList, assignmentMap);
    }

    private void printRoom(int level, RoomList roomList, AssignmentMap assignmentMap) {
        System.out.println(TABLE_HEAD);
        for (Room room : roomList.getRoomList()) {
            if (room.getLevel() == level) {
                System.out.println(room.toString() + "\t\t\t"
                        + assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()));
            }
        }
    }

    private boolean isValidLevel(int level, RoomList roomList) {
        for (Room room : roomList.getRoomList()) {
            if (room.getLevel() == level) {
                return true;
            }
        }
        return false;
    }
}
