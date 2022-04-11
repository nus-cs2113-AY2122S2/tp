package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_NAME = "View Inventory";
    public static final String USAGE_MESSAGE = "List all items in the inventory";
    public static final String COMMAND_FORMAT = COMMAND_WORD;
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE
            + "\n" + "[Command Format] " + COMMAND_FORMAT + "\n";

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
