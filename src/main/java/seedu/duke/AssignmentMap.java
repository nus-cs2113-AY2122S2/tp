package seedu.duke;

import java.util.HashMap;
import java.util.Map;

public class AssignmentMap {
    Map<Room, Housekeeper> map =
            new HashMap<>();

    public void addAssignment(String name, String roomID,
                              HousekeeperList housekeeperList, RoomList roomList)
            throws InvalidHousekeeperProfile, InvalidRoomNumberException {
        Room room;
        Housekeeper housekeeper;

        for (Room r : roomList.getRoomList()) {
            if (r.getRoomId() == Integer.parseInt(roomID)) {
                room = r;
                for (Housekeeper h : housekeeperList.getHousekeeperList()) {
                    if (h.getName().equalsIgnoreCase(name)) {
                        housekeeper = h;
                        map.put(room, housekeeper);
                    } else {
                        throw new InvalidHousekeeperProfile();
                    }
                }
            }
        }
        throw new InvalidRoomNumberException();
    }
}
