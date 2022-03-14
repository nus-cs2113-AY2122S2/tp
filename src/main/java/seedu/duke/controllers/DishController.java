package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.DishManager;

/**
 * DishController (or MenuController if you like).
 */
public class DishController extends Controller {
    private static final String[] CHOICES = {"Exit Menu", "Add Dish", "Delete Dish",
            "Change the price of a dish", "Change the name of a dish"
    };
    private final DishManager dishManager;

    public DishController() {
        super(CHOICES);
        dishManager = new DishManager();
    }

    @Override
    protected boolean optionSwitcher(int choice) throws OperationTerminationException {
        try {
            switch (choice) {
            case 1:
                addDish();
                break;
            case 2:
                deleteDish();
                break;
            case 3:
                changePrice();
                break;
            case 4:
                changeName();
                break;
            case 0:
                System.out.println("Exiting Menu...");
                return true;
            default:
                System.out.println("Unknown choice!");
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please make sure you don't name an empty dish!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please make sure the index is valid");
        }
        System.out.println("You are using Menu function");
        System.out.println(this);
        return false;
    }

    private void changeName() throws IndexOutOfBoundsException, IllegalArgumentException, OperationTerminationException {
        System.out.println("Changing name...");
        int index = InputParser.getInteger("The index of dish");
        String name = InputParser.getString("The new name of dish");
        dishManager.setName(index, name);
    }

    private void changePrice() throws IndexOutOfBoundsException, IllegalArgumentException, OperationTerminationException {
        System.out.println("Changing price...");
        int index = InputParser.getInteger("The index of dish");
        double newPrice = InputParser.getDouble("The new price of dish: ");
        dishManager.setPrice(index, newPrice);
    }

    private void deleteDish() throws IndexOutOfBoundsException, OperationTerminationException {
        System.out.println("Deleting dish");
        int index = InputParser.getInteger("The index of dish");
        dishManager.deleteDish(index);
    }

    private void addDish() throws IllegalArgumentException, OperationTerminationException {
        System.out.println("Adding new dish...");
        String name = InputParser.getString("The name of dish: ");
        double price = InputParser.getDouble("The price of dish: ");
        dishManager.addDish(new Dish(name, price));
    }

    @Override
    public void takeControl() {
        System.out.println("You are using Menu function");
        super.takeControl();
    }
}
