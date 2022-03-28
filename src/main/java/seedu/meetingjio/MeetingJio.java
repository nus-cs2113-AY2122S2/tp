package seedu.meetingjio;

import java.util.Scanner;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.storage.StorageFile;
import seedu.meetingjio.ui.Ui;
import static seedu.meetingjio.common.Messages.MESSAGE_DIVIDER;

/**
 * MeetingJio is an application that allows users to find potential time slots
 * for team meetings based on everyone’s availability.
 * Users can add a new lesson and delete a lesson in a timetable.
 */
public class MeetingJio {

    private static Scanner in = new Scanner(System.in);

    private static MasterTimetable masterTimetable = new MasterTimetable();

    /** Starts the interaction with the user. */
    public static void main(String[] args) {
        start();
        String name = in.nextLine().trim();
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! " + name);
        Ui.showHelpHint();
        System.out.println(MESSAGE_DIVIDER);

        String userInput = in.nextLine().trim();

        Ui.executeCommand(userInput, masterTimetable, in);
        StorageFile.saveData(masterTimetable);
        Ui.showGoodByeMessage();
    }

    /** Initializes the application. */
    private static void start() {
        try {
            StorageFile.loadData(masterTimetable);
            Ui.showWelcomeMessage();
        } catch (RuntimeException e) {
            /**TextUi.showInitFailedMessage();*/
            e.printStackTrace();
        }
    }
}
