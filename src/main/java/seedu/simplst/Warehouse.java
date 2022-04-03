package seedu.simplst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import seedu.simplst.jsonkeyconstants.WarehouseKeys;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.LargeQuantityException;
import util.exceptions.UnitTestException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {
    private int totalCapacity = 0; //in terms of arbritrary_units
    private static ArrayList<Order> orderLists = new ArrayList<>();
    private HashMap<String, UnitGood> unitGoodHashMap = new HashMap<>();
    private LinkedHashMap<String, Good> goodList = new LinkedHashMap<>();


    // In the case where they want to mass add a bunch of goods as part of setup
    public void addUnitGoodToInventory(String sku, String name, String description, String capacity)
            throws UnitTestException {
        UnitGood unitGood = new UnitGood(sku, name, description, capacity);
        unitGoodHashMap.put(sku, unitGood);
    }

    // In the case where they want add single/multiple of new/existing type of good
    public void addGoodToInventory(String sku, String qty)
            throws WrongCommandException, ItemDoesNotExistException {
        UnitGood unitGood = unitGoodHashMap.get(sku);

        if (unitGood == null) {
            if (sku.isBlank()) {
                // add command without typing sku
                throw new WrongCommandException("add", true);
            }
            // current unit good does not exist
            throw new ItemDoesNotExistException();
        }

        try {
            int quantity = Integer.parseInt(qty);
            Good good = new Good(unitGood, goodList.size() + 1, quantity);
            goodList.put(sku, good);
        } catch (NumberFormatException e1) {
            // quantity was not a number
            throw new WrongCommandException("add", true);
        }
    }

    // Meant for batch adding goods, could be used with UI if i create a param map.
    public void addGoodToInventory(int id, Object goodObject) {

    }

    public boolean doesUnitGoodExist(String sku) {
        return unitGoodHashMap.containsKey(sku);
    }

    public boolean isSkuInInventory(String sku) {
        return goodList.containsKey(sku);
    }

    private Good getInventoryGoodBySku(String sku) {
        return goodList.get(sku);
    }

    /**
     * Used to count the number of unique goods in the warehouse.
     *
     * @return number of unique goods
     */
    public int uniqueInventories() {
        ArrayList<Good> uniqueGoods = new ArrayList<>();

        goodList.forEach((sku, good) -> {
            if (!uniqueGoods.contains(good)) {
                uniqueGoods.add(good);
            }
        });

        return uniqueGoods.size();
    }

    public void viewUnitGood(String sku) {
        if (doesUnitGoodExist(sku)) {
            System.out.println(unitGoodHashMap.get(sku));
            System.out.println("Size of good: " + unitGoodHashMap.get(sku).getCapacity());
        } else {
            System.out.println("Could not find unit good with given SKU! Please check input SKU!");
        }
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

    public void viewGoodBySku(String sku) {
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
            if (isSkuInInventory(sku)) {
                Good curGood = getInventoryGoodBySku(sku);
                UnitGood curUG = unitGoodHashMap.get(sku);
                System.out.println("Viewing item with SKU " + sku);
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

    public void findGoods(String name) {
        AtomicInteger counter = new AtomicInteger();
        goodList.forEach((sku, good) -> {
            if (good.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Viewing item with SKU " + sku);
                System.out.println("Item name: " + good.getName());
                System.out.println("Item description: " + good.getDescription());
                System.out.println("Item quantity: " + good.getQuantity());
                System.out.println("");
                counter.getAndIncrement();
            }
        });

        if (counter.get() == 0) {
            System.out.println("Could not find any item containing " + name + " in the inventory!");
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
        for (Order order : orderLists) {
            for (Orderline orderline : order.getOrderlines()) {
                System.out.println("\t" + (counter + 1) + ". " + orderline);
                counter++;
            }
        }
    }

    public void listUnitGoods() {
        if (unitGoodHashMap.isEmpty()) {
            System.out.println("No unit goods have been added for this warehouse!");
            return;
        }
        System.out.println("List of unit goods (in no order):");
        unitGoodHashMap.forEach((SKU,unitGood) -> {
            System.out.println(unitGood);
        });
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

    public void removeGoodFromInventory(String unitGoodSKU) throws ItemDoesNotExistException {
        if (!unitGoodHashMap.containsKey(unitGoodSKU)) {
            throw new ItemDoesNotExistException();
        }
        unitGoodHashMap.remove(unitGoodSKU);
        goodList.remove(unitGoodSKU);
    }

    public void removeQtyGoodFromInventory(String sku, String qty) throws
            ItemDoesNotExistException, LargeQuantityException {
        if (!goodList.containsKey(sku)) {
            throw new ItemDoesNotExistException();
        }
        int qtyNum = Integer.parseInt(qty);
        goodList.get(sku).removeQuantity(qtyNum);
    }


    /**
     * Removes the entire good.
     *
     * @param sku sku of good to remove
     */
    public void removeUnitGood(String sku) {
        try {
            this.removeGoodFromInventory(sku);
        } catch (ItemDoesNotExistException e2) {
            Display.goodDontExistException();
        }
    }

    /**
     * Removes a specific quantity of specified good.
     *
     * @param sku sku of good to remove
     */
    public void removeGoods(String sku, String qty) throws WrongCommandException {
        try {
            removeQtyGoodFromInventory(sku, qty);
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e2) {
            Display.goodDontExistException();
        } catch (LargeQuantityException e3) {
            System.out.println("Quantity to remove is larger than current quantity");
            throw new WrongCommandException("remove", true);
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
    public Boolean saveWarehouseState() {
        String fp = LocalStorage.WAREHOUSE_PATH;
        // Create JSON Obj
        JSONObject state = this.serialize();
        // Save to file
        LocalStorage.writeSaveFile(LocalStorage.json2str(state), fp);
        Display.warehouseStateSaved(fp);
        return true;
    }

    private JSONArray serializeOrders() {
        JSONArray ja = new JSONArray();
        for (Order o : orderLists) {
            try {
                JSONObject jo = o.serialize();
                ja.add(jo);
            } catch (Exception e) {
                Display.serializeException("Warehouse Orderlist");
            }
        }
        return ja;
    }

    private JSONObject serialize() {
        JSONObject warehouse = new JSONObject();

        warehouse.put(WarehouseKeys.capacityOccupied, getCapacityOccupied());
        warehouse.put(WarehouseKeys.inventoryTypeCount, uniqueInventories());
        warehouse.put(WarehouseKeys.totalCapacity, this.totalCapacity);
        JSONArray sol = this.serializeOrders();
        if (sol == null) {
            return null;
        }
        warehouse.put(WarehouseKeys.orderLists, sol);

        return warehouse;
    }

    public void batchSetOrders(String filePath) throws WrongCommandException, InvalidFileException, InvalidObjectType {
        String saveStr = LocalStorage.readSaveFile(filePath);
        // READ JSON FILE
        JSONArray jsonOrders = (JSONArray) JSONValue.parse(saveStr);
        int idx = 0;
        for (Object orderObject : jsonOrders) {
            addOrder(idx, orderObject);
            idx += 1;
        }
    }

    /**
     * Adds all stuff in a csv or json (it's either one).
     *
     * @param filePath path to file
     * @throws InvalidFileException Invalid File
     */
    public void batchSetGoodsToInventory(String filePath) throws InvalidFileException {
        // READ JSON FILE

        JSONArray jsonGoods = new JSONArray();
        int idx = 0;
        for (Object goodObject : jsonGoods) {
            addGoodToInventory(idx, goodObject);
            idx += 1;
        }
    }

    // batch adding
    private void addOrder(int id, Object orderObject) throws
            WrongCommandException, InvalidFileException, InvalidObjectType {
        String receiver = null;
        String shippingAddress = null;
        String toFulfilBy = null;
        String comments = null;
        if (orderObject instanceof JSONObject) {
            JSONObject jorderObject = (JSONObject) orderObject;
        } else if (orderObject instanceof Map) {
            Map orderMap = (Map) orderObject;
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

    public Warehouse(Integer capacity) {
        this.totalCapacity = capacity;
    }

    /**
     * Getting the capacity left in the warehouse.
     *
     * @return capacity left
     */
    public int getCapacityLeft() {
        int totalGoods = getCapacityOccupied();

        return totalCapacity - totalGoods;
    }

    public void getPercentOccupied() {
        int totalGoods = getCapacityOccupied();
        Float percentageCapacity = (
                (float) totalGoods / (float) totalCapacity) * 100;
        Display.displayStorageCapacity(percentageCapacity);
    }

    /**
     * Getting the capacity occupied in the warehouse.
     *
     * @return capacity occupied
     */
    public int getCapacityOccupied() {
        AtomicInteger total = new AtomicInteger();
        goodList.forEach((sku, good) -> total.addAndGet(good.capacityTaken()));
        return total.get();
    }

    public boolean setCapacity(String input) {
        try {
            int capacity = Integer.parseInt(input);//Integer.parseInt(input);
            assert capacity > 0;

            if (capacity < getCapacityOccupied()) {
                throw new LargeQuantityException();
            }
            totalCapacity = capacity;
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

}


