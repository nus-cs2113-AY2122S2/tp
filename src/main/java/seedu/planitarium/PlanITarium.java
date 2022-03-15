package seedu.planitarium;

import java.util.logging.Level;
import java.util.Scanner;

import seedu.planitarium.commands.Command;
import seedu.planitarium.commands.CommandFactory;
import seedu.planitarium.person.PersonList;
import seedu.planitarium.ui.UI;

public class PlanITarium {
    protected Scanner userInput;
    protected Command command;
    protected static UI ui = new UI();
    protected PersonList personList = new PersonList();
    protected CommandFactory commandFactory = new CommandFactory();
    protected static ProjectLogger logger;

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) {
        try {
            new PlanITarium().run();
        } catch (Exception e) {
            ui.exit();
        }
    }

    /**
     * Runs the entire program.
     * @throws Exception if there's unacceptable condition exits.
     */
    public void run() throws Exception {
        initialisePlanitarium();
        while (true) {
            userInput = new Scanner(System.in);
            logger.getLogger().log(Level.INFO, "Next line has been read");
            command = commandFactory.getCommand(userInput.nextLine(), personList);
            command.execute();
        }
    }

    /**
     * Initializes the program with logger.
     */
    private static void initialisePlanitarium() {
        logger = new ProjectLogger(PlanITarium.class.getName(), "PlanITarium.log");
        logger.getLogger().log(Level.INFO, "Logger initialised");
        ui.printWelcomeMessage();
    }
}
