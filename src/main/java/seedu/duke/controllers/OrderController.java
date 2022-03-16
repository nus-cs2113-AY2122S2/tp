package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.OrderManager;

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
            addOrder();
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

    private void addOrder() throws OperationTerminationException {
        String userInput = InputParser.getString("Enter dishes you want to order (enter '-' to exit): ");
        while (true) {
            Object inputObj = userInput;
            int size = orderManager.addDishToOrder(inputObj);
            userInput = InputParser.getString("You’ve already added " + size + " dish(es), some more: \n");
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
