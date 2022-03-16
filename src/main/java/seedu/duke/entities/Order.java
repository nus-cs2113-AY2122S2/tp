package seedu.duke.entities;

import java.util.List;

public class Order {
    private List<Dish> dishes;

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

    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < this.dishes.size(); i++) {
            totalPrice += this.dishes.get(i).getPrice();
        }
        return totalPrice;
    }

}
