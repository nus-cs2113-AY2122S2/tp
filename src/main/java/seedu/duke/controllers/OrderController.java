package seedu.duke.controllers;

import seedu.duke.controllers.Controller;
import seedu.duke.entities.Dish;
import seedu.duke.manager.DishManager;
import seedu.duke.manager.OrderManager;

import java.util.Scanner;

public class OrderController extends Controller {
    private static final String[] CHOICES = {"0. Exit", "1. Display Menu",
        "2. Create an order", "3. Delete an order",
        "4. Get total value of current order",
        "5. Get total value of all orders in the list", "6, Print receipt"
    };
    private final OrderManager orderManager;

    public OrderController(Scanner scanner) {
        super(CHOICES, scanner);
        orderManager = new OrderManager();
    }

    @Override
    protected boolean optionSwitcher(int choice) throws IllegalArgumentException {
        switch (choice) {
        case 0:
            System.out.println("Exiting application...");
            return true;
        case 1:
            System.out.println("Implement me to view menu :D");
            break;
        case 2:
            orderManager.addDishToOrder();
            break;
        case 3:
            orderManager.deleteOrder();
            break;
        case 4:
            System.out.printf("Total value of all orders: %f. \n", orderManager.getOrderPrice());
            break;
        case 5:
            System.out.printf("Total value of all orders: %f. \n", orderManager.getAllOrderValue());
            break;
        case 6:
            System.out.println("Implement me to print receipt");
            break;
        default:
            // This should not happen, since the choice argument for this method is always parsed from the
            // getChoice() method, which also checks for the range of index input.
            System.out.println("Unknown choice!");
        }
        System.out.println("Now in Main Menu.");
        System.out.println(this);
        return false;
    }

    @Override
    public void takeControl() {
        System.out.println("You are using Order function");
        super.takeControl();
    }
}
