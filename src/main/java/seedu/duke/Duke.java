package seedu.duke;

import java.util.Scanner;
import seedu.duke.commands.Command;
import seedu.duke.ui.TextUi;
import static seedu.duke.common.Messages.MESSAGE_DIVIDER;

/**
 * MeetingJio is an application that allows users to find potential time slots for team meetings based on everyone’s availability.
 * Users can add a new lesson and delete a lesson in a timetable.
 */
public class Duke {

    private static Scanner in = new Scanner(System.in);

    /** Starts the interaction with the user. */
    public static void main(String[] args) {
        start();
        String name = in.nextLine();
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("Hello! " + name);
        TextUi.showHelpHint();
        System.out.println(MESSAGE_DIVIDER);

        Timetable timetable = new Timetable();
        String userInput = in.nextLine();
        Command.executeCommand(userInput, timetable, in);
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
