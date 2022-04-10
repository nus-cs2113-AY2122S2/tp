package seedu.simplst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.simplst.jsonkeyconstants.OrderKeys;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.LargeQuantityException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private String receiver;
    private String shippingAddress;
    private ArrayList<Orderline> orderlines = new ArrayList<>();
    private Boolean isFulfilled = false;

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

    public ArrayList<Orderline> getOrderlines() {
        return orderlines;
    }

    public Orderline getOrderline(String sku) {
        for (Orderline orderline : orderlines) {
            if (orderline.getSku().equals(sku)) {
                return orderline;
            }
        }
        return null;
    }

    public void addOrderline(UnitGood unitGood, String qty)
            throws WrongCommandException {
        try {
            int quantity = Integer.parseInt(qty);
            if (hasGood(unitGood.getSku())) {
                Orderline orderline = getOrderline(unitGood.getSku());
                assert orderline != null;
                orderline.setQuantity(quantity);
                System.out.printf("%s already exists in order. %d now required to fulfill\n",
                        orderline.getName(), orderline.getQuantity());
                return;
            }
            Orderline orderline = new Orderline(unitGood,
                    orderlines.size() + 1, quantity);
            orderlines.add(orderline);
            System.out.printf("%s is added to order. %d required to fulfill\n",
                    orderline.getName(), orderline.getQuantity());
        } catch (NumberFormatException e) {
            System.out.println("Quantity must be positive number");
            throw new WrongCommandException("add", true);
        }
    }

    public void removeOrderlineByQty(String sku, String qty)
            throws WrongCommandException {
        if (qty.isBlank()) {
            throw new WrongCommandException("remove", true);
        }

        try {
            int goodsQty = Integer.parseInt(qty);
            removeOrderlineByQtyHelper(sku, goodsQty);

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

    /*
     * FOR DEV ONLY.
     *
     * @param orderlineId orderline id
     */
    /*
    public void removeOrderline(int orderlineId) {
        for (int idx = 0; idx < orderlines.size(); idx++) {
            if (orderlineId == orderlines.get(idx).getId()) {
                orderlines.remove(idx);
                return;
            }
        }
    }
    */


    // Function to print grammar for statements to print
    private String checkPlural(int numberOfGoods) {
        if (numberOfGoods <= 1) {
            return " is ";
        } else {
            return " are ";
        }
    }


    private void removeOrderlineByQtyHelper(String sku, int qty)
            throws LargeQuantityException, ItemDoesNotExistException {

        if (!hasGood(sku)) {
            throw new ItemDoesNotExistException();
        }

        Orderline orderline = getOrderline(sku);
        assert orderline != null;
        if (qty > orderline.getQuantity()) {
            throw new LargeQuantityException();
        }
        orderline.setQuantity(orderline.getQuantity() - qty);
        System.out.println(qty + " " + orderline.getName()
                + checkPlural(qty) + "removed.");
        if (orderline.getQuantity() == 0) {
            orderlines.remove(orderline);
        }
    }

    public Boolean getFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    public boolean hasGood(String sku) {
        for (Orderline orderline : orderlines) {
            if (orderline.getSku().equals(sku)) {
                return true;
            }
        }

        return false;
    }

    //    private void addExistingGood(int gid, String name, int qty) throws ItemDoesNotExistException {
    //        Orderline orderline = findGood(gid);
    //        if (orderline != null) {
    //            if (!orderline.getName().equals(name)) {
    //                throw new ItemDoesNotExistException();
    //            }
    //            int oldQty = orderline.getQuantity();
    //            orderline.setQuantity(oldQty + qty);
    //            System.out.printf("%d more %s added, total quantity of %s is now %d\n",
    //                    qty, orderline.getName(),
    //                    orderline.getName(), orderline.getQuantity());
    //        }
    //    }

    private String completed() {
        if (isFulfilled) {
            return "completed";
        }
        return "not completed";
    }

    public String toString() {
        return String.format("%d - %s (%s) : ", orderId, receiver, shippingAddress)
                + completed();
    }


    private JSONArray serializeOrderlines() {
        JSONArray ja = new JSONArray();

        return ja;
    }

    public JSONObject serialize() {
        JSONObject jo = new JSONObject();
        jo.put(OrderKeys.orderId, this.orderId);
        jo.put(OrderKeys.receiver, this.receiver);
        jo.put(OrderKeys.shippingAddress, this.shippingAddress);
        jo.put(OrderKeys.isFulfilled, this.isFulfilled);
        // jo.put(OrderKeys.totalCost, this.totalCost);
        // jo.put(OrderKeys.toFulfilBy, this.toFulfilBy);
        // jo.put(OrderKeys.fulfilledBy, this.fulfilledBy);
        // jo.put(OrderKeys.comments, this.comments);
        JSONArray jaol = this.serializeOrderlines();
        if (jaol == null) {
            return null;
        }
        jo.put(OrderKeys.orderlines, this.orderlines);
        return jo;
    }

}
