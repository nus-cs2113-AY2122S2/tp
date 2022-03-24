package seedu.splitlah;

import seedu.splitlah.command.ActivityEditCommand;
import seedu.splitlah.command.Command;
import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Entry point of the SplitLah application.
 * Initializes SplitLah and starts interacting with the user.
 */
public class SplitLah {
    static Manager manager;

    public static void main(String[] args) throws InvalidDataException {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob Charlie";
        manager = new Manager();
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);
        String activityOneArgs = "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        Command createActivityOne = Parser.getCommand(activityOneArgs);
        createActivityOne.run(manager);
        String activityTwoArgs = "activity /create /sid 1 /n Lunch /p Alice /i Bob Charlie /cl 5 5";
        Command createActivityTwo = Parser.getCommand(activityTwoArgs);
        createActivityTwo.run(manager);
        String edit = "activity /edit /sid 1 /aid 1 /p Bob /co 30";
        Command editCommand = new ActivityEditCommand(1, 1, null, "Bob", null, 30.0);
        editCommand.run(manager);
        System.out.println(manager.getProfile().getSession(0).getActivityList());
        new SplitLah().run();
    }

    /** Sets up the required objects for application.  */
    public SplitLah() {
        manager = new Manager(true);
    }

    /** Runs the program until it terminates.  */
    private void run() {
        showWelcomeMessage();
        runProcessLoop();
        exitApplication();
    }

    /** Prints welcome message.  */
    private void showWelcomeMessage() {
        manager.getUi().printWelcome();
    }

    /** Exits the program.  */
    private void exitApplication() {
        manager.getLogger().log(Level.INFO, Message.LOGGER_SPLITLAH_APPLICATION_EXIT);
        System.exit(0);
    }

    /** Reads the user input until the user enters the exit command.  */
    private void runProcessLoop() {
        Command command;
        do {
            String userInput = manager.getUi().readNextLine();
            command = Parser.getCommand(userInput);
            command.run(manager);
        } while (!Command.isExitCommand(command));
        manager.saveProfile();
    }
}
