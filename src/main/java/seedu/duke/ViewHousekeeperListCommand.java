package seedu.duke;

public class ViewHousekeeperListCommand extends Command {

    /**
     * Printing the list of housekeeper name, age and availability.
     *
     * @param housekeeperList  The list of housekeeper recorded.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param listOfItems      The given list of Item objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param ui               The user interface for this execution method.
     */
    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList, ItemList listOfItems, Ui ui) throws HotelLiteManagerException, WrongCommandException {
        ui.printHousekeeperList(housekeeperList);
    }
}