package seedu.duke;

import util.exceptions.ItemDoesNotExistException;
import util.exceptions.LargeQuantityException;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;

public class Warehouse {
    private int capacity;
    private static ArrayList<Order> orderLists = new ArrayList<Order>();

    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    public void viewOrder(String orderId) {
        try {
            Integer idToBeViewed = Integer.parseInt(orderId);
            for (Order order : orderLists) {
                if (idToBeViewed.equals(order.getId())) {
                    System.out.println("Viewing order with id " + order.getId());
                    System.out.println("Receiver: " + order.getReceiver());
                    System.out.println("Shipping address:" + order.getShippingAddress());
                    System.out.println("Items in the order:");

                    ArrayList<Good> userGoods = order.getUserGoods();
                    int i = 1;
                    for (Good good : userGoods) {
                        System.out.println("\t" + i + ". " + good);
                        i++;
                    }
                    return;
                }
            }
            System.out.println("Could not find item with given id!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid format entered! Check format and try again!");
        }
    }

    public void viewGood(String goodId) {
        try {
            Integer idToBeViewed = Integer.parseInt(goodId);
            for (Order order : orderLists) {
                for (Good good : order.getGoods()) {
                    if (idToBeViewed.equals(good.getId())) {
                        System.out.println("Viewing item with id " + good.getId());
                        System.out.println("Item name: " + good.getName());
                        System.out.println("Item description: " + good.getDescription());
                        System.out.println("Item quantity: " + good.getQuantity());
                        return;
                    }
                }
            }
            System.out.println("Could not find item with given id!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid format entered! Check format and try again!");
        }
    }

    // Prints all inventory in a numbered list in order of input
    public void listOrders() {
        if (orderLists.isEmpty()) {
            System.out.println("There are currently no orders.");
            return;
        }
        System.out.println("List of orders:");
        int counter = 0;
        for (Order order : orderLists) {
            System.out.println("\t" + (counter + 1) + ". " + order);
            counter++;
        }
    }

    // Prints all inventory in a numbered list in order of input
    public void listGoods() {
        //int numberOfUserGoods = userGoods.size();
        if (orderLists.isEmpty()) {
            System.out.println("There are no inventory in the warehouse.");
            return;
        }
        System.out.println("List of goods:");
        System.out.println("id | name");
        int counter = 0;
        for (Order order: orderLists) {
            for (Good good : order.getGoods()) {
                System.out.println("\t" + (counter + 1) + ". " + good);
                counter++;
            }
        }
    }

    public int totalGoods() throws NullException {
        if (orderLists == null) {
            throw new NullException("userGoods");
        }
        assert (orderLists != null);
        int total = 0;
        for (Order order: orderLists) {
            for (Good good: order.getGoods()) {
                total += good.getQuantity();
            }
        }
        return total;
    }

    public void setCapacity(int capacity) {
        try {
            if (capacity < totalGoods()) {
                throw new LargeQuantityException();
            }
            this.capacity = capacity;
            System.out.printf("Current Warehouse capacity is %d\n", capacity);
        } catch (NullException nullException) {
            orderLists = new ArrayList<Order>();
            this.capacity = capacity;
            System.out.printf("Current Warehouse capacity is %d\n", capacity);
        } catch (LargeQuantityException largeQuantityException) {
            System.out.println("Current total goods in the warehouse is more"
                    + " than input capacity");
        }
    }

    public Order findOrder(int orderId) throws ItemDoesNotExistException {
        for (Order order : orderLists) {
            if (order.getId() == orderId) {
                return order;
            }
        }

        throw new ItemDoesNotExistException();
    }

    private Order findOrderContainsGood(int goodId) throws ItemDoesNotExistException {
        for (Order order : orderLists) {
            if (order.doesGoodExist(goodId)) {
                return order;
            }
        }

        throw new ItemDoesNotExistException();
    }

    public void addGoods(String orderId, String goodId, String name, String qty, String desc)
            throws WrongCommandException {
        if (orderId.isBlank()) {
            throw new WrongCommandException("add", true);
        }

        try {
            Order order = findOrder(Integer.parseInt(orderId));
            order.addGood(goodId, name, qty, desc);
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("add", true);
        } catch (ItemDoesNotExistException e2) {
            System.out.println("The order you are trying to add the goods to is not on the current list. "
                    + "Please try adding goods to an existing order or creating a new one.");
        }
    }

    public void removeGoods(String goodId, String qty) throws WrongCommandException {
        try {
            Order order = findOrderContainsGood(Integer.parseInt(goodId));
            order.removeGood(goodId, qty);
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("add", true);
        } catch (ItemDoesNotExistException e2) {
            System.out.println("The order you are trying to remove the goods from is not on the current list. "
                    + "Please try removing goods from an existing order.");
        }
    }

    public void addOrder(String id, String receiver, String shippingAddress) throws WrongCommandException {
        if (id.isBlank() || receiver.isBlank() || shippingAddress.isBlank()) {
            throw new WrongCommandException("add", true);
        }

        try {
            Order order = new Order(Integer.parseInt(id), receiver, shippingAddress);
            orderLists.add(order);
            System.out.println("Order " + id + " is added");
        } catch (NumberFormatException e) {
            throw new WrongCommandException("add", true);
        }
    }

    public void removeOrder(String id) throws WrongCommandException {
        if (id.isBlank()) {
            throw new WrongCommandException("remove", true);
        }

        try {
            int orderId = Integer.parseInt(id);
            orderLists.remove(findOrder(orderId));
            System.out.println("Order " + id + " has been removed.");
        } catch (ItemDoesNotExistException e1) {
            System.out.println("The order you are trying to remove are not on the current list. "
                    + "Please try another id.");
        } catch (NumberFormatException e2) {
            throw new WrongCommandException("remove", true);
        }
    }
}
