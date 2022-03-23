package seedu.meetingjio;

import java.util.Scanner;
import seedu.meetingjio.commands.Command;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;
import seedu.meetingjio.ui.TextUi;
import static seedu.meetingjio.common.Messages.MESSAGE_DIVIDER;

/**
 * MeetingJio is an application that allows users to find potential time slots
 * for team meetings based on everyone’s availability.
 * Users can add a new lesson and delete a lesson in a timetable.
 */
public class MeetingJio {

    private static Scanner in = new Scanner(System.in);

    /** Starts the interaction with the user. */
    public static void main(String[] args) {
        start();
        String name = in.nextLine();
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! " + name);
        TextUi.showHelpHint();
        System.out.println(MESSAGE_DIVIDER);

        MasterTimetable masterTimetable = new MasterTimetable();
        String userInput = in.nextLine();
        Command.executeCommand(userInput, masterTimetable, in);
        TextUi.showGoodByeMessage();
    }

    /** Initializes the application. */
    private static void start() {
        try {
            TextUi.showWelcomeMessage();
        } catch (RuntimeException e) {
            TextUi.showInitFailedMessage();
        }
    }
}
