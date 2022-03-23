package seedu.duke.manager;

import seedu.duke.controllers.DishController;
import seedu.duke.entities.Dish;
import seedu.duke.entities.Order;

import java.util.ArrayList;

/**
 * OrderManager.
 */
public class OrderManager {
    private ArrayList<Order> orders;
    private ArrayList<Dish> dishes;

    public OrderManager(DishController dishController) {
        this.orders = new ArrayList<Order>();
        this.dishes = dishController.getDishManager().getDishes();
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
     * Add dish by index to the order.
     *
     * @param dishIdx the index of the dish.
     * @return size of the order list.
     */
    public int addDishToOrder(int dishIdx) {
        Order order = null;
        Dish dish = this.dishes.get(dishIdx);
        order.addDishToOrder(dish);
        addOrder(order);
        return this.orders.size();
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
            for (Dish dish : dishes) {
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
        return orders;
    }

    /**
     * Get all dishes of a specific order.
     *
     * @param orderIdx user input: order index.
     * @return
     */
    public ArrayList<Dish> getDishesFromOrder(int orderIdx) {
        return this.dishes;
    }

}