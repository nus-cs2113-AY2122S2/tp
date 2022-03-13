package seedu.duke.controllers;

import seedu.duke.manager.OrderManager;

import java.util.Scanner;

public class OrderController extends Controller {
    private static final String[] CHOICES = {"Exit", "Display Menu",
        "Create an order", "Delete an order",
        "Get total value of current order",
        "Get total value of all orders in the list", "Print receipt"
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
            boolean notQuit = true;
            System.out.println("Enter dishes you want to order (Q/q to exit): ");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            if (userInput == "Q" || userInput == "q") {
                notQuit = false;
            }
            while (notQuit) {
                Object inputObj = userInput;
                int size = orderManager.addDishToOrder(inputObj);
                System.out.printf("You’ve already added %d dish(es), some more: \n", size);
                userInput = sc.nextLine();
                if (userInput == "Q" || userInput == "q") {
                    notQuit = false;
                }
            }
            break;
        case 3:
            System.out.println("Enter the order you want to delete: ");
            Scanner sc2 = new Scanner(System.in);
            int userInputInt = sc2.nextInt();
            orderManager.deleteOrder(userInputInt);
            break;
        case 4:
            System.out.println("Enter the order you want to get price: ");
            Scanner sc3 = new Scanner(System.in);
            userInputInt = sc3.nextInt();
            System.out.printf("Total value of all orders: %f. \n", orderManager.getOrderPrice(userInputInt));
            break;
        case 5:
            System.out.printf("Total value of all orders: %f. \n", orderManager.getAllOrderValue());
            break;
        case 6:
            System.out.println("These are all your orders receipts. \n");
            orderManager.printReceipt();
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
