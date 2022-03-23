package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.DishManager;
import seedu.duke.manager.OrderManager;

public class OrderController extends Controller {
    private static final String[] CHOICES = {"Exit", "Display Menu",
        "Create an order", "Delete an order",
        "Get total value of current order",
        "Get total value of all orders in the list", "Print receipt"
    };
    private OrderManager orderManager;

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
        int index = InputParser.getInteger("Enter dishes you want to order (enter '-' to exit): ");
        DishManager dishManager = new DishManager();
        assert index < dishManager.getDishes().size() : "Index out of bound!";
        while (true) {
            try {
                int size = orderManager.addDishToOrder(index);
                index = InputParser.getInteger("You’ve already added " + size + " dish(es), some more: \n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid dish index.");
            }
        }
    }

    private void deleteOrder() throws OperationTerminationException {
        try {
            int userInputInt = InputParser.getInteger("Enter the order you want to delete: ");
            orderManager.deleteOrder(userInputInt);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid index.");
        }
    }

    private void getOrderPrice() throws OperationTerminationException {
        try {
            int userInputInt = InputParser.getInteger("Enter the order you want to get price: ");
            System.out.printf("Total value of all orders: %f. \n", orderManager.getOrderPrice(userInputInt));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid index.");
        }
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
