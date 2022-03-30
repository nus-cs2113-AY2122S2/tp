package seedu.duke.commands;

import seedu.duke.InvMgr;
import seedu.duke.common.Messages;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

import java.util.Objects;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_NAME = "Edit Item";
    public static final String USAGE_MESSAGE = "Edits the name and/or description of an item.";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "/n name [/d description] | [/n name] /d description]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    private final int index;
    private final String name;
    private final Integer quantity;
    private final String description;
    private final boolean relative;

    public EditCommand(int index, String name, Integer quantity, String description, boolean relative) {
        this.index = index;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.relative = relative;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        Item targetedItem;
        try {
            targetedItem = itemList.getItem(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvMgrException(Messages.INVALID_INDEX);
        }
        Item placeholderItem = new Item(targetedItem.getName(), targetedItem.getQuantity(), targetedItem.getDescription());
        if (this.name!=null) {
            placeholderItem.setName(this.name);
        }
        if (this.quantity!=null) {
            int currentQuantity = relative ? placeholderItem.getQuantity() : 0;
            int newQuantity = currentQuantity + this.quantity;
            try {
                placeholderItem.setQuantity(newQuantity);
            } catch (IllegalArgumentException e) {
                throw new InvMgrException(Messages.NEGATIVE_QUANTITY_MESSAGE, e);
            }
        }
        if (this.description!=null) {
            placeholderItem.setName(this.name);
        }
        itemList.set(index, placeholderItem);
    }
}
