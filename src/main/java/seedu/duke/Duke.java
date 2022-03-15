package seedu.duke;

import java.util.Scanner;
import seedu.duke.commands.Command;
import seedu.duke.ui.TextUi;
import static seedu.duke.common.Messages.MESSAGE_DIVIDER;

public class Duke {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        TextUi.showWelcomeMessage();
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
}
