package seedu.simplst;

public class Display {
    public static void hello() {
        String logo = "Simplst";
        System.out.println("Hello from\n" + logo);
        System.out.println("What would you like to do?");
    }

    public static void bye() {
        System.out.println("Thanks for using Simplst 2.1!");
    }

    public static void stateRestored() {
        System.out.println("State restored!");
    }

    public static void newLogin() {
        System.out.println("New login.");
    }

    // successful output

    public static void unitGoodAdded(String sku) {
        System.out.println("Unit Good of SKU: " + sku + " added to warehouse");
    }

    public static void orderAdded(int id) {
        System.out.println("Order " + id + " is added to the warehouse");
    }

    public static void orderlineAdded(String name, int qty) {
        System.out.printf("%s is added to order. %d required to fulfill\n",
                name, qty);
    }

    public static void displayStorageCapacity(Float percentageCapacity) {
        System.out.println("Storage capacity: " + percentageCapacity + '%');
    }

    public static void warehouseStateSaved(String filepath) {
        System.out.println("Warehouse information succesfully stored in " + filepath);
    }

    // error outputs

    public static void skuAlreadyExists(String sku) {
        System.out.println("Item with SKU: " + sku + " already exists in the warehouse. "
                + "Please check the SKU again.");
    }

    public static void orderIdAlreadyExists(Integer id) {
        System.out.printf("Order id %d already exists, please choose another id\n",
                id);
    }

    public static void orderlineAlreadyExists(String name, int qty) {
        System.out.printf("%s already exists in order. %d now required to fulfill\n",
                name, qty);
    }

    public static void nonExistentGood() {
        System.out.println("The goods you are trying to remove are not on the current list. "
                + "Please try another id or add the goods first.");
    }

    public static void qtyRemoveMoreThanExists() {
        System.out.println("The quantity input is larger than the current quantity of the goods. "
                + "Please enter the command again with a smaller quantity.");
    }

    public static void warehouseStateNotSaved() {
        System.out.println("Warehouse information can't save.");
    }

    public static void warehouseStateNotRestored() {
        System.out.println("Warehouse information can't restore.");
    }

    // exception outputs
    public static void goodDontExistException() {
        System.out.println("This type of Good you are trying to remove does not exist.\n"
                + "Please type a valid id.");
    }

    public static void serializeException(String obj) {
        System.out.println("The following " + obj + " has trouble serializing."); // perhaps reattempt reserializing?
    }

    public static void tryCommandAgain() {
        System.out.println("Please enter the command again.");
    }

    public static void inputAllFlags() {
        System.out.println("Missing flag in input. Please key in all the flags required in the command.");
    }

    public static void fillCompulsoryFields() {
        System.out.println("Non-optional fields are empty. Please fill in all compulsory fields.");
    }

    // Command related

    public static void helpUnitGood() {
        System.out.println("------------------------- Unit Good Commands -------------------------");
        System.out.println("Add new Unit Good Command");
        System.out.println("\tFormat: add ug/ sku/[SKU] n/[NAME] d/[DESCRIPTION]"
                + " size/[ESTIMATED_SIZE]");
        System.out.println("\tExample: add ug/ sku/WC1 n/Wooden Chair "
                + "d/Chair made of oak from Europe size/Medium\n");
        System.out.println("Remove Unit Good Command");
        System.out.println("\tFormat: remove ug/ sku/[SKU]");
        System.out.println("\tExample: remove ug/ sku/WC1\n");
        System.out.println("List Unit Goods Command");
        System.out.println("\tFormat: list ug/\n");
    }

    public static void helpGood() {
        System.out.println("------------------------- Goods Commands -------------------------");
        System.out.println("Add Quantity Command");
        System.out.println("\tFormat: add g/ sku/[SKU] qty/[QUANTITY]");
        System.out.println("\tExample: add g/ sku/WC1 qty/10\n");
        System.out.println("Remove Quantity Command");
        System.out.println("\tFormat: remove g/ sku/[SKU] qty/[QUANTITY]");
        System.out.println("\tExample: remove g/ sku/WC1 qty/10\n");
        System.out.println("List available Goods Command");
        System.out.println("\tFormat: list g/\n");
        System.out.println("View a Good Command");
        System.out.println("\tFormat: view g/ sku/[SKU]");
        System.out.println("\tExample: view g/ sku/WC1\n");
        System.out.println("Find Goods Command");
        System.out.println("\tFormat: find n/[NAME]");
        System.out.println("\tExample: find n/chair\n");
    }

    public static void helpOrder() {
        System.out.println("------------------------- Order Commands -------------------------");
        System.out.println("Add order detail Command");
        System.out.println("\tFormat: add o/ oid/[ORDER_ID] r/[RECEIVER_NAME] addr/[SHIPPING_ADDRESS]");
        System.out.println("\tExample: add o/ oid/1 r/Danny Phantom "
                + "addr/Amity Park\n");
        System.out.println("Add good to order Command");
        System.out.println("\tFormat: add og/ oid/[ORDER_ID] sku/[SKU] qty/[QUANTITY_NEEDED_FOR_ORDER]");
        System.out.println("\tExample: add og/ oid/1 sku/WC1 qty/10\n");
        System.out.println("Remove order Command");
        System.out.println("\tFormat: remove o/ oid/[ORDER_ID]");
        System.out.println("\tExample: remove o/ oid/1\n");
        System.out.println("Remove a specific quantity of orderline in an order");
        System.out.println("\tFormat: remove og/ oid/[ORDER_ID] sku/[SKU]"
                + " qty/[QUANTITY_TO_REMOVE]");
        System.out.println("\tExample: remove og/ oid/1 sku/WC1 qty/1\n");
        System.out.println("Listing all orders");
        System.out.println("\tFormat: list o/\n");
        System.out.println("Listing orderlines in an order");
        System.out.println("\tFormat: list og/ oid/[ORDER_ID]\n");
        System.out.println("\tExample: list og/ oid/1\n");
        System.out.println("View Command");
        System.out.println("\tFormat: view o/ oid/[ORDER_ID]");
        System.out.println("\tExample: view o/ oid/1\n");
        System.out.println("Fulfill Command");
        System.out.println("\tFormat: fulfill oid/[ORDER_ID]");
        System.out.println("\tExample: fulfill oid/1\n");
    }

    public static void help() {
        helpUnitGood();
        helpGood();
        helpOrder();
    }

    public static void jsonParseException(String fp) {
        System.out.println("File: " + fp + " is not a valid json file.");
    }

    public static void numberFormatException() {
        System.out.println("Number formatting invalid");
    }

    public static void numberFormatException(String val, String type) {
        System.out.println(val + " can't be intrepreted as type " + type);
    }

}
