package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_validItemList_addSuccessful() {
        Ui tempUi = new Ui();
        ArrayList<Item> tempItemList = new ArrayList<>();
        Item item1 = new Item("Markers", 3, "Drawing");
        Item item2 = new Item("Whiteboard", 1, "To draw on");
        Item item3 = new Item("HDMI Cable", 2, "For connecting displays");
        tempItemList.add(item1);
        tempItemList.add(item2);
        tempItemList.add(item3);

        ItemList expectedItemList = new ItemList(tempItemList);
        int initSize = expectedItemList.getSize();

        Item validItem = new Item("Paper Towels", 25, "For cleaning");
        AddCommand testComd = new AddCommand(validItem);
        testComd.execute(expectedItemList, tempUi);

        assertEquals(initSize+1, expectedItemList.getSize());
    }

}
