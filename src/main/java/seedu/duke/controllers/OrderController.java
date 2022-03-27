package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.loggers.MainLogger;
import seedu.duke.manager.DishManager;
import seedu.duke.manager.OrderManager;

public class OrderController extends Controller {
    private static final String[] CHOICES = {"Exit", "Display Menu",
        "Create an order", "Delete an order",
        "Get total value of current order",
        "Get total value of all orders in the list", "Print receipt"
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
            dishManager.printDishes();
            break;
        case 2:
            addNewOrder();
            break;
        case 3:
            deleteOrder();
            break;
        case 4:
            displayOrderPrice();
            break;
        case 5:
            displayAllOrderPrice();
            break;
        case 6:
            printOrder();
            break;
        default:
            System.out.println("Unknown choice!");
            break;
        }
        System.out.println(this);
        return false;
    }

    private void addNewOrder() throws OperationTerminationException {
        int index = InputParser.getInteger("Enter dishes you want to order (enter negative number to exit): ");
        int createdOrderIdx = orderManager.getOrderCount();
        try {
            while (index >= 0) {
                Dish dish = dishManager.getDishes().get(index);
                orderManager.addDishToOrder(dish, createdOrderIdx);
                index = InputParser.getInteger("You have "
                        + orderManager.getOrders().get(createdOrderIdx).getDishCount()
                        + " dish(es), some more: \n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid dish index and try again.");
        }

    }

    private void deleteOrder() throws OperationTerminationException {
        try {
            int userInputInt = InputParser.getInteger("Enter the order you want to delete: ");
            orderManager.deleteOrder(userInputInt);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid order index.");
        }
    }

    private void displayOrderPrice() throws OperationTerminationException {
        try {
            int userInputInt = InputParser.getInteger("Enter the order you want to get price: ");
            System.out.printf("Total value of this order: %f. \n", orderManager.getOrderPrice(userInputInt));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid order index.");
        }
    }

    private void displayAllOrderPrice() {
        System.out.printf("Total value of all orders: %f. \n", orderManager.getAllOrderValue());
    }

    private void printOrder() throws OperationTerminationException {
        try {
            int userInputInt = InputParser.getInteger("Enter the order you want to display: ");
            System.out.println("These is your order. \n" + orderManager.getOrder(userInputInt));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid order index");
        }
    }

    @Override
    public void takeControl() {
        System.out.println("Entering Order Menu...\n");
        super.takeControl();
    }
}
