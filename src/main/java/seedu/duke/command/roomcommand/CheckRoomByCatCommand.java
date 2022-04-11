package seedu.duke.command.roomcommand;

import seedu.duke.AssignmentMap;
import seedu.duke.exceptions.InvalidCategoryException;
import seedu.duke.ListContainer;
import seedu.duke.roomlists.Room;
import seedu.duke.roomlists.RoomList;
import seedu.duke.roomlists.RoomType;
import seedu.duke.Ui;
import seedu.duke.command.Command;


/**
 * Class that implements execution behavior for listing the information
 * of all rooms with corresponding category.
 * Information contains its type, room id, level and vacancy status
 */
public class CheckRoomByCatCommand extends Command {
    private RoomType type;

    /**
     * Extracts the room type from user input.
     *
     * @param commandStringWithoutCommand contains the information of the room type.
     * @throws InvalidCategoryException If the category is not valid.
     */
    public CheckRoomByCatCommand(String commandStringWithoutCommand) throws InvalidCategoryException {
        switch (commandStringWithoutCommand.trim()) {
        case "single":
            type = RoomType.Single;
            break;
        case "double":
            type = RoomType.Double;
            break;
        case "triple":
            type = RoomType.Triple;
            break;
        case "queen":
            type = RoomType.Queen;
            break;
        case "twin":
            type = RoomType.Twin;
            break;
        case "king":
            type = RoomType.King;
            break;
        default:  // throw !!!!!!!
            throw new InvalidCategoryException();
        }
    }


    /**
     * Override of execute command in Command class.
     * Print out all room information with corresponding room type
     * including the information of:
     * type, room number, level, status and assigned housekeeper.
     * @param listContainer The object containing the necessary data structure.
     * @param ui The object that deals with user interface for the program.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        RoomList roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        ui.printTableHeader();
        for (Room room : roomList.getRoomList()) {
            if (room.getType() == type) {
                System.out.println(room.toString()
                        + String.format("%-30s", assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()))
                );
            }
        }
    }
}

