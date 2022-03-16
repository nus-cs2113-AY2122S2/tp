package seedu.duke;

public class ViewItemListCommand extends Command {
    @Override
    public void execute(ItemList listOfItems, Ui ui) {
        ui.printItemList(listOfItems);
    }
}
