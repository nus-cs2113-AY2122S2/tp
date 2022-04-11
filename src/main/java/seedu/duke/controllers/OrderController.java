package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.loggers.MainLogger;
import seedu.duke.manager.DishManager;
import seedu.duke.manager.OrderManager;
import java.text.DecimalFormat;

public class OrderController extends Controller {
    private static final String[] CHOICES = {"Exit Order Menu",
        "Create an order", "Delete an order",
        "Get total price of an order",
        "Get total price of all orders", "Print receipt", "Display order list", "Display dish menu"
    };
    private final OrderManager orderManager = OrderManager.getInstance();
    private final DishManager dishManager = DishManager.getInstance();

    public OrderController() {
        super(CHOICES);
    }

    @Override
    protected boolean optionSwitcher(int choice) throws OperationTerminationException {
        switch (choice) {
        case 0:
            System.out.println("Exiting menu...");
            try {
                orderManager.saveData();
            } catch (Exception e) {
                System.out.println("There was an error saving Order data!\n");
                MainLogger.logWarning(this, "Error saving Order data!");
            }
            return true;
        case 1:
            addNewOrder();
            break;
        case 2:
            deleteOrder();
            break;
        case 3:
            displayPrice();
            break;
        case 4:
            displayAllPrice();
            break;
        case 5:
            printOrder();
            break;
        case 6:
            displayOrders();
            break;
        case 7:
            System.out.println(dishManager.printDishes());
            break;
        default:
            System.out.println("Unknown choice!");
            break;
        }
        System.out.println(this);
        return false;
    }

    /**
     * Prints all orders in the records.
     */
    private void displayOrders() {
        if (orderManager.getOrderCount() > 0) {
            System.out.println("Printing all orders...");
            for (int i = 0; i < orderManager.getOrderCount(); i++) {
                System.out.printf("Order %d:\n" + orderManager.getOrder(i) + "\n", i + 1);
            }
        } else {
            System.out.println("No order now.");
        }
    }

    /**
     * Add new order.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void addNewOrder() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is creating an order");
        System.out.println("Creating a new order...");
        int index = InputParser.getInteger("Enter dishes to add, enter 0 to exit: ");
        int createdOrderIdx = orderManager.getOrderCount();
        try {
            while (index >= 1) {
                Dish dish = dishManager.getDishes().get(index - 1);
                orderManager.addToOrder(dish, createdOrderIdx);
                index = InputParser.getInteger("You have "
                        + orderManager.getOrders().get(createdOrderIdx).getDishCount()
                        + " dish(es), some more: \n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid dish index and try again.");
        }

    }

    /**
     * Delete order by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void deleteOrder() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is deleting an order");
        System.out.println("Deleting an order...");
        if (orderManager.getOrderCount() == 0) {
            System.out.println("No order now.");
        } else {
            try {
                int userInputInt = InputParser.getInteger("Enter the order you want to delete: ") - 1;
                orderManager.deleteOrder(userInputInt);
                System.out.println("Deleted successfully!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid order index.");
            }
        }
    }

    /**
     * Display order price by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void displayPrice() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is displaying the price of an order");
        System.out.println("Getting total price of an order...");
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            int userInputInt = InputParser.getInteger("Enter the order you want to get price: ") - 1;
            System.out.println("Total value of this order: " + df.format(orderManager.getPrice(userInputInt)));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid order index.");
        }
    }

    /**
     * Display total order price.
     */
    private void displayAllPrice() {
        MainLogger.logInfo(this, "User is displaying the total price of all orders");
        System.out.println("Getting total price of all orders in the list...");
        if (orderManager.getOrderCount() == 0) {
            System.out.println("No order now.");
        } else {
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("Total value of all orders: " + df.format(orderManager.getAllValue()));
        }
    }

    /**
     * Print receipt by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void printOrder() throws OperationTerminationException {
        MainLogger.logInfo(this, "User is printing receipt");
        System.out.println("Printing receipt...");
        try {
            int userInputInt = InputParser.getInteger("Enter the order you want to display: ") - 1;
            System.out.println("This is your order. \n" + orderManager.getOrder(userInputInt));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid order index.");
        }
    }

    @Override
    public void takeControl() {
        System.out.println("Entering Order Menu...\n");
        super.takeControl();
    }
}
