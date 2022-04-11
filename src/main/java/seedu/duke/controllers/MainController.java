package seedu.duke.controllers;

import seedu.duke.loggers.MainLogger;

public class MainController extends Controller {
    private static final String[] CHOICES = {
        "Exit Application", "Enter Dish Menu", "Enter Order Menu", "Enter Staff Menu"
    };
    private final Controller[] controllers;

    /**
     * Creates the MainController.
     */
    public MainController() {
        super(CHOICES);
        this.controllers = new Controller[]{
            new DishController(), new OrderController(), new StaffController(),
        };
    }

    @Override
    protected boolean optionSwitcher(int choice) {
        switch (choice) {
        case 0:
            // Relinquish control. This will return to the programme main function.
            System.out.println("Exiting application...");
            return true;
        case 1:
            this.enterDishMenu();
            break;
        case 2:
            this.enterOrderMenu();
            break;
        case 3:
            this.enterStaffMenu();
            break;
        default:
            System.out.println("Invalid choice!");
        }
        MainLogger.logInfo(this, "Back in Main Menu.");
        System.out.println("Now in Main Menu.");
        System.out.println(this);
        return false;
    }

    private void enterDishMenu() {
        MainLogger.logInfo(this, "User entered Dish Menu.");
        this.controllers[0].takeControl();
    }

    private void enterOrderMenu() {
        MainLogger.logInfo(this, "User entered Order Menu.");
        this.controllers[1].takeControl();
    }

    private void enterStaffMenu() {
        MainLogger.logInfo(this, "User entered Staff Menu.");
        this.controllers[2].takeControl();
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
