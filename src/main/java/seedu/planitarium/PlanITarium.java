package seedu.planitarium;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class PlanITarium {
    public static final Logger logger = Logger.getLogger(PlanITarium.class.getName());

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) {
        new PlanITarium().run();
    }

    public void run() {
        Scanner in = new Scanner();
        initialisePlanitarium();
        // executeCommands();
        printExitMessage();
    }

    private static void initialisePlanitarium() {
        try {
            initialiseLogger();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
        printWelcomeMessage();
    }

    private static void initialiseLogger() throws IOException {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        FileHandler fh = new FileHandler("PlanITarium.log");
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        logger.addHandler(fh);
    }

    private static void printWelcomeMessage() {
        String logo = "$$$$$$$  $$                   $$ $$$$$$$$               $$\n"
            + "$$$$$$$  $$                   $$ $$$$$$$$\n"
            + "$$ $$$$  $$  $$$$$$  $$$$$$$  $$    $$    $$$$$$  $$$$$ $$  $$   $$  $$$$$$$$$$\n"
            + "$$ $$$$  $$  $$$$$$  $$$$$$$  $$    $$    $$$$$$  $$$$$ $$  $$   $$  $$$$$$$$$$\n"
            + "$$$$$$$  $$    $$$$  $$$$$$$  $$    $$      $$$$  $$$$$ $$  $$   $$  $$$$$$$$$$\n"
            + "$$$$$$$  $$   $$$$$  $$$ $$$  $$    $$     $$$$$  $$$   $$  $$  $$$  $$$$$$$$$$\n"
            + "$$       $$  $$$$$$  $$$  $$  $$    $$    $$$$$$  $$$   $$  $$$ $$$  $$  $$  $$\n"
            + "$$       $$  $$$$$$$ $$   $$  $$    $$    $$$$$$$ $$    $$  $$$$$$$  $$  $$  $$\n"
            + "$$       $$  $$$$$$$ $$   $$  $$    $$    $$$$$$$ $$    $$  $$$$$$$  $$  $$  $$\n"
            + "$$       $$  $$$$$$$ $$   $$  $$    $$    $$$$$$$ $$    $$  $$$$$$$  $$  $$  $$\n";
        System.out.println(logo);
    }

    private static void printExitMessage() {
        System.out.println("See you next time~");
    }
}
