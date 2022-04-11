package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;
import java.lang.Math;

import java.util.Optional;

/**
 * Edits an Item at a particular index. The name, quantity and description of the Item is only modified
 * if they are given new values. Otherwise, the old values will remain.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_NAME = "Edit Item";
    public static final String USAGE_MESSAGE = "Edits the name and/or description of an item.";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "INDEX [n/Name] [d/Description] "
            + "[q/Quantity [r/ +|-]]\n One of n/, d/, or q/ should be present!";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    private static final String EDIT_RESULT_FORMAT = "Item at index %d has been modified."
            + "\nBefore: %s"
            + "\nAfter: %s";

    private final int index;
    private final Optional<String> name;
    private final Optional<Integer> quantity;
    private final Optional<String> description;
    private final Optional<Boolean> relativeAdd;

    public EditCommand(int index,
            Optional<String> name, Optional<Integer> quantity,
            Optional<String> description, Optional<Boolean> relativeAdd) {
        this.index = index;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.relativeAdd = relativeAdd;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        Item targetedItem;
        try {
            targetedItem = itemList.getItem(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvMgrException(Messages.INVALID_INDEX);
        }
        Item placeholderItem = Item.copyItem(targetedItem);

        if (this.name.isPresent()) {
            placeholderItem.setName(this.name.get());
        }

        if (this.quantity.isPresent() && this.relativeAdd.isPresent()) {
            int currentQuantity = placeholderItem.getQuantity();
            int multiplier = this.relativeAdd.get() ? 1 : -1;
            try {
                int newQuantity = Math.addExact(currentQuantity, multiplier * this.quantity.get());
                placeholderItem.setQuantity(newQuantity);
            } catch (IllegalArgumentException e) {
                throw new InvMgrException(Messages.NEGATIVE_QUANTITY_MESSAGE, e);
            } catch (ArithmeticException e) {
                throw new InvMgrException(Messages.OVERFLOW_QUANTITY_MESSAGE, e);
            }
        } else if (this.quantity.isPresent() && !this.relativeAdd.isPresent()) {
            placeholderItem.setQuantity(quantity.get());
        }

        if (this.description.isPresent()) {
            placeholderItem.setDescription(this.description.get());
        }
        ui.showMessages(String.format(EDIT_RESULT_FORMAT, this.index + 1, targetedItem.toDetailedString(),
                placeholderItem.toDetailedString()));
        itemList.set(index, placeholderItem);

    }

    /**
     * Check if another object is equal to this EditCommand object.
     *
     * @param other the Object to compare against.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        EditCommand toCompare;
        if (other == this) {
            // return if same object
            return true;
        }
        if (other instanceof EditCommand) {
            // cast only if other is instance of EditCommand
            toCompare = (EditCommand) other;
            return (this.index == toCompare.index)
                    && (this.name.equals(toCompare.name))
                    && (this.quantity.equals(toCompare.quantity))
                    && (this.description.equals(toCompare.description))
                    && (this.relativeAdd.equals(toCompare.relativeAdd));
        } else {
            // null, or object not EditCommand
            return false;
        }
    }
}
