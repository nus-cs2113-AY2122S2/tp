package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.Item;
import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.ItemList;

import java.util.logging.Logger;

public class DeleteItemCommand extends Command {
    private Item item;
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    public DeleteItemCommand(String userInput) throws HotelLiteManagerException {
        if (userInput.isEmpty()) {
            throw new EmptyItemNameException();
        }
        String itemName = userInput.trim();
        Item item = new Item(itemName.trim());
        setItem(item);
    }

    public Item getItem() {
        return item;
    }

    private void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        Item itemToDelete = getItem();
        ItemList listOfItems = listContainer.getItemList();
        listOfItems.deleteItemInList(itemToDelete);
        ui.printDeleteItemAcknowledgementMessage(itemToDelete, listOfItems);
    }
}
