package seedu.duke.storage;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.roomlists.Room;
import seedu.duke.roomlists.RoomType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/room_file.txt";

    public void load(ArrayList<Room> roomList) throws IOException, HotelLiteManagerException {
        Boolean isNewFile = !isFileExist(FILE_PATH);
        File file = getFile(FILE_PATH);
        if (isNewFile) {
            createNewRoomList(roomList);
            this.save(roomList);
            return;
        }
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String type = splitData[0].trim();
            RoomType roomType = getRoomTypeStr(type);
            int id = Integer.parseInt(splitData[1].trim());
            int level = Integer.parseInt(splitData[2].trim());
            String status = splitData[3].trim();
            Room room = new Room(id, level, roomType, status);
            roomList.add(room);
        }
    }

    private void createNewRoomList(ArrayList<Room> roomList) {
        Room r1 = new Room(101, 1, RoomType.Single);
        roomList.add(r1);
        r1 = new Room(102, 1, RoomType.Single);
        roomList.add(r1);
        r1 = new Room(103, 1, RoomType.Double);
        roomList.add(r1);
        r1 = new Room(201, 2, RoomType.Double);
        roomList.add(r1);
        r1 = new Room(202, 2, RoomType.Triple);
        roomList.add(r1);
        r1 = new Room(203, 2, RoomType.Triple);
        roomList.add(r1);
        r1 = new Room(204, 2, RoomType.Queen);
        roomList.add(r1);
        r1 = new Room(301, 3, RoomType.Queen);
        roomList.add(r1);
        r1 = new Room(302, 3, RoomType.King);
        roomList.add(r1);
        r1 = new Room(303, 3, RoomType.King);
        roomList.add(r1);
        r1 = new Room(401, 4, RoomType.Twin);
        roomList.add(r1);
        r1 = new Room(402, 4, RoomType.Twin);
        roomList.add(r1);
    }

    private RoomType getRoomTypeStr(String type) {
        switch (type) {
        case "Single":
            return RoomType.Single;
        case "Double":
            return RoomType.Double;
        case "Triple":
            return RoomType.Triple;
        case "Queen":
            return RoomType.Queen;
        case "King":
            return RoomType.King;
        case "Twin":
            return RoomType.Twin;
        default: return null;
        }
    }

    public void save(ArrayList<Room> roomArrayList) throws IOException, HotelLiteManagerException {
        File file = getFile(FILE_PATH);
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Room room : roomArrayList) {
            fileWriter.write(room.toFileString() + System.lineSeparator());
        }
        fileWriter.close();
    }

}
