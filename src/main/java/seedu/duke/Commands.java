package seedu.duke;

import util.exceptions.LargeQuantityException;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.NullException;

import java.util.ArrayList;

public class Commands {

    public static void addGood(String id, String name, String qty, ArrayList<Goods> userGoods) {
        if (id.isBlank() || name.isBlank() || qty.isBlank()) {
            System.out.println("Please add a good in this format:\n"
                    + "add id/id_of_good_as_number n/name_of_good q/quantity_of_good_as_number");
            return;
        }
        try {
            Goods goods = new Goods(
                    Integer.parseInt(id),
                    name,
                    Integer.parseInt(qty),
                    "Empty Description"); //description not yet added since not in UserGuide
            userGoods.add(goods);
            System.out.printf("%d %s Has been added\n", goods.getQuantity(), goods);
        } catch (NumberFormatException e) {
            // error handling here
            System.out.println("Please add a good in this format:\n"
                    + "add id/id_of_good_as_number n/name_of_good q/quantity_of_good_as_number");
        }
    }

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

    /*Prints all inventory in a numbered list in order of input*/
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

    /*Function to print grammar for statements to print*/
    public static String checkPlural(int numberOfGoods) {
        if (numberOfGoods <= 1) {
            return "is ";
        } else {
            return "are ";
        }
    }

    private static void remove(int id, int qty, ArrayList<Goods> userGoods)
            throws LargeQuantityException, ItemDoesNotExistException {

        for (Goods good : userGoods) {
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

    public static void removeGood(String id, String qty, ArrayList<Goods> userGoods) {
        if (id.isBlank() || qty.isBlank()) {
            System.out.println("Please remove goods in this format:\n"
                    + "remove id/id_of_good_as_number q/quantity_of_good_as_number");
            return;
        }

        try {
            int goodsId = Integer.parseInt(id);
            int goodsQty = Integer.parseInt(qty);

            remove(goodsId, goodsQty, userGoods);
          
        } catch (NumberFormatException e1) {
            System.out.println("Please remove goods in this format:\n"
                    + "remove id/id_of_good_as_number q/quantity_of_good_as_number");
        } catch (ItemDoesNotExistException e2) {
            System.out.println("The goods you are trying to remove are not on the current list. "
                    + "Please try another id or add the goods first.");
        } catch (LargeQuantityException e3) {
            System.out.println("The quantity input is larger than the current quantity of the goods. "
                    + "Please enter the command again with a smaller quantity.");
        }
    }

    public static void totalGoods(ArrayList<Goods> userGoods) throws NullException {
        if (userGoods == null) {
            throw new NullException("userGoods");

        }
        Integer total = 0;
        for (Goods good: userGoods) {
            total += good.getQuantity();
        }
        System.out.println(String.format("There are %d goods in total.",total));
    }

    public static void addOrder(String orderId, String orderDate, String postalCode, String receiverName, String receiverAddress) {
        if (orderId.isBlank() || postalCode.isBlank()
                || receiverName.isBlank() || receiverAddress.isBlank()) {
            System.out.println(1);
        }

        if (orderDate.isBlank()) {
            //hello
        }
    }
}
