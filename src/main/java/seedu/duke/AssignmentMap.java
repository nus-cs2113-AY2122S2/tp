package seedu.duke;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.storage.AssignmentListFileManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AssignmentMap {
    HashMap<Integer, String> map;

    public AssignmentMap(HashMap<Integer, String> map) {
        this.map = map;
    }

    public void addAssignment(String name, int roomId) {
        map.put(roomId, name);
    }

    public String getHouseKeeperNameByRoom(int roomId) {
        if (!map.containsKey(roomId)) {
            return "NA";
        }
        return map.get(roomId);
    }

    public void removeAssignment(String name) {
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            if (entry.getValue().equals(name)) {
                int k = entry.getKey();
                map.remove(k);
            }
        }
    }

    public void save() throws IOException, HotelLiteManagerException {
        AssignmentListFileManager assignmentListFileManager = new AssignmentListFileManager();
        assignmentListFileManager.save(map);
    }
}
