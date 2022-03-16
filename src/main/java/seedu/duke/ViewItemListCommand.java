package seedu.duke;

public class ViewItemListCommand extends Command {
    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) {
        ui.printItemList(listOfItems);
    }
}
