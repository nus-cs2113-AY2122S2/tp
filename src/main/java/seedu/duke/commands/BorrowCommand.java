package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

/**
 * Borrow an item from the inventory for a specified duration
 */
public class BorrowCommand extends Command {
    private final int itemIndex;
    private final String sDate;
    private final String eDate;
    private final String borrowerName;

    public BorrowCommand(String itemIndex, String sDate, String eDate, String borrowerName) {
        this.itemIndex = itemIndex;
        this.sDate = sDate;
        this.eDate = eDate;
        this.borrowerName = borrowerName;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        itemList.addBorrowRecord(itemIndex, sDate, eDate, borrowerName);
        System.out.println("Successfully added borrow record!");
    }
}
