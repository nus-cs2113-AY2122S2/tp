package seedu.planitarium;

import java.util.Scanner;

import commands.Command;

public class PlanITarium {
    protected Scanner userInput;
    protected Command commandExecuter;

    public void run() {
        printWelcomeMessage();
        while (true) {
            userInput = new Scanner(System.in);
            commandExecuter = new Command(userInput.nextLine());
            //commandExecuter.execute();
        }
    }

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) {
        new PlanITarium().run();
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
}
