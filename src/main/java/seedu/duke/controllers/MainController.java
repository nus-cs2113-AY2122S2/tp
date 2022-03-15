package seedu.duke.controllers;

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
        System.out.println("Now in Main Menu.");
        System.out.println(this);
        return false;
    }

    private void enterDishMenu() {
        System.out.println("Entering Dish Menu...\n");
        this.controllers[0].takeControl();
    }

    private void enterOrderMenu() {
        System.out.println("Entering Order Menu...\n");
        this.controllers[1].takeControl();
    }

    private void enterStaffMenu() {
        System.out.println("Entering Staff Menu...\n");
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
