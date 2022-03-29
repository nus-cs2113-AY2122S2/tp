package seedu.duke.storage;

import seedu.duke.Event;
import seedu.duke.Room;
import seedu.duke.RoomType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EventListFileManager extends FileManager {
    private static final String FILE_PATH = "event_file.txt";

    public void load(ArrayList<Event> eventList) throws IOException {
        Boolean isNewFile = !isFileExist(FILE_PATH);
        File file = getFile(FILE_PATH);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String description = splitData[0].trim();
            LocalDate by = LocalDate.parse((splitData[1].trim()));
            Event event = new Event(description, by);
            eventList.add(event);
        }
    }



    public void save(ArrayList<Event> eventList) throws IOException {
        File file = getFile(FILE_PATH);
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Event event : eventList) {
            fileWriter.write(event.toFileString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
