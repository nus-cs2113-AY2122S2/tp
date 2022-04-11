package seedu.simplst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import seedu.simplst.jsonkeyconstants.GoodKeys;
import seedu.simplst.jsonkeyconstants.WarehouseKeys;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.LargeQuantityException;
import util.exceptions.WrongCommandException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {
    private int totalCapacity = 0; //in terms of arbritrary_units

    private ArrayList<Order> orderLists = new ArrayList<>();
    private HashMap<String, UnitGood> unitGoodHashMap = new HashMap<>();

    private LinkedHashMap<String, Good> goodList = new LinkedHashMap<>();

    public Warehouse(Integer capacity) {
        this.totalCapacity = capacity;
    }

    /**
     * Adds a unit good object into the unitGoodHashMap as well as a goods object
     * into the goodList since the warehouse should now include this good but quantity
     * is set to 0 as at this point the good is not in the warehouse yet.
     *
     * @param sku         The Stock Keeping Unit of the unit good
     * @param name        The name of the unit good
     * @param description Short description of the unit good
     * @param capacity    Represents the size of the good using arbitrary units
     */
    public void addUnitGoodToInventory(String sku, String name, String description, String capacity) {
        UnitGood unitGood = new UnitGood(sku, name, description, capacity);
        Good newGood = new Good(unitGood, 0);
        if (unitGoodHashMap.containsKey(sku)) {
            Display.skuAlreadyExists(sku);
            return;
        }
        unitGoodHashMap.put(sku, unitGood);
        goodList.put(sku, newGood);
        Display.unitGoodAdded(sku);
    }

    public Boolean addUnitGoodToInventory(UnitGood unitGood) {
        Good newGood = new Good(unitGood, 0);
        String sku = unitGood.getSku();
        if (unitGoodHashMap.containsKey(sku)) {
            Display.skuAlreadyExists(sku);
            return false;
        }
        unitGoodHashMap.put(sku, unitGood);
        goodList.put(sku, newGood);
        Display.unitGoodAdded(sku);
        return true;
    }

    /**
     * Adds a quantity of a good that currently exists in the goodList.
     *
     * @param sku The Stock Keeping Unit of the good
     * @param qty The quantity to be added to the warehouse for this good
     * @throws WrongCommandException     Exception when the command is not properly used
     * @throws ItemDoesNotExistException Exception when the sku cannot be found in the unitGoodHashMap
     */
    public Boolean addQuantityOfGoodToInventory(String sku, String qty)
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
            goodList.get(sku).addQuantity(quantity);
            System.out.printf("%d of Good with SKU: %s added to warehouse\n",
                    quantity, sku);
        } catch (NumberFormatException e1) {
            // quantity was not a number
            throw new WrongCommandException("add", true);
        }
        return true;
    }


    // Meant for batch adding goods, could be used with UI if i create a param map.
    public void addGoodToInventory(int id, Object goodObject) {

    }

    public Boolean addOrderline(String oid, String sku, String qty) throws WrongCommandException {
        Boolean status = true;
        try {
            int id = Integer.parseInt(oid);
            Order order = findOrder(id);
            status = addGoodToOrder(order, sku, qty);
            if (!status) {
                return false;
            }
            System.out.printf("%s of %s is added to order number %d\n",
                    qty, sku, order.getId());
        } catch (NumberFormatException e) {
            throw new WrongCommandException("add", true);
        } catch (ItemDoesNotExistException e) {
            System.out.println("Order does not exist in the warehouse");
            System.out.println("Try adding an order first");
            throw new WrongCommandException("add", true);
        }
        return true;
    }

    private Boolean addGoodToOrder(Order order, String sku, String qty) throws WrongCommandException {
        if (!isSkuInInventory(sku)) {
            System.out.println("Good does not exist in the warehouse");
            System.out.println("Try adding a good first");
            return false;
        }

        Orderline ol =  order.addOrderline(getUnitGoodBySku(sku), qty);
        if (ol == null){
            return false;
        }
        return true;
    }

    public boolean hasUnitGood(String sku) {
        return unitGoodHashMap.containsKey(sku);
    }

    private UnitGood getUnitGoodBySku(String sku) {
        return unitGoodHashMap.get(sku);
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
    private int uniqueInventories() {
        ArrayList<Good> uniqueGoods = new ArrayList<>();

        goodList.forEach((sku, good) -> {
            if (!uniqueGoods.contains(good)) {
                uniqueGoods.add(good);
            }
        });

        return uniqueGoods.size();
    }

    public void viewGood(String sku) {
        if (hasUnitGood(sku)) {
            System.out.println(goodList.get(sku));
            System.out.println("Unit size of good: " + unitGoodHashMap.get(sku).getCapacity());
        } else {
            System.out.println("Could not find good with given SKU! Please check input SKU!");
        }
    }

    public void viewOrderById(String orderId) {
        try {
            Integer idToBeViewed = Integer.parseInt(orderId);
            for (Order order : orderLists) {
                if (idToBeViewed.equals(order.getId())) {
                    System.out.println("Viewing order with order ID " + order.getId());
                    System.out.println("Receiver: " + order.getReceiver());
                    System.out.println("Shipping address: " + order.getShippingAddress());
                    System.out.println("Items in the order:");
                    listOrderlines(String.valueOf(order.getId()));
                    return;
                }
            }
            System.out.println("Could not find order with given id!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid order id format entered! Check format and try again!");
        }
    }

    /**
     * Searches for goods based on the user input name and views each good
     * if the user input can be found in the name of the good.
     *
     * @param name Name to searched for in the goodList
     */
    public void findGoods(String name) {
        AtomicInteger counter = new AtomicInteger();
        for (Map.Entry<String, Good> goodEntry : goodList.entrySet()) {
            String sku = goodEntry.getKey();
            Good good = goodEntry.getValue();
            boolean isContainedInGoodName = good.getName().toLowerCase().contains(name.toLowerCase());
            if (isContainedInGoodName) {
                viewGood(sku);
                counter.getAndIncrement();
            }
        }

        if (counter.get() == 0) {
            System.out.println("Could not find any item containing '" + name + "' in the inventory!");
        }
    }

    // Prints all inventory in a numbered list in order of input
    public void listOrders() {
        if (orderLists.isEmpty()) {
            System.out.println("There are currently no orders.");
            return;
        }
        System.out.println("List of orders:");
        for (Order order : orderLists) {
            System.out.println("\t" + order);
        }
    }

    public void listOrderlines(String oid) {
        try {
            int id = Integer.parseInt(oid);
            Order order = findOrder(id);
            ArrayList<Orderline> orderLines = order.getOrderlines();
            for (Orderline orderline : orderLines) {
                System.out.println(orderline);
            }
        } catch (NumberFormatException e) {
            System.out.println("OID field must be an order id which is"
                    + " currently in the warehouse");
        } catch (ItemDoesNotExistException e) {
            System.out.printf("No order with oid: %s found in the warehouse\n", oid);
        }
    }

    /**
     * Prints to the user the current available goods in the warehouse.
     * Goods with 0 quantity are not shown as they are not available in the warehouse.
     */
    public void listGoods() {
        LinkedHashMap<String, Good> availableGoods = new LinkedHashMap<>();
        for (Map.Entry<String, Good> goodsEntry : goodList.entrySet()) {
            String key = goodsEntry.getKey();
            Good value = goodsEntry.getValue();
            boolean isAvailable = (value.getQuantity() > 0);
            if (isAvailable) {
                availableGoods.put(key, value);
            }
        }

        if (availableGoods.isEmpty()) {
            System.out.println("No available goods found in this warehouse!");
            return;
        }
        System.out.println("List of available goods with its quantity:");
        for (Map.Entry<String, Good> availableGoodsEntry : availableGoods.entrySet()) {
            Good good = availableGoodsEntry.getValue();
            System.out.println("\t" + good);
        }
    }

    /**
     * Prints to the user all the unit goods the warehouse can take.
     */
    public void listUnitGoods() {
        if (unitGoodHashMap.isEmpty()) {
            System.out.println("No unit goods have been added for this warehouse!");
            return;
        }
        System.out.println("List of unit goods (in no order):");
        for (Map.Entry<String, UnitGood> unitGoodEntry : unitGoodHashMap.entrySet()) {
            UnitGood unitGood = unitGoodEntry.getValue();
            System.out.println("\t" + unitGood);
        }
    }

    /**
     * FulFills order.
     *
     * @param oid order id to fulfill
     * @throws WrongCommandException when oid is not a positive integer
     */
    public void fulfillOrder(String oid) throws WrongCommandException {
        try {
            int orderID = Integer.parseInt(oid);
            Order order = findOrder(orderID);

            if (order.getFulfilled()) {
                System.out.printf("Order %d already completed\n", orderID);
                return;
            }

            ArrayList<Orderline> orderlines = order.getOrderlines();
            for (Orderline orderline : orderlines) {
                Good good = goodList.get(orderline.getSku());
                assert good != null;
                fulfillOrderline(orderline, good);
            }

            if (checkOrderComplete(order, orderlines)) {
                System.out.printf("Order %d completed\n", orderID);
            } else {
                System.out.printf("Order %d not completed\n", orderID);
            }
        } catch (NumberFormatException e) {
            System.out.println("order ID must be a positive number");
            throw new WrongCommandException("fulfill", true);
        } catch (ItemDoesNotExistException e1) {
            System.out.printf("No order with oid: %s found in the warehouse\n", oid);
        }
    }

    /**
     * Fulfill individual orderline in the order.
     * Checks off orderline if it is fulfilled
     *
     * @param orderline orderline to fulfill
     * @param good      good with matching sku as orderline in warehouse
     */
    private void fulfillOrderline(Orderline orderline, Good good) {
        int qtyToFulfill = orderline.getQuantity();
        int currentQtyInWarehouse = good.getQuantity();
        try {
            if (currentQtyInWarehouse < qtyToFulfill) {
                throw new LargeQuantityException();
            }

            orderline.setQuantityFulfilled(qtyToFulfill);
            good.removeQuantity(qtyToFulfill);
            assert currentQtyInWarehouse > good.getQuantity();

            if (good.getQuantity() == 0) {
                System.out.println("Orderline fulfilled");
                System.out.printf("No more %s in the warehouse\n", good.getName());
            } else {
                System.out.println("Orderline fulfilled");
                System.out.printf("%d %s left in the warehouse\n",
                        good.getQuantity(), good.getName());
            }
        } catch (LargeQuantityException e) {
            System.out.printf("Not enough %s in the warehouse\n", good.getName());
            System.out.println("Orderline not fulfilled");
        }
    }

    /**
     * Checks off the order if all orderlines are checked off.
     *
     * @param order      order to check
     * @param orderlines orderlines in the order to check
     * @return true if all orderlines are checked off, false if any orderline is not checked off
     */
    private boolean checkOrderComplete(Order order, ArrayList<Orderline> orderlines) {
        for (Orderline orderline : orderlines) {
            if (!orderline.getCheckedOff()) {
                return false;
            }
        }
        order.setFulfilled(true);
        return true;
    }

    /**
     * Gives the total number of orders.
     *
     * @return total number of orders
     */
    public int totalNumberOfOrder() {
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

    private Order findOrderContainsGood(String sku) throws ItemDoesNotExistException {
        for (Order order : orderLists) {
            if (order.hasGood(sku)) {
                return order;
            }
        }

        throw new ItemDoesNotExistException();
    }

    /**
     * Removes the entire good from both unitGoodHashMap and goodList.
     *
     * @param sku The Stock Keeping Unit of the good to be removed
     */
    public void removeUnitGood(String sku) {
        try {
            this.removeUnitGoodFromInventory(sku);
        } catch (ItemDoesNotExistException e2) {
            Display.goodDontExistException();
        }
    }

    public void removeUnitGoodFromInventory(String sku) throws ItemDoesNotExistException {
        if (!unitGoodHashMap.containsKey(sku)) {
            throw new ItemDoesNotExistException();
        }
        unitGoodHashMap.remove(sku);
        goodList.remove(sku);
        System.out.println("Unit Good with SKU: " + sku + " has been removed from warehouse");
    }

    /**
     * Removes a specific quantity of specified good in the goodList.
     *
     * @param sku The Stock Keeping Unit of the good to have some quantity removed
     */
    public void removeQuantityOfGoods(String sku, String qty) throws WrongCommandException {
        try {
            removeQuantityOfGoodFromInventory(sku, qty);
        } catch (NumberFormatException e1) {
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e2) {
            Display.goodDontExistException();
        } catch (LargeQuantityException e3) {
            System.out.println("Quantity to remove is larger than current quantity");
            throw new WrongCommandException("remove", true);
        }
    }

    public void removeQuantityOfGoodFromInventory(String sku, String qty) throws
            ItemDoesNotExistException, LargeQuantityException, NumberFormatException {
        if (!goodList.containsKey(sku)) {
            throw new ItemDoesNotExistException();
        }
        int qtyNum = Integer.parseInt(qty);
        goodList.get(sku).removeQuantity(qtyNum);
        System.out.println(qtyNum + " of Good with SKU: " + sku + " has been removed from warehouse");
    }

    /**
     * Removes an order in the warehouse.
     *
     * @param oid order id
     * @throws WrongCommandException remove command is wrong
     */
    public void removeOrder(String oid) throws WrongCommandException {
        if (oid.isBlank()) {
            throw new WrongCommandException("remove", true);
        }

        try {
            int orderId = Integer.parseInt(oid);
            orderLists.remove(findOrder(orderId));
            System.out.println("Order " + oid + " has been removed");
        } catch (ItemDoesNotExistException e1) {
            System.out.println("The order you are trying to remove are not on the current list. "
                    + "Please try another id.");
        } catch (NumberFormatException e2) {
            throw new WrongCommandException("remove", true);
        }
    }

    public void removeOrderline(String oid, String sku, String qty) throws WrongCommandException {
        try {
            int orderID = Integer.parseInt(oid);
            Order order = findOrder(orderID);
            if (order.getFulfilled()) {
                System.out.println("Order has already been fulfilled, unable to edit order.");
            }
            order.removeOrderlineByQty(sku, qty);

        } catch (NumberFormatException e) {
            System.out.println("order ID must be a positive number");
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e1) {
            System.out.printf("Order id: %s does not exist.\n", oid);
        }

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

    private boolean hasOrderId(int oid) {
        for (Order order : orderLists) {
            if (order.getId() == oid) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add the base details of an order.
     * This will add the order to orderLists in the warehouse
     *
     * @param oid  order id
     * @param recv receiver name
     * @param addr shipping address
     * @throws WrongCommandException when input for either field is wrong or empty
     */
    public void addOrder(String oid, String recv, String addr) throws WrongCommandException {
        if (oid.isBlank() || recv.isBlank() || addr.isBlank()) {
            throw new WrongCommandException("add", true);
        }

        try {
            int id = Integer.parseInt(oid);

            if (hasOrderId(id)) {
                Display.orderIdAlreadyExists(id);
                return;
            }

            Order order = new Order(id, recv, addr);
            orderLists.add(order);
            Display.orderAdded(id);
        } catch (NumberFormatException e) {
            System.out.println("oid must be a positive number");
            throw new WrongCommandException("add", true);
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
            Order order = new Order(id, receiver, shippingAddress);
            orderLists.add(order);
            Display.orderAdded(id);
        } catch (NumberFormatException e) {
            throw new WrongCommandException("add", true);
        }
    }

    private Boolean addOrder(Order order) {
        Integer id = order.getId();
        if (hasOrderId(id)) {
            Display.orderIdAlreadyExists(id);
            return false;
        }
        orderLists.add(order);
        return true;
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

    public boolean setTotalCapacity(String input) {
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
        } catch (NullPointerException e){
            System.out.println("Warehouse capacity can't be null");
        }
        return false;
    }

    public LinkedHashMap<String, Good> getGoodList() {
        return goodList;
    }

    public HashMap<String, UnitGood> getUnitGoodHashMap() {
        return unitGoodHashMap;
    }

    public ArrayList<Order> getOrderLists() {
        return orderLists;
    }


    // Related to saving state outside program
    public Boolean saveWarehouseState() throws IOException {
        String fp = LocalStorage.WAREHOUSE_PATH;
        // Create JSON Obj
        JSONObject state = this.serialize();
        // Save to file
        Boolean status = LocalStorage.writeSaveFile(LocalStorage.json2str(state), fp);
        if (!status) {
            Display.warehouseStateNotSaved();
            return false;
        }
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

    private Boolean restoreOrders(JSONArray ja) {
        Boolean status = true;
        for (Object o : ja) {
            JSONObject jo = (JSONObject) o;
            Order restoredOrder = Order.restoreOrder(jo);
            if (restoredOrder == null){
                return false;
            }
            status = this.addOrder(restoredOrder);
            if (!status) {
                return false;
            }
        }
        return true;
    }

    private JSONObject serializeGoods() {
        JSONObject jo = new JSONObject();
        goodList.forEach((sku, good) -> {
            try {
                JSONObject goodJ = good.serialize();
                jo.put(sku, goodJ);
            } catch (Exception e) {
                Display.serializeException("Warehouse Goodlist");
            }
        });
        return jo;
    }

    private Boolean restoreGoods(JSONObject jo) {
        Boolean status = true;
        try {
            for (Object ko : jo.keySet()) {
                String sku = ko.toString();
                JSONObject jg = (JSONObject) jo.get(ko);
                UnitGood ug = UnitGood.restoreUnitGood(jg);
                if (ug==null){
                    return false;
                }
                status = this.addUnitGoodToInventory(ug);

                if (!status) {
                    return false;
                }
                Object qtyO = (jg).get(GoodKeys.quantity);
                if (qtyO == null){
                    return false;
                }
                String qty = qtyO.toString();
                try {
                    status = this.addQuantityOfGoodToInventory(ug.getSku(), qty);
                    if (!status) {
                        return false;
                    }
                } catch (WrongCommandException e) {
                    e.printStackTrace();
                    return false;
                } catch (ItemDoesNotExistException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    private JSONObject serializeUnitGoods() {
        JSONObject jo = new JSONObject();
        //System.out.println("Unit Good Hash Map");
        unitGoodHashMap.forEach((sku, ug) -> {
            //System.out.println("SKU: " + sku);
            jo.put(sku, ug.serialize());
        });
        return jo;
    }

    private JSONObject serialize() {
        JSONObject warehouse = new JSONObject();
        warehouse.put(WarehouseKeys.totalCapacity, this.totalCapacity);
        JSONArray sol = this.serializeOrders();
        if (sol == null) {
            return null;
        }
        warehouse.put(WarehouseKeys.orderLists, sol);

        JSONObject sgl = this.serializeGoods();
        if (sgl == null) {
            return null;
        }
        warehouse.put(WarehouseKeys.goodList, sgl);

        JSONObject ugm = this.serializeUnitGoods();
        if (ugm == null) {
            return null;
        }
        warehouse.put(WarehouseKeys.unitGoodHashMap, ugm);

        return warehouse;
    }


    public Boolean restoreWarehouseState() {
        // READ JSON FILE
        String fp = LocalStorage.WAREHOUSE_PATH;
        String saveStr = LocalStorage.readSaveFile(fp);
        if (saveStr == null) {
            return false;
        }
      
        // PARSE
        try {
            JSONObject jsonWarehouse = (JSONObject) JSONValue.parseWithException(saveStr);
            boolean status = false;
            Object capO = jsonWarehouse.get(WarehouseKeys.totalCapacity);
            if (capO == null){
                return false;
            }
            status = this.setTotalCapacity(capO.toString());
            if (!status) {
                return false;
            }
            Object olO = jsonWarehouse.get(WarehouseKeys.orderLists);
            if (olO == null){
                return false;
            }
            JSONArray sol = (JSONArray) olO ;
            status = this.restoreOrders(sol);
            if (!status) {
                return false;
            }
            Object glO = jsonWarehouse.get(WarehouseKeys.goodList);
            if (glO == null){
                return false;
            }
            JSONObject sgl = (JSONObject) glO;
            if (sgl == null){
                return false;
            }
            status = this.restoreGoods(sgl);
            if (!status) {
                return false;
            }

        } catch (ParseException e) {
            Display.jsonParseException(fp);
            return false;
        } catch (NumberFormatException e) {
            Display.numberFormatException();
            Display.jsonParseException(fp);
            return false;
        }
        return true;
    }
}


