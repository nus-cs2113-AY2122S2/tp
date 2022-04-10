package seedu.simplst;

public class Display {

    //    public static void addGood(String id, String name, String qty,
    //                               String desc, ArrayList<Orderline> userOrderlines) throws WrongCommandException {
    //        if (id.isBlank() || name.isBlank() || qty.isBlank()) {
    //            throw new WrongCommandException("add", true);
    //        }
    //        try {
    //            Orderline orderline = new Orderline(
    //                    Integer.parseInt(id),
    //                    name,
    //                    Integer.parseInt(qty),
    //                    desc);
    //            userOrderlines.add(orderline);
    //            System.out.printf("%d %s %s added\n", orderline.getQuantity(), orderline,
    //                    checkPlural(orderline.getQuantity()));
    //        } catch (NumberFormatException e) {
    //            throw new WrongCommandException("add", true);
    //        }
    //    }

    // Function to print grammar for statements to print
    //    public static String checkPlural(int numberOfGoods) {
    //        if (numberOfGoods <= 1) {
    //            return "is ";
    //        } else {
    //            return "are ";
    //        }
    //    }


    // successful output
    public static void inventoryGoodQtyRemoved(Float qty, String goodName) {
        System.out.println(qty + " of " + goodName + " has been removed.");
    }

    public static void orderlineCheckedOff(String unitGoodName, String orderId) {
        System.out.println(unitGoodName + "of order " + orderId + " has been removed.");
    }


    public static void displayStorageCapacity(Float percentageCapacity) {
        System.out.println("Storage capacity: " + percentageCapacity + '%');
    }

    public static void warehouseStateSaved(String filepath) {
        System.out.println("Warehouse information succesfully stored in " + filepath);
    }

    // error outputs
    public static void nonExistentGood() {
        System.out.println("The goods you are trying to remove are not on the current list. "
                + "Please try another id or add the goods first.");
    }

    public static void qtyRemoveMoreThanExists() {
        System.out.println("The quantity input is larger than the current quantity of the goods. "
                + "Please enter the command again with a smaller quantity.");
    }

    // exception outputs
    public static void goodDontExistException() {
        System.out.println("This type of Good you are trying to remove does not exist.\n"
                + "Please type a valid id.");
    }

    public static void serializeException(String obj) {
        System.out.println("The following " + obj + " has trouble serializing."); // perhaps reattempt reserializing?
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
        System.out.println("\tFormat: add og/ oid/[ORDER_ID] sku/[SKU] q/[QUANTITY_NEEDED_FOR_ORDER]");
        System.out.println("\tExample: add og/ oid/1 sku/WC1 q/10");
        System.out.println("Remove order Command");
        System.out.println("\tFormat: remove o/ oid/[ORDER_ID]");
        System.out.println("\tExample: remove o/ oid/1\n");
        System.out.println("Remove a specific quantity of orderline in an order");
        System.out.println("\tFormat: remove og/ oid/[ORDER_ID] sku/[SKU]"
                + " q/[QUANTITY_TO_REMOVE]");
        System.out.println("\tExample: remove og/ oid/1 sku/WC1 q/1\n");
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

    public static void tryCommandAgain() {
        System.out.println("Please enter the command again.");
    }


}
