package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.loggers.MainLogger;
import seedu.duke.manager.DishManager;
import seedu.duke.manager.DishStorageManager;

/**
 * DishController (or MenuController if you like).
 */
public class DishController extends Controller {
    private static final String[] CHOICES = {"Exit Menu", "List Dish", "Add Dish",
        "Delete Dish", "Change the price of a dish", "Change the name of a dish"};
    private final DishManager dishManager;

    public DishController() {
        super(CHOICES);
        dishManager = new DishManager();
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
            System.out.println("Exiting Menu...");
            dishManager.store();
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
        dishManager.printDishes();
    }

    private void changeName() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is changing the name of dish");
        System.out.println("Changing name...");
        int index = InputParser.getInteger("The index of dish: ");
        String name = InputParser.getString("The new name of dish: ");
        try {
            dishManager.setName(index, name);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please make sure the index is valid");
        } catch (IllegalArgumentException e) {
            System.out.println("Please make sure the name is not empty");
        }
    }

    private void changePrice() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is changing the price of dish");
        System.out.println("Changing price...");
        int index = InputParser.getInteger("The index of dish: ");
        double newPrice = InputParser.getDouble("The new price of dish: ");
        try {
            dishManager.setPrice(index, newPrice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please make sure the index is valid");
        } catch (IllegalArgumentException e) {
            System.out.println("Please make sure the price is not negative");
        }
    }

    private void deleteDish() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is deleting dish");
        System.out.println("Deleting dish...");
        int index = InputParser.getInteger("The index of dish: ");
        try {
            dishManager.deleteDish(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please make sure the index is valid");
        }
    }

    private void addDish() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is adding dish");
        System.out.println("Adding new dish...");
        String name = InputParser.getString("The name of dish: ");
        double price = InputParser.getDouble("The price of dish: ");
        try {
            dishManager.addDish(new Dish(name, price));
        } catch (IllegalArgumentException e) {
            System.out.println("Please make sure the name is not empty and price is positive");
        }
    }
}
