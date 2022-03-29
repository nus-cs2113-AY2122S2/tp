package seedu.meetingjio.ui;

import seedu.meetingjio.parser.Parser;
import seedu.meetingjio.commands.Command;
import seedu.meetingjio.timetables.MasterTimetable;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.meetingjio.commands.ExitCommand.EXIT_COMMAND;
import static seedu.meetingjio.common.Messages.MESSAGE_DIVIDER;
import static seedu.meetingjio.common.Messages.LOGO;
import static seedu.meetingjio.common.Messages.MESSAGE_WELCOME;
import static seedu.meetingjio.common.Messages.MESSAGE_QUESTION_NAME;
import static seedu.meetingjio.common.Messages.MESSAGE_HINT;
import static seedu.meetingjio.common.Messages.MESSAGE_GOODBYE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INIT_FAILED;

/**
 * Text UI of the application.
 */
public class Ui {

    public static Logger logger = Logger.getLogger(Parser.class.getName());

    public static void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(MESSAGE_WELCOME);
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(MESSAGE_QUESTION_NAME);
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void showInitFailedMessage() {
        System.out.println(ERROR_INIT_FAILED);
    }


    public static void showHelpHint() {
        System.out.println(MESSAGE_HINT);
    }

    public static void showGoodByeMessage() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Executes the command until termination or the exit command is called.
     *
     * @param userInput full user input
     * @param masterTimetable MasterTimetable
     * @param in next user input
     */
    public static void executeCommand(String userInput, MasterTimetable masterTimetable, Scanner in) {
        while (!userInput.equalsIgnoreCase(EXIT_COMMAND)) {
            String trimmedInput = userInput.trim();
            Parser parser = new Parser(trimmedInput);
            Command parsedCommand = parser.parseCommand();
            try {
                String feedback = parsedCommand.execute(masterTimetable);
                System.out.println(feedback);
            } catch (AssertionError ae) {
                logger.log(Level.INFO, "Assertion Error");
            }
            userInput = in.nextLine();
        }
    }
}
