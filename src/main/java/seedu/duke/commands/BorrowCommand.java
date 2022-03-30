package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;
import java.time.LocalDate;

/**
 * Borrow an item from the inventory for a specified duration.
 */
public class BorrowCommand extends Command {
    private final int itemIndex;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String borrowerName;

    public BorrowCommand(int itemIndex, String startDateStr, String endDateStr, String borrowerName) {
        this.itemIndex = itemIndex;
        this.startDate = LocalDate.parse(startDateStr);
        this.endDate = LocalDate.parse(endDateStr);
        this.borrowerName = borrowerName;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        BorrowRecord newRecord = new BorrowRecord(startDate, endDate, borrowerName);
        itemList.addBorrowRecord(itemIndex, newRecord);
        System.out.println("Successfully added borrow record!");
    }
}
