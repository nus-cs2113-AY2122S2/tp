package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

public class DescCommand extends Command {
    public static final String COMMAND_WORD = "desc";
    public static final String COMMAND_NAME = "Describe Item";
    public static final String USAGE_MESSAGE = "Describes the function of an item";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [item number]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";
    private final int index;

    public DescCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        // Get item from itemList by searching for the item's name
        Item item = itemList.getItem(index);

        // Get description of item found
        String itemDescription = item.getDescription();

        // Print item name and description
        ui.showMessages("Name of Item: " + item.toString() + System.lineSeparator()
                + "Description: " + itemDescription);
    }
}
