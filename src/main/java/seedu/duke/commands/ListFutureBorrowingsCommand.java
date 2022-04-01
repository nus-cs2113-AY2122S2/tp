package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Optional;

public class ListFutureBorrowingsCommand extends Command {
    public static final String COMMAND_WORD = "listfb";

    private final Optional<String> name;

    public ListFutureBorrowingsCommand(Optional<String> name) {
        this.name = name;
    }

    public void execute(ItemList itemList, Ui ui) {
        if (name.isPresent()) {
            ui.showMessages("Here is a list of future borrowings for " + name.get() + ": ");
            int borrowIndex = 0;
            for (int i = 0; i < itemList.getSize(); i++) {
                Item borrowedItem = itemList.getItem(i);
                ArrayList<BorrowRecord> borrowRecords = borrowedItem.getBorrowRecords();

                for (BorrowRecord record : borrowRecords) {
                    boolean isFutureBorrowing = record.getBorrowStatus() == BorrowStatus.FUTURE;
                    boolean matchesName = record.getBorrowerName().equals(name.get());
                    if (isFutureBorrowing && matchesName) {
                        borrowIndex++;
                        ui.showMessages(borrowIndex + ") Name of Item: " + borrowedItem.getName(),
                                "Name of Borrower: " + record.getBorrowerName(),
                                "Borrow Duration: " + record.getBorrowDuration() + "\n");
                    }
                }
            }
        } else {
            ui.showMessages("Here is a list of future borrowings: ");
            for (int i = 0; i < itemList.getSize(); i++) {
                Item borrowedItem = itemList.getItem(i);
                ArrayList<BorrowRecord> borrowRecords = borrowedItem.getBorrowRecords();

                for (BorrowRecord record : borrowRecords) {
                    if (record.getBorrowStatus() == BorrowStatus.FUTURE) {
                        ui.showMessages("Name of Item: " + borrowedItem.getName(),
                                "Name of Borrower: " + record.getBorrowerName(),
                                "Borrow Duration: " + record.getBorrowDuration() + "\n");
                    }
                }
            }
        }
    }


}