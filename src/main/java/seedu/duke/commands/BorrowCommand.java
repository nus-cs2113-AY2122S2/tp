package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
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
    private final BorrowStatus borrowStatus;

    public BorrowCommand(int itemIndex, String startDateStr, String endDateStr, String borrowerName) {
        this.itemIndex = itemIndex;
        this.startDate = LocalDate.parse(startDateStr);
        this.endDate = LocalDate.parse(endDateStr);
        this.borrowerName = borrowerName;

        // Determine the borrow status compared to today's date
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.endDate)) {
            this.borrowStatus = BorrowStatus.PAST;
        } else if (today.isBefore(this.startDate)) {
            this.borrowStatus = BorrowStatus.FUTURE;
        } else {
            this.borrowStatus = BorrowStatus.PRESENT;
        }
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        BorrowRecord newRecord = new BorrowRecord(startDate, endDate, borrowerName, borrowStatus);
        itemList.addBorrowRecord(itemIndex, newRecord);
        System.out.println("Successfully added borrow record!");
    }
}
