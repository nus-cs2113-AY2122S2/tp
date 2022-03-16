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
    protected PersonList personList = new PersonList();
    protected CommandFactory commandFactory = new CommandFactory();
    protected static ProjectLogger logger;

    public static void main(String[] args) {
        initialisePlanitarium();
        new PlanITarium().run();
    }

    /**
     * Runs the entire program.
     */
    public void run() {
        userInput = new Scanner(System.in);
        while (true) {
            try {
                command = commandFactory.getCommand(userInput.nextLine(), personList);
                logger.getLogger().log(Level.INFO, "Next line has been read");
                System.out.println(UI.HORI_LINE);
                command.execute();
                System.out.println(UI.HORI_LINE);
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
