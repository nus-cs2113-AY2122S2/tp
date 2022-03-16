package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.OrderManager;

import java.util.Scanner;

public class OrderController extends Controller {
    private static final String[] CHOICES = {"Exit", "Display Menu",
        "Create an order", "Delete an order",
        "Get total value of current order",
        "Get total value of all orders in the list", "Print receipt"
    };
    private final OrderManager orderManager;

    public OrderController() {
        super(CHOICES);
        orderManager = new OrderManager();
    }

    @Override
    protected boolean optionSwitcher(int choice) throws OperationTerminationException {
        switch (choice) {
        case 0:
            System.out.println("Exiting menu...");
            return true;
        case 1:
            System.out.println("Implement me to view menu :D");
            break;
        case 2:
            quitOrder();
            break;
        case 3:
            deleteOrder();
            break;
        case 4:
            getOrderPrice();
            break;
        case 5:
            getAllOrderPrice();
            break;
        case 6:
            printReceipt();
            break;
        default:
            System.out.println("Unknown choice!");
            break;
        }
        System.out.println(this);
        return false;
    }

    private void quitOrder() throws OperationTerminationException {
        boolean notQuit = true;
        System.out.println("Enter dishes you want to order (Q/q to exit): ");
        Scanner sc = new Scanner(System.in);
        char userInput = sc.next().charAt(0);

        if (userInput == 'Q' || userInput == 'q') {
            notQuit = false;
        }
        while (notQuit) {
            Object inputObj = userInput;
            int size = orderManager.addDishToOrder(inputObj);
            System.out.printf("You’ve already added " + size + " dish(es), some more: \n");
            userInput = sc.next().charAt(0);
            if (userInput == 'Q' || userInput == 'q') {
                notQuit = false;
            }
        }
    }

    private void deleteOrder() throws OperationTerminationException {
        int userInputInt = InputParser.getInteger("Enter the order you want to delete: ");
        orderManager.deleteOrder(userInputInt);
    }

    private void getOrderPrice() throws OperationTerminationException {
        int userInputInt = InputParser.getInteger("Enter the order you want to get price: ");
        System.out.printf("Total value of all orders: %f. \n", orderManager.getOrderPrice(userInputInt));
    }

    private void getAllOrderPrice() {
        System.out.printf("Total value of all orders: %f. \n", orderManager.getAllOrderValue());
    }

    private void printReceipt() {
        System.out.println("These are all your orders receipts. \n");
        orderManager.printReceipt();
    }

    @Override
    public void takeControl() {
        System.out.println("Entering Order Menu...\n");
        super.takeControl();
    }
}
