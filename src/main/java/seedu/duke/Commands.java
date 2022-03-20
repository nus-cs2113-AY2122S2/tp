package seedu.duke;

import util.exceptions.LargeQuantityException;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;

public class Commands {

    public static void addGood(String id, String name, String qty,
                               String desc, ArrayList<Good> userGoods) throws WrongCommandException {
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
            System.out.printf("%d %s %s added\n", good.getQuantity(), good,
                    checkPlural(good.getQuantity()));
        } catch (NumberFormatException e) {
            throw new WrongCommandException("add", true);
        }
    }

    // Function to print grammar for statements to print
    public static String checkPlural(int numberOfGoods) {
        if (numberOfGoods <= 1) {
            return "is ";
        } else {
            return "are ";
        }
    }

    private static void remove(int id, int qty, ArrayList<Good> userGoods)
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

    public static void removeGood(String id, String qty, ArrayList<Good> userGoods)
            throws WrongCommandException {
        if (id.isBlank() || qty.isBlank()) {
            throw new WrongCommandException("remove", true);
        }

        try {
            int goodsId = Integer.parseInt(id);
            int goodsQty = Integer.parseInt(qty);

            remove(goodsId, goodsQty, userGoods);

        } catch (NumberFormatException e1) {
            throw new WrongCommandException("remove", true);
        } catch (ItemDoesNotExistException e2) {
            System.out.println("The goods you are trying to remove are not on the current list. "
                    + "Please try another id or add the goods first.");
        } catch (LargeQuantityException e3) {
            System.out.println("The quantity input is larger than the current quantity of the goods. "
                    + "Please enter the command again with a smaller quantity.");
        }
    }

    public static void help() {
        System.out.println("* Add order `add -o id/ID r/RECEIVER a/SHIPPING_ADDRESS`\n"
                + "* Add good `add -g oid/ORDER_ID gid/GOOD_ID n/NAME q/QUANTITY d/DESCRIPTION`\n"
                + "* Remove order: `remove -o i/ID`\n"
                + "* Remove good: `remove -g i/ID q/QUANTITY`\n"
                + "* List all orders: `list -o`\n"
                + "* List all goods: `list -g`\n"
                + "* View order: `view -o id/ORDER_ID`\n"
                + "* View good: `view -g id/GOOD_ID`\n"
                + "* Total quantity of goods: `total`");
    }

    /*
    public static void viewGood(String id, ArrayList<Goods> userGoods) {
        try {
            Integer idToBeViewed = Integer.parseInt(id);
            for (Goods good : userGoods) {
                if (idToBeViewed.equals(good.getId())) {
                    System.out.println("Viewing item with id " + good.getId());
                    System.out.println("Item name: " + good.getName());
                    System.out.println("Item description: " + good.getDescription());
                    System.out.println("Item quantity: " + good.getQuantity());
                    return;
                }
            }
            System.out.println("Could not find item with given id!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid format entered! Check format and try again!");
        }
    }
    */

    /*
    // Prints all inventory in a numbered list in order of input
    public static void listGoods(ArrayList<Goods> userGoods) {
        //int numberOfUserGoods = userGoods.size();
        if (userGoods.isEmpty()) {
            System.out.println("There is no inventory in the warehouse.");
            return;
        }
        System.out.println("List of inventory items:");
        int counter = 0;
        for (Goods good : userGoods) {
            System.out.println((counter + 1) + ". " + good.getName());
            counter++;
        }
    }
    */

    /*
    public static void totalGoods(ArrayList<Goods> userGoods) throws NullException {
        if (userGoods == null) {
            throw new NullException("userGoods");
        }
        int total = 0;
        for (Goods good: userGoods) {
            total += good.getQuantity();
        }
        System.out.println(String.format("There are %d goods in total.",total));
    }
    */
}
