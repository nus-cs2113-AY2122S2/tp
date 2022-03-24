package seedu.duke.commands;


import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Prints out the list of items in the itemList.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        ui.showMessages("Here are the items in your list:");
        for (int i = 0; i < itemList.getSize(); i++) {
            ui.showMessages(String.valueOf(i + 1) + "." + itemList.getItem(i));
        }
    }
}
