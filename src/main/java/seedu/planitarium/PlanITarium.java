package seedu.planitarium;

import java.util.Scanner;

public class PlanITarium {
    
    public void run() {
        Scanner in = new Scanner();
        initialisePlanitarium();
        // executeCommands();
        printExitMessage();
    }

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) {
        new PlanITarium().run();
    }

    private static void initialisePlanitarium() {
        printWelcomeMessage();
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
