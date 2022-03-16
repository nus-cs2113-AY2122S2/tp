package seedu.duke;

public class ViewItemListCommand extends Command {
    @Override
    public void execute(SatisfactionList satisfactionList, RoomList roomList, ItemList listOfItems, Ui ui) {
        ui.printItemList(listOfItems);
    }
}
