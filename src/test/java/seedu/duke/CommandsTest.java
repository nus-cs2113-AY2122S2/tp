package seedu.duke;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandsTest {
    public void addGoodTest() {
        //test 1, adding good normally - success
        ArrayList<Goods> goodsArrayList = new ArrayList<>();
        Commands.addGood("1", "chairs", "15", goodsArrayList);
        assertEquals(1, goodsArrayList.size());

        //test 2, blank inputs - fail, good is not added
        Commands.addGood("1", "     ", "15", goodsArrayList);
        assertEquals(0, goodsArrayList.size());

        //test 3, empty inputs - fail, good is not added
        Commands.addGood("1", "", "15", goodsArrayList);
        assertEquals(0, goodsArrayList.size());

        //test 4, non-numerical inputs for id - good is not added
        Commands.addGood("hi", "chairs", "15", goodsArrayList);
        assertEquals(0, goodsArrayList.size());

    }

}