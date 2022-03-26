package seedu.duke.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private ArrayList<Dish> dishes = new ArrayList<Dish>();

    public Order(List<Dish> dishList) {
        this.dishes = new ArrayList<>(dishList);
    }

    public Order() {
        this.dishes = new ArrayList<Dish>();
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

    public void printOrder() {
        assert this.dishes.size() != 0 : "No orders!";
        for (Dish dish : dishes) {
            System.out.println(dish.toString());
        }
        System.out.println("Total price:" + this.getTotalPrice());
    }

    public List<Dish> getDishes() {
        return this.dishes;
    }

    public void addDish(Dish dish) {
        this.dishes.add(dish);
    }

    public int getDishCount() {
        if (this.dishes.size() > 0) {
            return this.dishes.size();
        }
        return -1;

    }
}
