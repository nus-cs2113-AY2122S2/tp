package seedu.simplst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.simplst.jsonkeyconstants.GoodKeys;
import seedu.simplst.jsonkeyconstants.OrderKeys;
import seedu.simplst.jsonkeyconstants.OrderlinesKeys;
import seedu.simplst.jsonkeyconstants.UnitGoodKeys;
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

    public Orderline addOrderline(UnitGood unitGood, String qty)
            throws WrongCommandException {
        Orderline orderline = null;
        try {
            int quantity = Integer.parseInt(qty);
            if (hasGood(unitGood.getSku())) {
                orderline = getOrderline(unitGood.getSku());
                assert orderline != null;
                orderline.setQuantity(quantity);
                Display.orderlineAlreadyExists(orderline.getName(), orderline.getQuantity());
                return null;
            }
            orderline = new Orderline(unitGood,
                    orderlines.size() + 1, quantity);
            orderlines.add(orderline);
            Display.orderlineAdded(orderline.getName(), orderline.getQuantity());
        } catch (NumberFormatException e) {
            System.out.println("Quantity must be positive number");
            throw new WrongCommandException("add", true);
        }
        return orderline;
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

        if (orderline.getCheckedOff()) {
            System.out.println("Orderline has already been fulfilled, unable to edit orderline.");
            return;
        }

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
        for (Orderline ol : this.orderlines) {
            ja.add(ol.serialize());
        }
        return ja;
    }

    public JSONObject serialize() {
        JSONObject jo = new JSONObject();
        jo.put(OrderKeys.orderId, this.orderId);
        jo.put(OrderKeys.receiver, this.receiver);
        jo.put(OrderKeys.shippingAddress, this.shippingAddress);
        jo.put(OrderKeys.isFulfilled, this.isFulfilled);
        JSONArray jaol = this.serializeOrderlines();
        if (jaol == null) {
            return null;
        }
        jo.put(OrderKeys.orderlines, jaol);
        return jo;
    }


    public static Order restoreOrder(JSONObject jo) {
        Object idO = jo.get(OrderKeys.orderId);
        Object rO = jo.get(OrderKeys.receiver);
        Object saO = jo.get(OrderKeys.shippingAddress);
        Object fO = jo.get(OrderKeys.isFulfilled);
        Object olO = jo.get(OrderKeys.orderlines);
        if (idO == null || rO == null || saO == null || fO == null || olO == null){
            return null;
        }
        Integer orderId = Integer.parseInt(idO.toString());
        String receiver = rO.toString();
        String shippingAddress = saO.toString();
        Order cur = new Order(
                orderId,
                receiver,
                shippingAddress
        );

        cur.setFulfilled(Boolean.parseBoolean(fO.toString()));
        JSONArray orderLinesJA = (JSONArray) olO;
        for (Object item: orderLinesJA){
            JSONObject jol = (JSONObject) item;
            UnitGood ug = UnitGood.restoreUnitGood(jol);
            if (ug == null){
                return null;
            }
            Object qtyO = jol.get(GoodKeys.quantity);
            Object qfO = jol.get(OrderlinesKeys.quantityFulfilled);
            Object ifO = jol.get(OrderlinesKeys.isCheckedOff);
            if (qtyO == null || qfO == null || ifO == null){
                return null;
            }
            String qty = qtyO.toString();
            try {
                Orderline ol = cur.addOrderline(ug, qty);
                if (ol == null){
                    return null;
                }
                Boolean isCheckOff = Boolean.parseBoolean(ifO.toString());
                if (isCheckOff){
                    ol.checkOff();
                }
                Integer qtyF = Integer.parseInt(qfO.toString());
                ol.setQuantityFulfilled(qtyF);
            } catch (WrongCommandException e) {
                e.printStackTrace();
            }
        }

        return cur;
    }


}
