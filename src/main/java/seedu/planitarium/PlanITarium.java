package seedu.planitarium;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import commands.Command;
import ui.UI;

public class PlanITarium {
    public static final Logger logger = Logger.getLogger(PlanITarium.class.getName());
    protected Scanner userInput;
    protected Command commandExecuter;
    protected UI ui = new UI();

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) {
        try {
            new PlanITarium().run();
        } catch (Exception e) {
            UI.exit(); // Need fix later
        }
    }

    public void run() throws Exception {
        initialisePlanitarium(ui);
        while (true) {
            userInput = new Scanner(System.in);
            commandExecuter = new Command(userInput.nextLine());
            commandExecuter.execute();
        }
    }

    private static void initialisePlanitarium(UI ui) {
        try {
            initialiseLogger();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
        ui.printWelcomeMessage();
    }

    private static void initialiseLogger() throws IOException {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        FileHandler fh = new FileHandler("PlanITarium.log");
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        logger.addHandler(fh);
    }
}
