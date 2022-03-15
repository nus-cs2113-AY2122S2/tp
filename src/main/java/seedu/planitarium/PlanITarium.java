package seedu.planitarium;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import commands.Command;
import seedu.planitarium.person.PersonList;
import ui.UI;

public class PlanITarium {
    public static final Logger logger = Logger.getLogger(PlanITarium.class.getName());
    protected Scanner userInput;
    protected Command commandExecuter;
    protected static UI ui = new UI();
    protected PersonList personList = new PersonList();

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) {
        try {
            new PlanITarium().run();
        } catch (Exception e) {
            ui.exit(); // Need fix later
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
            commandExecuter = new Command(userInput.nextLine(), personList);
            commandExecuter.execute();
        }
    }

    /**
     * Initializes the program with logger.
     */
    private static void initialisePlanitarium() {
        try {
            initialiseLogger();
        } catch (IOException e) {
            logger.log(Level.WARNING, "File logger not working.", e);
        }
        ui.printWelcomeMessage();
    }

    /**
     * Initialize a logger for the program
     * @throws IOException if there's exception for reading or writing.
     */
    private static void initialiseLogger() throws IOException {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        FileHandler fh = new FileHandler("PlanITarium.log");
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        logger.addHandler(fh);
    }
}
