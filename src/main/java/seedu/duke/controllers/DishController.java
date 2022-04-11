package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.loggers.MainLogger;
import seedu.duke.manager.DishManager;

/**
 * DishController (or MenuController if you like).
 */
public class DishController extends Controller {
    private static final String[] CHOICES = {"Exit Dish Menu", "List Dish", "Add Dish",
        "Delete Dish", "Change the price of a dish", "Change the name of a dish"};
    private final DishManager dishManager;

    public DishController() {
        super(CHOICES);
        dishManager = DishManager.getInstance();
    }

    @Override
    public void takeControl() {
        MainLogger.logInfo(this, "User is using menu function");
        System.out.println("Entering Dish Menu...\n");
        super.takeControl();
    }

    @Override
    protected boolean optionSwitcher(int choice) throws OperationTerminationException {
        switch (choice) {
        case 0:
            System.out.println("Exiting Dish Menu...");
            try {
                dishManager.saveData();
            } catch (Exception e) {
                System.out.println("There was an error saving Dish data!\n");
                MainLogger.logWarning(this, "Error saving Dish data!");
            }
            return true;
        case 1:
            listDishes();
            break;
        case 2:
            addDish();
            break;
        case 3:
            deleteDish();
            break;
        case 4:
            changePrice();
            break;
        case 5:
            changeName();
            break;
        default:
            System.out.println("Unknown choice!");
            break;
        }
        System.out.println(this);
        return false;
    }

    private void listDishes() {
        MainLogger.logInfo(this, "User is listing dishes");
        System.out.println("Listing dishes...");
        System.out.println(dishManager.printDishes());
    }

    private void changeName() throws OperationTerminationException {
        if (dishManager.getNumOfDishes() == 0) {
            System.out.println("You haven't added a dish!");
            return;
        }
        MainLogger.logInfo(this, "User is changing the name of dish");
        System.out.println("Changing name...");
        int index = InputParser.getInteger("The index of dish: ");
        if (index <= 0 || index > dishManager.getNumOfDishes()) {
            System.out.println("Please make sure the index is valid");
            return;
        }
        String name = InputParser.getString("The new name of dish: ");
        if (name.isEmpty()) {
            System.out.println("Please make sure the name is not empty and price is non-negative");
            return;
        }
        if (dishManager.checkNameExistence(name)) {
            System.out.println("Ooops, this dish already exists in your menu");
            return;
        }
        dishManager.setName(index, name);
    }

    private void changePrice() throws OperationTerminationException {
        if (dishManager.getNumOfDishes() == 0) {
            System.out.println("You haven't added a dish!");
            return;
        }
        MainLogger.logInfo(this, "User is changing the price of dish");
        System.out.println("Changing price...");
        int index = InputParser.getInteger("The index of dish: ");
        if (index <= 0 || index > dishManager.getNumOfDishes()) {
            System.out.println("Please make sure the index is valid");
            return;
        }
        double newPrice = InputParser.getDouble("The new price (will be rounded into 1 decimal place) of dish: ");
        if (newPrice < 0) {
            System.out.println("Please make sure the price is not negative");
            return;
        }
        dishManager.setPrice(index, newPrice);
    }

    private void deleteDish() throws OperationTerminationException {
        if (dishManager.getNumOfDishes() == 0) {
            System.out.println("You haven't added a dish!");
            return;
        }
        MainLogger.logInfo(this, "User is deleting dish");
        System.out.println("Deleting dish...");
        int index = InputParser.getInteger("The index of dish: ");
        if (index <= 0 || index > dishManager.getNumOfDishes()) {
            System.out.println("Please make sure the index is valid");
            return;
        }
        dishManager.deleteDish(index);
    }

    private void addDish() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is adding dish");
        System.out.println("Adding new dish...");
        String name = InputParser.getString("The name of dish: ");
        if (name.isEmpty()) {
            System.out.println("Please make sure the name is not empty and price is non-negative");
            return;
        }
        if (dishManager.checkNameExistence(name)) {
            System.out.println("Ooops, this dish already exists in your menu");
            return;
        }
        double price = InputParser.getDouble("The price (will be rounded into 1 decimal place) of dish: ");
        if (price < 0) {
            System.out.println("Please make sure the price is not negative");
            return;
        }
        dishManager.addDish(new Dish(name, price));
    }

    protected DishManager getDishManager() {
        return dishManager;
    }
}
