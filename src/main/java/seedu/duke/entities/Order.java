package seedu.duke.entities;

import java.util.List;

public class Order {
    private static List<Dish> dishes;

    public Order(List<Dish> asList) {

    }

    public Order() {
    }

    public void addDishToOrder(Dish dish) {
        this.dishes.add(dish);
    }

    public void removeDishFromOrderByIndex(int i) {
        this.dishes.remove(i);
    }

    public int getOrderLength() {
        return this.dishes.size();
    }

    public static double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < dishes.size(); i++) {
            totalPrice += dishes.get(i).getPrice();
        }
        return totalPrice;
    }

    public static void printOrder() {
        if (dishes.size() == 0) {
            System.out.println("No orders!");
        }
        for (Dish dish : dishes) {
            System.out.println(dish.toString());
        }
        System.out.println("Total price:" + getTotalPrice());
    }

}
