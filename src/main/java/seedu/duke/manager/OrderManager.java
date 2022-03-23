package seedu.duke.manager;

import seedu.duke.entities.Dish;
import seedu.duke.entities.Order;

import java.util.ArrayList;


public class OrderManager {
    private final ArrayList<Order> orders;
    private final DishManager dishManager = new DishManager();
    private final ArrayList<Dish> dishes;

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

    public Order getOrder(int orderIndex) throws IndexOutOfBoundsException {
        if (orderIndex < 0 || orders.size() <= orderIndex) {
            throw new IndexOutOfBoundsException();
        }
        return this.orders.get(orderIndex);
    }

    public void deleteOrder(int orderIndex) {
        this.orders.remove(orderIndex);
    }

    public double getOrderPrice(int orderIndex) {
        return this.getOrder(orderIndex).getTotalPrice();
    }

    public double getAllOrderValue() {
        double total = 0;
        for (int i = 0; i < this.orders.size(); i++) {
            total += this.orders.get(i).getTotalPrice();
        }
        return total;
    }

}