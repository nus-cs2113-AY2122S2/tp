package seedu.duke.command.roomcommand;

import seedu.duke.exceptions.RoomAlrOccupiedException;
import seedu.duke.roomlists.RoomList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.AssignmentMap;
import seedu.duke.roomlists.Room;
import seedu.duke.exceptions.InvalidRoomNumberException;
import seedu.duke.command.Command;

import java.io.IOException;

/**
 * Class that checks in a room and changes the status
 * of the room to be occupied.
 */

public class CheckInCommand extends Command {
    RoomList roomList;
    int roomId;

    /**
     * Override of execute command in Command class.
     * Check in room with corresponding room number.
     * The room status will be changed to occupied.
     * After check in, it will print out corresponding room information.
     *
     * @param listContainer The object containing the necessary data structure.
     * @param ui            The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the room number is not in the room list.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, IOException {
        this.roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                if (!room.getIsVacant()) {
                    throw new RoomAlrOccupiedException();
                }
                room.checkIn();
                ui.printTableHeader();
                System.out.println(room
                        + String.format("%-30s", assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()))
                );
                return;
            }
        }

        throw new InvalidRoomNumberException();
    }

    /**
     * Constructor that initializes the roomId.
     *
     * @param command contains the roomId.
     */
    public CheckInCommand(String command) throws InvalidRoomNumberException {
        if (!RoomHelper.isValidIntNumber(command.trim())) {
            throw new InvalidRoomNumberException();
        }
        roomId = Integer.parseInt(command.trim());
    }

}
