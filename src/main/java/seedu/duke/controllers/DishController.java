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
    private final Scanner scanner;

    public DishController() {
        super(CHOICES);
        dishManager = new DishManager();
        scanner = new Scanner(System.in);
    }

    @Override
    protected boolean optionSwitcher(int choice) {
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

    private void changeName() throws IndexOutOfBoundsException, IllegalArgumentException {
        System.out.println("Changing name");
        System.out.print("The index of dish");
        int index = scanner.nextInt();
        System.out.print("The new name of dish");
        String name = scanner.nextLine();
        dishManager.setName(index, name);
    }

    private void changePrice() throws IndexOutOfBoundsException, IllegalArgumentException {
        System.out.println("Changing price");
        System.out.print("The index of dish: ");
        int index = scanner.nextInt();
        System.out.print("The new price of dish: ");
        double newPrice = scanner.nextDouble();
        dishManager.setPrice(index, newPrice);
    }

    private void deleteDish() throws IndexOutOfBoundsException {
        System.out.println("Deleting dish");
        System.out.print("The index of dish : ");
        int index = scanner.nextInt();
        dishManager.deleteDish(index);
    }

    private void addDish() throws IllegalArgumentException {
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
