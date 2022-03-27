package seedu.planitarium;

import java.util.logging.Level;
import java.util.Scanner;

import seedu.planitarium.commands.Command;
import seedu.planitarium.commands.CommandFactory;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.person.Family;
import seedu.planitarium.global.UI;

public class PlanITarium {
    protected Scanner userInput;
    protected Command command;
    protected Family family = new Family();
    protected CommandFactory commandFactory = new CommandFactory();
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
        userInput = new Scanner(System.in);
        while (true) {
            try {
                command = commandFactory.getCommand(userInput.nextLine(), family);
                logger.log(Level.INFO, "Next line has been read");
                System.out.println(UI.HORI_LINE);
                command.execute();
                System.out.println(UI.HORI_LINE);
            } catch (Exception e) {
                ui.printMsg(e.toString());
            }
        }
    }

    /**
     * Initializes the program with logger.
     */
    private static void initialisePlanitarium() {
        logger.log(Level.INFO, "Logger initialised");
        UI.printWelcomeMessage();
    }
}
