package seedu.duke;

import java.util.List;
import static manager.OrderManager.orders;

public class OrderReceiptManager {
    public void printOrderReceipt() {
        if (this.orders.size() == 0) {
            System.out.println("No orders!");
        }
        for (Order order: orders) {
            for(Dish dish : order) {
                System.out.println(dish.toString());
            }
            System.out.println("Total price:" + order.getTotalPrice());
        }
        System.out.println("Total revenue:" + OrderManager.getAllOrderValue());
    }
}

