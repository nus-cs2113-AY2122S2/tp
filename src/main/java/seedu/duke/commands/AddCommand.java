package seedu.duke.commands;

import static java.util.Objects.requireNonNull;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

/**
 * Adds an item to the item list.
 */
public class AddCommand extends Command {
    private final Item itemToAdd;
    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_NAME = "Add Item";
    public static final String USAGE_MESSAGE = "Adds an item to the inventory list";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [item name] [item quantity]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    /**
     * Creates an AddCommand to add the specified {@code Item}.
     * @param itemToAdd The specified item.
     */
    public AddCommand(Item itemToAdd) {
        requireNonNull(itemToAdd, "item should not be null!");
        this.itemToAdd = itemToAdd;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        itemList.addItem(itemToAdd);
        ui.showMessages(itemToAdd + " has been added!");
    }
}
