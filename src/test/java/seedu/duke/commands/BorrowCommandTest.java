package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowCommandTest {
    @Test
    public void execute_borrowCommand() throws InvMgrException {
        Ui ui = new Ui();
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ItemList itemList = new ItemList(itemArrayList);
        Item item1 = new Item("Markers", 3, "Drawing");
        itemList.addItem(item1);

        Command c = new BorrowCommand(0, "21-03-2021", "23-03-2021", "John Smith");
        c.execute(itemList, ui);
    }
}
