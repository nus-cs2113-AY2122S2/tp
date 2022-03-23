package seedu.splitlah;

import seedu.splitlah.command.Command;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;
//
import seedu.splitlah.ui.TableFormatter;

import java.util.logging.Level;

/**
 * Entry point of the SplitLah application.
 * Initializes SplitLah and starts interacting with the user.
 */
public class SplitLah {
    Manager manager;

    public static void main(String[] args) {
        // tableformattertest
        TableFormatter table = new TableFormatter("#", "2", "3");
        table.addTableName("tablename");
        table.addRowWithValidation("saurav", "student", "splitlah");
        table.addRowWithValidation("warren", "student", "splitlah");
        table.addRowWithValidation("roy", "a", "posb");
        table.addRowWithValidation("kasdfjlajfd", "fdlkjsaflj;", "dsalkfj;fdkl");
        System.out.println(table.toString());
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
