package seedu.planitarium;

import java.util.logging.Level;
import java.util.Scanner;

import seedu.planitarium.commands.Command;
import seedu.planitarium.commands.CommandFactory;
import seedu.planitarium.global.Help;
import seedu.planitarium.family.Family;
import seedu.planitarium.global.UI;
import seedu.planitarium.storage.Storage;

public class PlanITarium {
    protected Scanner in;
    protected Command command;
    protected static Family family = new Family();
    protected CommandFactory commandFactory = new CommandFactory();
    protected static Storage storage = new Storage();
    protected static ProjectLogger logger = new ProjectLogger(PlanITarium.class.getName(), "PlanITarium.log");
    protected UI ui = new UI();

    public static void main(String[] args) {
        initialisePlanitarium();
        new PlanITarium().run();
    }

    /**
     * Runs the entire program.
     */
    public void run() {
        in = new Scanner(System.in);
        while (true) {
            try {
                String userInput = in.nextLine();
                UI.printHoriLine();
                command = commandFactory.getCommand(userInput, family);
                logger.log(Level.INFO, "Next line has been read");
                command.execute();
                UI.printHoriLine();
            } catch (Exception e) {
                ui.printErrorMsg(e.toString());
            }
        }
    }

    /**
     * Initializes the program with logger.
     */
    private static void initialisePlanitarium() {
        logger.log(Level.INFO, "Logger initialised");
        Help.initialiseHelp();
        UI.printWelcomeMessage();
        family = storage.loadData();
    }
}
