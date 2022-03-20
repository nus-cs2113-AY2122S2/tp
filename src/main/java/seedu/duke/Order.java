package seedu.duke;

import util.exceptions.ItemDoesNotExistException;
import util.exceptions.LargeQuantityException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private String receiver;
    private String shippingAddress;
    private ArrayList<Good> userGoods = new ArrayList<>();

    public Order(int orderId, String receiver, String shippingAddress) {
        this.orderId = orderId;
        this.receiver = receiver;
        this.shippingAddress = shippingAddress;
    }

    public int getId() {
        return orderId;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public ArrayList<Good> getUserGoods() {
        return userGoods;
    }

    public ArrayList<Good> getGoods() {
        return userGoods;
    }

    public void addGood(String id, String name, String qty,
                               String desc) throws WrongCommandException {
        if (id.isBlank() || name.isBlank() || qty.isBlank()) {
            throw new WrongCommandException("add", true);
        }
        try {
            Good good = new Good(
                    Integer.parseInt(id),
                    name,
                    Integer.parseInt(qty),
                    desc);
            userGoods.add(good);
            System.out.printf("%d %s %s added\n", good.getQuantity(), good.getName(),
                    checkPlural(good.getQuantity()));
        } catch (NumberFormatException e) {
            throw new WrongCommandException("add", true);
        }
    }

    // Function to print grammar for statements to print
    private String checkPlural(int numberOfGoods) {
        if (numberOfGoods <= 1) {
            return "is ";
        } else {
            return "are ";
        }
    }


    private void remove(int id, int qty)
            throws LargeQuantityException, ItemDoesNotExistException {

        for (Good good : userGoods) {
            if (good.getId() == id) {
                if (qty > good.getQuantity()) {
                    throw new LargeQuantityException();
                }

                good.setQuantity(good.getQuantity() - qty);

                if (qty < 2) {
                    System.out.println(qty + " " + good.getName() + " has been removed.");
                } else {
                    System.out.println(qty + " " + good.getName() + " have been removed.");
                }

                if (good.getQuantity() == 0) {
                    userGoods.remove(good);
                }

                return;
            }
        }

        throw new ItemDoesNotExistException();
    }

    public void removeGood(String id, String qty)
            throws WrongCommandException {
        if (id.isBlank() || qty.isBlank()) {
            throw new WrongCommandException("remove", true);
        }

        try {
            int goodsId = Integer.parseInt(id);
            int goodsQty = Integer.parseInt(qty);

            remove(goodsId, goodsQty);

        } catch (NumberFormatException e1) {
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e2) {
            System.out.println("The good you are trying to remove are not on the current list. "
                    + "Please try another id first.");
        } catch (LargeQuantityException e3) {
            System.out.println("The quantity input is larger than the current quantity of the goods. "
                    + "Please enter the command again with a smaller quantity.");
        }
    }

    public boolean doesGoodExist(int goodId) {
        for (Good good : userGoods) {
            if (good.getId() == goodId) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        return String.format("%d - %s (%s)", orderId, receiver, shippingAddress);
    }
}
