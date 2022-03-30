package seedu.duke;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import seedu.duke.JsonKeyConstants.WarehouseKeys;
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

//    // Map of constants for JSON keys
//    private Map<String,String> JSONConstants = new HashMap<String,String>();
//    private void setupJSONConstants(){
//
//    }

    public Warehouse(Integer capacity) {
        this.totalCapacity = (float)capacity;
//        setupJSONConstants();
    }

    public Warehouse(Float capacity) {
        this.totalCapacity = capacity;
//        setupJSONConstants();
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

    public void getPercentOccupied(){
        Float totalGoods = totalInventoryVol();
        Float warehouseCapacity = getCapacityOccupied();
        Float percentageCapacity = (totalGoods / warehouseCapacity) * 100;
        Display.displayStorageCapacity(percentageCapacity);
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
        } catch (LargeQuantityException largeQuantityException) {
            System.out.println("Current total goods in the warehouse is more"
                    + " than input capacity");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Please set the Warehouse capacity again.");
        }
        return false;
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

    public Float totalInventoryVol() {   //should be float
        Float total = 0f;
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
           Float unitPrice,
           String unitItem,
           Boolean isWholeUnit,
            Float baseArea,
            Float volume,
            Boolean isPerishable){
        Good newGood = new Good();
        newGood.assignUnitGood(SKU,name, description, unitPrice, unitItem, isWholeUnit,
                baseArea, volume, isPerishable);
        inventory.put(Integer.toString(inventoryTypeCount),newGood);
        inventoryTypeCount += 1;
    }

    // Meant for batch adding goods, could be used with UI if i create a param map.
    public void addGoodToInventory(int id, Object goodObject){

    }
    // In the case where they want add single/multiple of new/existing type of good
    public void addGoodToInventory(
            String sku,
            String name,
            String desc,
            Float up,
            String ui,
            Boolean isWholeUnit,
            Float ba,
            Float v,
            Boolean ip,
            Float qty) throws WrongCommandException {
        if (sku.isBlank()) {
            throw new WrongCommandException("add", true);
        }
        try {
            if (inventory.containsKey(sku)){
                Good curGood = inventory.get(sku);
                Float curQty = curGood.getQuantity();
                curGood.setQuantity(curQty + qty); // might overflow
            } else {
                Good newGood = new Good();
                newGood.setQuantity(qty);
                newGood.assignUnitGood(
                        sku,
                        name,
                        desc,
                        up,
                        ui,
                        isWholeUnit,
                        ba,
                        v,
                        ip
                );
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
            Display.goodDontExistException();
        }
    }
    public void removeGoods(String unitGoodId, String qty) throws WrongCommandException {
        try {
            this.removeQtyGoodFromInventory(unitGoodId, qty);
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e2) {
            Display.goodDontExistException();
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


    // Related to saving state outside program

    public Boolean saveWarehouseState(){
        String fp = LocalStorage.WAREHOUSE_PATH;
        // Create JSON Obj
        JSONObject state = this.serialize();
        // Save to file
        LocalStorage.writeSaveFile(LocalStorage.json2str(state),fp);
        Display.warehouseStateSaved(fp);
        return true;
    }

    private JSONArray serializeOrders(){
        JSONArray ja = new JSONArray();
        for (Order o : orderLists){
            try {
                JSONObject jo = o.serialize();
                ja.add(jo);
            } catch (SerializeException e) {
                Display.serializeException("Warehouse Orderlist");
            }
        }
    }

    private JSONObject serialize(){
        JSONObject warehouse = new JSONObject();

        warehouse.put(WarehouseKeys.capacityOccupied, this.capacityOccupied);
        warehouse.put(WarehouseKeys.inventoryTypeCount, this.inventoryTypeCount);
        warehouse.put(WarehouseKeys.totalCapacity, this.totalCapacity);
        JSONArray s_ol = this.serializeOrders();
        if (s_ol == null){
            return null;
        }
        warehouse.put(WarehouseKeys.orderLists, s_ol);

        return warehouse;
    }

}


