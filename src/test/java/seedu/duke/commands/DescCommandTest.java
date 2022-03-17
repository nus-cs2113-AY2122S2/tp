package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescCommandTest {
    @Test
    public void execute_descCommand() {
        // Instantiate an example of ItemList and Ui
        ArrayList<Item> items = new ArrayList<>();
        ItemList tempItemList = new ItemList(items);
        Item item1 = new Item("Markers", 3, "Drawing");
        tempItemList.addItem(item1);
        Ui tempUi = new Ui();

        // Create the DescCommand and test it
        Command newCommand = new DescCommand(1);
        newCommand.execute(tempItemList, tempUi);
    }
}
