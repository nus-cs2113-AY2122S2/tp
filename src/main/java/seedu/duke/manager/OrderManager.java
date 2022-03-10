package seedu.duke.manager;

import java.util.ArrayList;
import java.util.Scanner;
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

    public void addDishToOrder() {
        Order order = null;
        boolean notQuit = true;
        System.out.println("Enter dishes you want to order (Q/q to exit): ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        if (userInput == "Q" || userInput == "q") {
            notQuit = false;
        }
        while (notQuit) {
            Object inputObj = userInput;
            if (inputObj instanceof Integer && ((Integer) inputObj <= this.dishes.size())) {
                int dishIdx = (Integer) inputObj;
                Dish dish = this.dishes.get(dishIdx);
                order.addDishToOrder(dish);
            } else {
                System.out.println("Please enter a valid input: number of dish in the menu.");
            }
            System.out.printf("You’ve already added %d dish(es), some more: \n", this.dishes.size());
            userInput = sc.nextLine();
            if (userInput == "Q" || userInput == "q") {
                notQuit = false;
            }
        }
        addOrder(order);
    }

    public void deleteOrder() {
        System.out.println("Enter the order you want to delete: ");
        Scanner sc = new Scanner(System.in);
        int userInput = sc.nextInt();
        this.orders.remove(userInput);
    }

    public double getOrderPrice() {
        System.out.println("Enter the order you want to get price: ");
        Scanner sc = new Scanner(System.in);
        int userInput = sc.nextInt();
        return this.orders.get(userInput).getTotalPrice();
    }

    public double getAllOrderValue() {
        double total = 0;
        for (int i = 0; i < this.orders.size(); i++) {
            total += this.orders.get(i).getTotalPrice();
        }
        return total;
    }

}