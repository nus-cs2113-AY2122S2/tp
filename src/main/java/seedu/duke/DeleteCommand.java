package seedu.duke;

import static seedu.duke.Errors.ErrorTypes.INVALID_LIST_INDEX_DELETE;
import static seedu.duke.Errors.INVALID_LIST_INDEX_DELETE_STRING_DETECTED;
import static seedu.duke.Timetable.list;

public class DeleteCommand {

    public static void executeDelete(String parameters) {
        try {
            int index = Integer.parseInt(parameters);
            if (index < 0 || index >= list.size()) {
                System.out.println(INVALID_LIST_INDEX_DELETE);
            }
            Event event = list.get(index-1);
            list.remove(index-1);
            printDeleteConfirmation(event);
        } catch (NumberFormatException nfe) {
            System.out.println(INVALID_LIST_INDEX_DELETE_STRING_DETECTED);
        }
    }

    public static void printDeleteConfirmation(Event event) {
        System.out.println("The following event has been deleted from your timetable:");
        System.out.println(event);
    }
}
