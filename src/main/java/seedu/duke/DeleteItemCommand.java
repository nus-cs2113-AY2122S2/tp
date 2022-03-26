package seedu.duke;

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
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, AssignmentMap assignmentMap,
                        RoomList roomList, ItemList listOfItems, Ui ui) throws HotelLiteManagerException,
            WrongCommandException {
        Item itemToDelete = getItem();
        listOfItems.deleteItemInList(itemToDelete);
        ui.printDeleteItemAcknowledgementMessage(itemToDelete, listOfItems);
    }
}
