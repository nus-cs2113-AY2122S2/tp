package seedu.planitarium;

import commands.Command;

import java.util.Scanner;

public class PlanITarium {
    protected Scanner userInput;
    protected Command commandExecuter;

    public void run() throws Exception{
        ui.printWelcomeMessage();
        while (true) {
            userInput = new Scanner(System.in);
            commandExecuter = new Command(userInput.nextLine());
            commandExecuter.execute();
        }
    }

    /**
     * Entry-point for the PlanITarium application.
     */
    public static void main(String[] args) throws Exception{
        new PlanITarium().run();
    }

}
