package seedu.duke.manager;

import java.util.ArrayList;
import seedu.duke.entities.Order;
import seedu.duke.entities.Dish;


public class OrderManager {
    private ArrayList<Order> orders;
    private DishManager dishManager = new DishManager();
    private ArrayList<Dish> dishes;

    public OrderManager() {
        this.orders = new ArrayList<Order>();
        this.dishes = dishManager.getDishes();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public int addDishToOrder(Object inputObj) {
        Order order = null;
        if (inputObj instanceof Integer && ((Integer) inputObj <= this.dishes.size())) {
            int dishIdx = (Integer) inputObj;
            Dish dish = this.dishes.get(dishIdx);
            order.addDishToOrder(dish);
        } else {
            System.out.println("Please enter a valid input: number of dish in the menu.");
        }
        addOrder(order);
        return this.orders.size();
    }

    public void deleteOrder(int userInputInt) {
        this.orders.remove(userInputInt);
    }

    public double getOrderPrice(int userInputInt) {
        return this.orders.get(userInputInt).getTotalPrice();
    }

    public double getAllOrderValue() {
        double total = 0;
        for (int i = 0; i < this.orders.size(); i++) {
            total += this.orders.get(i).getTotalPrice();
        }
        return total;
    }

    public void printReceipt() {
        if (this.orders.size() == 0) {
            System.out.println("No orders!");
        }
        for (Order order: orders) {
            for (Dish dish : dishes) {
                System.out.println(dish.toString());
            }
            System.out.println("Total price:" + order.getTotalPrice());
        }
        System.out.println("Total revenue:" + this.getAllOrderValue());
    }

}