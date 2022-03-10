package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.manager.DishManager;

import java.util.Scanner;

/**
 * DishController (or MenuController if you like).
 */
public class DishController extends Controller {
    private static final String[] CHOICES = {"Exit Menu", "Add Dish", "Delete Dish",
        "Change the price of a dish", "Change the name of a dish"
    };
    private final DishManager dishManager;

    public DishController(Scanner scanner) {
        super(CHOICES, scanner);
        dishManager = new DishManager();
    }

    @Override
    protected boolean optionSwitcher(int choice) throws IllegalArgumentException {
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
            // Relinquish control. This will return to the programme main function.
            System.out.println("Exiting application...");
            return true;
        default:
            // This should not happen, since the choice argument for this method is always parsed from the
            // getChoice() method, which also checks for the range of index input.
            System.out.println("Unknown choice!");
        }
        System.out.println("Now in Main Menu.");
        System.out.println(this);
        return false;
    }

    private void changeName() throws IllegalArgumentException {
        System.out.println("Changing name");
        System.out.print("The index of dish");
        int index = scanner.nextInt();
        System.out.print("The new name of dish");
        String name = scanner.nextLine();
        try {
            dishManager.setName(index, name);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void changePrice() throws IllegalArgumentException {
        System.out.println("Changing price");
        System.out.print("The index of dish: ");
        int index = scanner.nextInt();
        System.out.print("The new price of dish: ");
        double newPrice = scanner.nextDouble();
        try {
            dishManager.setPrice(index, newPrice);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void deleteDish() throws IllegalArgumentException {
        System.out.println("Deleting dish");
        System.out.print("The index of dish : ");
        int index = scanner.nextInt();
        try {
            dishManager.deleteDish(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void addDish() {
        System.out.println("Adding new dish");
        System.out.print("The name of dish: ");
        String name = scanner.nextLine();
        System.out.print("The price of dish: ");
        double price = scanner.nextDouble();
        dishManager.addDish(new Dish(name, price));
    }

    @Override
    public void takeControl() {
        System.out.println("You are using Menu function");
        super.takeControl();
    }
}
