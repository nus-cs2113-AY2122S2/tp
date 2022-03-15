package seedu.planitarium;

import java.util.Scanner;
import java.util.logging.Level;

import commands.Command;
import seedu.planitarium.person.PersonList;
import ui.UI;

public class PlanITarium {
    protected Scanner userInput;
    protected Command commandExecuter;
    protected static UI ui = new UI();
    protected PersonList personList = new PersonList();
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
            commandExecuter = new Command(userInput.nextLine(), personList);
            commandExecuter.execute();
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
