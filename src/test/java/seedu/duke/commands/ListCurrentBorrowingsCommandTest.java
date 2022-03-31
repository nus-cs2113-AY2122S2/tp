package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ListCurrentBorrowingsCommandTest {

    @Test
    public void execute_listCurrentBorrowingsCommand() {
        Ui ui = new Ui();
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ItemList itemList = new ItemList(itemArrayList);
        Item item1 = new Item("Markers", 3, "Drawing");
        itemList.addItem(item1);
        
    }
}
