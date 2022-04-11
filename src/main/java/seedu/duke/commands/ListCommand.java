package seedu.duke.commands;


import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Prints out the list of items in the itemList.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        if (itemList.getSize() == 0) {
            ui.showMessages("There are no items in the list.");
            return;
        }
        ui.showMessages("Here are the items in your list:");
        for (int i = 0; i < itemList.getSize(); i++) {
            ui.showMessages(String.valueOf(i + 1) + "." + itemList.getItem(i));
        }
    }
}
