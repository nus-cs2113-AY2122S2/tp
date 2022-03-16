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
    //protected static UI ui = new UI(); in case it is needed later on 
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
            UI.exit();
        }
    }

    /**
     * Runs the entire program.
     * @throws Exception if there's unacceptable condition exits.
     */
    public void run() throws Exception {
        initialisePlanitarium();
        while (true) {
            try {
                userInput = new Scanner(System.in);
                command = commandFactory.getCommand(userInput.nextLine(), personList);
                logger.getLogger().log(Level.INFO, "Next line has been read");
                command.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Initializes the program with logger.
     */
    private static void initialisePlanitarium() {
        logger = new ProjectLogger(PlanITarium.class.getName(), "PlanITarium.log");
        logger.getLogger().log(Level.INFO, "Logger initialised");
        UI.printWelcomeMessage();
    }
}
