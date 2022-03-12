package seedu.duke;

import util.exceptions.LargeQuantityException;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.NegativeQuantityException;

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
                    Integer.parseInt(id),
                    "Empty Description"); //description not yet added since not in UserGuide
            userGoods.add(goods);
            System.out.printf("%d %s Has been added\n" + "You have %d goods in the warehouse\n",
                    goods.getQuantity(), goods, userGoods.size());
        } catch (NumberFormatException e) {
            // error handling here
            System.out.println("Please add a good in this format:\n"
                    + "add id/id_of_good_as_number n/name_of_good q/quantity_of_good_as_number");
        }
    }

    public static void listGoods(ArrayList<Goods> userGoods) {
        int numberOfUserGoods = userGoods.size();
        if (userGoods.isEmpty()) {
            System.out.println("There is no inventory in the warehouse.");
            return;
        }
        System.out.println("List of inventory items: ");
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
        boolean isGoodExist = false;

        for (Goods good : userGoods) {
            if (good.getId() == id) {
                isGoodExist = true;

                if (qty > good.getQuantity()) {
                    throw new LargeQuantityException();
                }

                good.setQuantity(good.getQuantity() - qty);

                if (qty < 2) {
                    System.out.println(qty + " " + good.getName() + " has been removed.");
                } else {
                    System.out.println(qty + " " + good.getName() + " have been removed.");
                }

                break;
            }
        }

        if (!isGoodExist) {
            throw new ItemDoesNotExistException();
        }

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

            System.out.println(1);
        } catch (NumberFormatException e1) {
            System.out.println("Please remove goods in this format:\n"
                    + "remove id/id_of_good_as_number q/quantity_of_good_as_number");
        } catch (ItemDoesNotExistException e2) {
            System.out.println("The goods you are trying to remove are ot on the current list. "
                    + "Please try another id or add the goods first.");
        } catch (LargeQuantityException e3) {
            System.out.println("The quantity input is larger than the current quantity of the goods. "
                    + "Please enter the command again with a smaller quantity.");
        }
    }
}
