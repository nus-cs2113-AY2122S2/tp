package seedu.duke.controllers;

import java.util.Scanner;

public class MainController extends Controller {
    /**
     * Creates the MainController.
     *
     * @param scanner Scanner for System.in.
     */
    public MainController(Scanner scanner) {
        super(new String[]{
                "Exit Application",
        }, scanner);
    }

    @Override
    protected boolean optionSwitcher(int choice) throws IllegalArgumentException {
        switch (choice) {
        case 0:
            // Relinquish control. This will return to the programme main function.
            System.out.println("Exiting application...");
            return true;
        }
        System.out.println("Now in Main Menu.");
        System.out.println(this);
        return false;
    }

    /**
     * Override takeControl to print welcome message.
     */
    @Override
    public void takeControl() {
        System.out.println("Welcome to Restaurant Information Programme!");
        super.takeControl();
    }
}
