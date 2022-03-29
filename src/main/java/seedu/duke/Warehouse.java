package seedu.duke;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import util.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Float capacityOccupied = 0F;
    private Float totalCapacity; // volume
    private static ArrayList<Order> orderLists = new ArrayList<>();
    private Map<String, Good> inventory = new HashMap<String, Good>();
    private int inventoryTypeCount = 0;


    public Warehouse(Integer capacity) {
        this.totalCapacity = (float)capacity;
    }

    public Warehouse(Float capacity) {
        this.totalCapacity = capacity;
    }

    public boolean isSKUInInventory(String SKU){
        return inventory.containsKey(SKU);
    }

    private Good getInventoryGoodBySKU(String SKU){
        return inventory.get(SKU);
    }

    public Float getCapacityOccupied() {
        return capacityOccupied;
    }

    public void viewOrderById(String orderId) {
        try {
            Integer idToBeViewed = Integer.parseInt(orderId);
            for (Order order : orderLists) {
                if (idToBeViewed.equals(order.getId())) {
                    System.out.println("Viewing order with id " + order.getId());
                    System.out.println("Receiver: " + order.getReceiver());
                    System.out.println("Shipping address:" + order.getShippingAddress());
                    System.out.println("Items in the order:");

                    ArrayList<Orderline> userOrderlines = order.getOrderlines();
                    int i = 1;
                    for (Orderline orderline : userOrderlines) {
                        System.out.println("\t" + i + ". " + orderline);
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

    public void viewGoodBySKU(String SKU) {
        try {
//            Integer idToBeViewed = Integer.parseInt(SKU);
//            for (Order order : orderLists) {
//                for (Orderline orderline : order.getOrderlines()) {
//                    if (idToBeViewed.equals(orderline.getId())) {
//                        System.out.println("Viewing item with id " + orderline.getId());
//                        System.out.println("Item name: " + orderline.getName());
//                        System.out.println("Item description: " + orderline.getDescription());
//                        System.out.println("Item quantity: " + orderline.getQuantity());
//                        return;
//                    }
//                }
//            }
            if (isSKUInInventory(SKU)){
                Good curGood = getInventoryGoodBySKU(SKU);
                UnitGood curUG = curGood.getUnitGood();
                System.out.println("Viewing item with SKU " + SKU);
                System.out.println("Item name: " + curUG.getName());
                System.out.println("Item description: " + curUG.getDescription());
                System.out.println("Item quantity: " + curGood.getQuantity());
            } else {
                System.out.println("Could not find item with given id!");
            }

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
            for (Orderline orderline : order.getOrderlines()) {
                System.out.println("\t" + (counter + 1) + ". " + orderline);
                counter++;
            }
        }
    }

    public int totalInventoryVol() throws NullException {   //should be float
        if (orderLists == null) {
            throw new NullException("userGoods");
        }
        int total = 0;
        for (Order order: orderLists) {
            for (Orderline orderline : order.getOrderlines()) {
                total += orderline.getQuantity();
            }
        }
        return total;
    }

    public Float inventoryVolLeft(){
        return 0f;
    }

    public Float percentageInvVolLeft(){
        return 0f;
    }

    public int totalOrder() {
        return orderLists.size();
    }

    public boolean setCapacity(String input) {
        try {
            Float capacity = Float.parseFloat(input);//Integer.parseInt(input);
            assert capacity > 0;

            if (capacity < totalInventoryVol()) {
                throw new LargeQuantityException();
            }
            this.capacityOccupied = capacity;
            System.out.printf("Current Warehouse capacity is %d\n", capacity);
            return true;
        } catch (NullException nullException) {
            orderLists = new ArrayList<>();
            return setCapacity(input);
        } catch (LargeQuantityException largeQuantityException) {
            System.out.println("Current total goods in the warehouse is more"
                    + " than input capacity");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Please set the Warehouse capacity again.");
        }
        return false;
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

    // In the case where they want to mass add a bunch of goods as part of setup
    public void addUnitGoodToInventory(
            String SKU,
            String name,
                                       String description,
                                       String unitPrice,
                                       String unitItem,
                                       String isWholeUnit,
                                       String baseArea,
                                       String volume,
                                       String isPerishable){
        Good newGood = new Good();
        newGood.assignUnitGood(SKU,name, description, Float.parseFloat(unitPrice), unitItem, Boolean.parseBoolean(isWholeUnit),
                Float.parseFloat(baseArea), Float.parseFloat(volume), Boolean.parseBoolean(isPerishable));
        inventory.put(Integer.toString(inventoryTypeCount),newGood);
        inventoryTypeCount += 1;
    }

    // Meant for batch adding goods, could be used with UI if i create a param map.
    public void addGoodToInventory(int id, Object goodObject){

    }
    // In the case where they want add single/multiple of new/existing type of good
    public void addGoodToInventory(String sku, String name, String desc, String up, String ui, String isWholeUnit, String ba, String v, String ip, String qty) throws WrongCommandException {
        if (sku.isBlank()) {
            throw new WrongCommandException("add", true);
        }
        try {
            if (inventory.containsKey(sku)){
                Good curGood = inventory.get(sku);
                Float curQty = curGood.getQuantity();
                curGood.setQuantity(curQty + Float.parseFloat(qty)); // might overflow
            } else {
                Good newGood = new Good();
                newGood.setQuantity(Float.parseFloat(qty));
                newGood.assignUnitGood(
                        sku,
                        name,
                        desc, Float.parseFloat(up),
                        ui,
                        Boolean.parseBoolean(isWholeUnit),
                        Float.parseFloat(ba),
                        Float.parseFloat(v),
                        Boolean.parseBoolean(ip));
                inventory.put(sku, newGood);
            }
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("add", true);
        }
    }

    /**
     * Adds all stuff in a csv or json (it's either one)
     * @param filePath
     * @throws InvalidFileException
     */
    public void batchSetGoodsToInventory(String filePath) throws InvalidFileException {
        // READ JSON FILE

        JSONArray json_goods = new JSONArray();
        int idx = 0;
        for (Object goodObject : json_goods){
            addGoodToInventory(idx, goodObject);
            idx += 1;
        }
    }



    public void removeGoodFromInventory(String unitGoodId) throws ItemDoesNotExistException {
        if (!inventory.containsKey(unitGoodId)) throw new ItemDoesNotExistException();
        inventory.remove(unitGoodId);
    }

    public void removeQtyGoodFromInventory(String unitGoodId, String qty) throws ItemDoesNotExistException {
        if (!inventory.containsKey(unitGoodId)) throw new ItemDoesNotExistException();
        Float qty_num = Float.parseFloat(qty);
        inventory.get(unitGoodId).removeQuantity(qty_num);
    }

    public void removeGoods(String unitGoodId){
        try {
            this.removeGoodFromInventory(unitGoodId);
        } catch (ItemDoesNotExistException e2) {
            System.out.println("This type of Good you are trying to remove does not exist.\n"
                    + "Please type a valid id.");
        }
    }
    public void removeGoods(String unitGoodId, String qty) throws WrongCommandException {
        try {
            this.removeQtyGoodFromInventory(unitGoodId, qty);
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e2) {
            System.out.println("This type of Good you are trying to remove does not exist.\n"
                + "Please type a valid id."
            );
        }
    }

    private void addOrder(int id, Object orderObject) throws WrongCommandException, InvalidFileException, InvalidObjectType {
        String receiver = null;
        String shippingAddress = null;
        String toFulfilBy = null;
        String comments = null;
        if ( orderObject instanceof JSONObject) {
            JSONObject jOrderObject = (JSONObject) orderObject;
        } else if (orderObject instanceof Map) {
            Map orderMap = (Map)orderObject;
        } else {
            throw new InvalidObjectType("Order should either be JSONObject or Map type");
        }

        try {
            Order order = new Order(id, receiver, shippingAddress, toFulfilBy, comments);
            orderLists.add(order);
            System.out.println("Order " + id + " is added");
        } catch (NumberFormatException e) {
            throw new WrongCommandException("add", true);
        }
    }

    public void batchSetOrders(String filePath) throws WrongCommandException, InvalidFileException, InvalidObjectType {
        String saveStr = LocalStorage.readSaveFile(filePath);
        // READ JSON FILE
        JSONArray json_orders = (JSONArray) JSONValue.parse(saveStr);
        int idx = 0;
        for (Object orderObject : json_orders){
            addOrder(idx, orderObject);
            idx += 1;
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

//    public void addOrderLine(){
//
//    }

    private JSONObject serialize(){
        JSONObject warehouse = new JSONObject();


        return warehouse;
    }

}


