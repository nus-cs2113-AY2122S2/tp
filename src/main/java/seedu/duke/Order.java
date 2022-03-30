package seedu.duke;

import util.exceptions.ItemDoesNotExistException;
import util.exceptions.LargeQuantityException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private String receiver;
    private String shippingAddress;
    private ArrayList<Orderline> orderlines = new ArrayList<>();
    private Float totalCost = 0F;
    private String toFulfilBy;
    private String fulfilledBy;
    private Boolean isFulfilled = false;
    private String comments;

    public Order(int orderId, String receiver, String shippingAddress, String toFulfilBy,  String comments) {
        this.orderId = orderId;
        this.receiver = receiver;
        this.shippingAddress = shippingAddress;
        this.toFulfilBy = toFulfilBy;
        this.comments = comments;
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

    public Orderline getOrderline(int orderlineId){
//        Orderline toRet = null;
        for (Orderline ol : orderlines){
            if (orderlineId == ol.getId()){
                return ol;
            };
        }
        return null;
    }

    public void addOrderline(){

    }

//    public void addOrderline(String idStr, String name, String qtyStr,
//                             String desc) throws WrongCommandException {
//        if (idStr.isBlank() || name.isBlank() || qtyStr.isBlank()) {
//            throw new WrongCommandException("add", true);
//        }
//        try {
//            int id = Integer.parseInt(idStr);
//            int qty = Integer.parseInt(qtyStr);
//
//            if (doesGoodExist(id)) {
//                addExistingGood(id, name, qty);
//                return;
//            }
//
//            Orderline orderline = new Orderline(id, name, qty, desc);
//            orderlines.add(orderline);
//            System.out.printf("%d %s %s added\n", orderline.getQuantity(), orderline.getName(),
//                    checkPlural(orderline.getQuantity()));
//        } catch (NumberFormatException e) {
//            throw new WrongCommandException("add", true);
//        } catch (ItemDoesNotExistException itemDoesNotExistException) {
//            System.out.println("ID has been used but with a different name");
//            throw new WrongCommandException("add", true);
//        }
//    }

    public void removeOrderlineByQty(int id, String qty)
            throws WrongCommandException {
        if (qty.isBlank()) {
            throw new WrongCommandException("remove", true);
        }

        try {
            int goodsId = id;//Integer.parseInt(id);
            int goodsQty = Integer.parseInt(qty);

            removeOrderlineByQtyHelper(goodsId, goodsQty);

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

    /**
     * FOR DEV ONLY
     * @param orderlineId
     */
    public void removeOrderline(int orderlineId){
        for (int idx = 0; idx < orderlines.size(); idx++){
            if (orderlineId == orderlines.get(idx).getId()){
                orderlines.remove(idx);
                return;
            }
        }
    }

    public void checkOffOrderline(int orderlineId){
        Orderline curOrderline = getOrderline(orderlineId);
        if (curOrderline == null) return;
        curOrderline.checkOff();
    }



    // Function to print grammar for statements to print
    private String checkPlural(int numberOfGoods) {
        if (numberOfGoods <= 1) {
            return "is ";
        } else {
            return "are ";
        }
    }



    private void removeOrderlineByQtyHelper(int id, int qty)
            throws LargeQuantityException, ItemDoesNotExistException {

        for (Orderline orderline : orderlines) {
            if (orderline.getId() == id) {
                if (qty > orderline.getQuantity()) {
                    throw new LargeQuantityException();
                }

                orderline.setQuantity(orderline.getQuantity() - qty);
                UnitGood orderlineUnit = orderline.getUnitGood();
                if (qty < 2) {
                    System.out.println(qty + " " + orderlineUnit.getName() + " has been removed.");
                } else {
                    System.out.println(qty + " " + orderlineUnit.getName() + " have been removed.");
                }

                if (orderline.getQuantity() == 0) {
                    orderlines.remove(orderline);
                }

                return;
            }
        }

        throw new ItemDoesNotExistException();
    }


    public boolean doesGoodExist(int goodId) {
        for (Orderline orderline : orderlines) {
            if (orderline.getId() == goodId) {
                return true;
            }
        }

        return false;
    }

    private Orderline findGood(int goodId) {
        for (Orderline orderline : orderlines) {
            if (orderline.getId() == goodId) {
                return orderline;
            }
        }

        return null;
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


    public String getToFulfilBy(){
        return this.toFulfilBy;
    }


    public String getFulfilledBy(){
        return this.fulfilledBy;
    }

    public void setFulfilledBy(String fulfilledBy){
        this.fulfilledBy = fulfilledBy;
    }

    public String getComments(){
        return this.comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public void addToComments(String comments){
        this.comments += '\n';
        this.comments += comments;
    }

    public Float getTotalCost(){
        return this.totalCost;
    }

    public String toString() {
        return String.format("%d - %s (%s)", orderId, receiver, shippingAddress);
    }
}
