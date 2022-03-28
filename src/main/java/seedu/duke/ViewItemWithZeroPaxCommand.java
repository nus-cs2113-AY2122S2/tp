package seedu.duke;

public class ViewItemWithZeroPaxCommand extends Command {
    public void execute(ListContainer listContainer, Ui ui) {
        ItemList listOfItems = listContainer.getItemList();
        ItemList listOfItemsWithZeroPax = listOfItems.findItemsWithZeroPaxInList();
        ui.printItemList(listOfItemsWithZeroPax);
    }
}
