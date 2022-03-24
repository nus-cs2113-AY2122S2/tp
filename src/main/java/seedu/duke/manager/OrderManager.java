package seedu.duke.manager;

import java.util.Arrays;
import java.util.List;
import seedu.duke.entities.Dish;
import seedu.duke.entities.Order;
import seedu.duke.loggers.MainLogger;

import java.util.ArrayList;

/**
 * OrderManager.
 */
public class OrderManager extends Manager {
    private static OrderManager singleton = null;
    private List<Order> orders;

    private static final String ORDER_STORAGE_FILE = "order.dat";

    private OrderManager() {
        super(ORDER_STORAGE_FILE);
        try {
            this.loadData();
        } catch (Exception e) {
            MainLogger.logWarning(this, e.toString());
            MainLogger.logWarning(this, "Start with an empty order");
            orders.clear();
        }
    }

    public static OrderManager getInstance() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new OrderManager();
        return singleton;
    }

    public static void resetInstance() {
        singleton = null;
    }

    @Override
    protected void loadData() throws Exception {
        this.orders = new ArrayList<>();
        ArrayList<?> list = (ArrayList<?>) this.load();
        for (Object object : list) {
            this.addOrder((Order) object);
        }
    }

    @Override
    public void saveData() throws Exception {
        this.save(this.orders);
    }

    /**
     * Add the created order to the order list.
     *
     * @param order the created order.
     */
    public void addOrder(Order order) {
        this.orders.add(order);
    }


    /**
     * add dish to an order.
     *
     * @param dishIdx index of dish.
     * @param orderIdx index of order.
     * @param dishManager dishmanager instance.
     */
    public void addDishToOrder(int dishIdx, int orderIdx, DishManager dishManager) {
        ArrayList<Dish> dishes = dishManager.getDishes();
        Dish dish = dishes.get(dishIdx);
        if (orderIdx == this.orders.size()) {
            Order order = null;
            order = new Order(Arrays.asList(new Dish(dish.getName(), dish.getPrice())));
            this.orders.add(order);
        } else {
            Order order = new Order();
            for (Dish existingDish : this.orders.get(orderIdx).getDishes()) {
                order.addDish(new Dish(existingDish.getName(), existingDish.getPrice()));
            }
            order.addDish(dish);
            this.orders.set(orderIdx, order);
        }
        System.out.println("Added successfully!\n");

    }

    /**
     * Remove an order from the order list.
     *
     * @param userInputInt user input index.
     */
    public void deleteOrder(int userInputInt) {
        this.orders.remove(userInputInt);
    }

    /**
     * Get the sum of prices of the dishes in the order.
     *
     * @param userInputInt user input index.
     * @return total value of this order.
     */
    public double getOrderPrice(int userInputInt) {
        return this.orders.get(userInputInt).getTotalPrice();
    }

    /**
     * Get revenue, total value of all orders.
     *
     * @return total value of all orders.
     */
    public double getAllOrderValue() {
        double total = 0;
        for (int i = 0; i < this.orders.size(); i++) {
            total += this.orders.get(i).getTotalPrice();
        }
        return total;
    }

    /**
     * Generate report.
     */
    public void printReceipt() {
        if (this.orders.size() == 0) {
            System.out.println("No orders!");
        }
        for (Order order : orders) {
            for (Dish dish : order.getDishes()) {
                System.out.println(dish.toString());
            }
            System.out.println("Total price:" + order.getTotalPrice());
        }
        System.out.println("Total revenue:" + this.getAllOrderValue());
    }

    /**
     * Get all orders as ArrayList.
     *
     * @return
     */
    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Get all dishes of a specific order.
     *
     * @param orderIdx user input: order index.
     * @return
     */
    public ArrayList<Dish> getDishesFromOrder(int orderIdx) {
        return new ArrayList<>(orders.get(orderIdx).getDishes());
    }

    public int getOrderCount() {
        return this.orders.size();
    }

}