package seedu.duke;

import seedu.duke.command.Command;

/**
 * Class that implements execution behavior to list room with corresponding
 * room number.
 * The information includes its type, room id, level and vacancy status.
 */
public class CheckRoomCommand extends Command {
    private int roomId;

    /**
     * Extracts the room level from user input.
     *
     * @param commandStringWithoutCommand contains the information of room number.
     */
    public CheckRoomCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        String command = commandStringWithoutCommand.trim();
        if (command.isEmpty()) {
            throw new InvalidRoomNumberException();
        }
        roomId = Integer.parseInt(command);
    }


    /**
     * Override of execute command in Command class.
     * Print out the room information with corresponding room number
     * including information of:
     * type, room number, level and status.
     *
     * @param listContainer asd
     * @param ui            The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws InvalidRoomNumberException {
        RoomList roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                ui.printTableHeader();
                System.out.println(room + "\t\t\t"
                        + assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()));
                return;
            }
        }
        throw new InvalidRoomNumberException();
    }
}

