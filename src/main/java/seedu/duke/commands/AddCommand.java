package seedu.duke.commands;

import static java.util.Objects.requireNonNull;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.List;

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

    /**
     * This method adds an item to the itemList only if there are no similar items existing in the inventory.
     * If there is a similar item, output will tell user to edit the existing item instead.
     * @param itemList ItemList containing all items in the current inventory
     * @param ui User Interface for reading inputs and/or printing outputs
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        // Check if the item is ALREADY in the item list
        List<Item> items = itemList.getItemArrayList();

        boolean isNotInList = checkItemInList(itemToAdd.getName(), items);

        // Add the item if there is no such item in the list, else do not add
        if(isNotInList){
            itemList.addItem(itemToAdd);
            ui.showMessages(itemToAdd + " has been added!");
        } else {
            ui.showMessages("There is already a similar item in the list!",
                    "Use edit command to edit the item's quantity/description instead.",
                    "Or change the name of the item to be more specific.");
        }
    }

    /**
     * Checks if there is another item in the list with the same name as the item to be added.
     * @param name name of the item to be added.
     * @param items list of items in the itemList.
     * @return boolean value of whether there is a similar item, or not.
     */
    private boolean checkItemInList(String name, List<Item> items) {
        Item matchedItem =
                items.stream()
                     // Filter to items where there is a match (case insensitive)
                     .filter(item -> item.getName().equalsIgnoreCase(name))
                     .findAny()
                     .orElse(null);

        if(matchedItem != null) {
            return false;
        }

        return true;
    }

    /**
     * This method is used for testing in matching AddCommand's created.
     * @param other The object to be compared, typically a command object.
     * @return boolean value of whether the commands are both the same AddCommand or not.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && itemToAdd.equals(((AddCommand) other).itemToAdd));
    }
}
