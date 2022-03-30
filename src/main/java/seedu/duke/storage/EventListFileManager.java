package seedu.duke.storage;

import seedu.duke.exceptions.HotelLiteManagerException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import seedu.duke.eventlists.Event;
import java.util.Scanner;

public class EventListFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/event_file.txt";

    public void load(ArrayList<Event> eventList) throws IOException, HotelLiteManagerException {
        File file = getFile(FILE_PATH);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String description = splitData[0].trim();
            String atString = splitData[1].trim();
            LocalDate at = LocalDate.parse(atString);
            Event event = new Event(description, at);
            eventList.add(event);
        }
    }


    public void save(ArrayList<Event> eventList) throws IOException, HotelLiteManagerException {
        File file = getFile(FILE_PATH);
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Event event : eventList) {
            fileWriter.write(event.toFileString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}