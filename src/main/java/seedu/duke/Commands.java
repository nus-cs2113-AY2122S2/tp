package seedu.duke;

import java.util.ArrayList;

public class Commands {

    public static void addGood(Goods goods, ArrayList<Goods> userGoods) {
        userGoods.add(goods);
        System.out.printf("%d %s Has been added\n" + "You have %d goods in the warehouse\n",
                goods.getQuantity(), goods, userGoods.size());
    }
}
