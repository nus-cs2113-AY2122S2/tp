package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;

public class MainController extends Controller {
    /**
     * Creates the MainController.
     */
    public MainController() {
        super(new String[]{"Exit Application"});
    }

    @Override
    protected boolean optionSwitcher(int choice) throws OperationTerminationException {
        switch (choice) {
        case 0:
            // Relinquish control. This will return to the programme main function.
            System.out.println("Exiting application...");
            return true;
        default:
            System.out.println("Invalid choice!");
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
