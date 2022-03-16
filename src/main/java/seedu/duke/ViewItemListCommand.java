package seedu.duke;

/**
 * Represents a command to view all the items currently within the item list.
 */
public class ViewItemListCommand extends Command {
    @Override
    /**
     * Prints out the item name ,pax as well as index for each item that is found within the item list.
     *
     * @param housekeeperList  The list of housekeeper recorded. N/A for this class, but
     *                         must be included for the execution override.
     * @param satisfactionList The given list of Satisfaction objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param assignmentMap    The assignments of the various housekeepers.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param listOfItems      The list of items within the inventory.
     * @param ui               The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     */
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList, ItemList listOfItems, Ui ui) {
        ui.printItemList(listOfItems);
    }
}
