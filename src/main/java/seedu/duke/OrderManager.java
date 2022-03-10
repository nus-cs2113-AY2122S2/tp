package seedu.duke;

import java.util.ArrayList;

public class OrderManager {
    private ArrayList<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    //user input is dishID
    //how to trace orderIdx?
    public void addDishToOrder(Dish dish, int orderIdx) {
        Order order = this.orders.get(orderIdx);
        order.addDishToOrder(dish);
        //System.out.printf("You’ve already added %d dish(es), some more: ", order.getOrderLength());
    }

    public double getAllOrderValue() {
        double total = 0;
        for (int i = 0; i < this.orders.size(); i++) {
            total += this.orders.get(i).getTotalPrice();
        }
        return total;
    }

}