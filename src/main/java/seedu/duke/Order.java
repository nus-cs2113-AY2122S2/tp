package seedu.duke;

import java.util.ArrayList;

public class Order {
    private ArrayList<Dish> order;

    Order() {
        this.order = new ArrayList<Dish>();
    }

    void addDishToOrder(Dish dish) {
        this.order.add(dish);
    }

    void removeDishFromOrderByIndex(int i) {
        this.order.remove(i);
    }

    public int getOrderLength() {
        return this.order.size();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < this.order.size(); i++) {
            totalPrice += this.order.get(i).price;
        }
        return totalPrice;
    }

//    public String displayOrder() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("-----")
//
//        return sb.toString();
//    }

}
