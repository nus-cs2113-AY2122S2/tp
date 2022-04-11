package seedu.meetingjio;

import java.util.NoSuchElementException;
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
        try {
            Ui.showHelpHint();
            System.out.println(MESSAGE_DIVIDER);
            String userInput = in.nextLine().trim();
            Ui.executeCommand(userInput, masterTimetable, in);
        } catch (NoSuchElementException nsee) {
            Ui.executeCommand("exit", masterTimetable, in);
        }
        exit();
    }

    /** Initializes the application.
     *  Loads the local data if any.
     *  If IOException is caught, the application has failed to start up.
     *  A corresponding error message will be shown to the user.
     */
    private static void start() {
        try {
            StorageFile.loadData(masterTimetable);
            Ui.showWelcomeMessage();
        } catch (RuntimeException e) {
            Ui.showInitFailedMessage();
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /** Saves data and shows the goodbye message when user exits the application.*/
    private static void exit() {
        StorageFile.saveData(masterTimetable);
        Ui.showGoodByeMessage();
    }
}
