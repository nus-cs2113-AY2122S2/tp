package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.List;
import java.util.Optional;

public class ListCurrentBorrowingsCommand extends Command {
    public static final String COMMAND_WORD = "listcb";

    private final Optional<String> name;

    public ListCurrentBorrowingsCommand(Optional<String> name) {
        this.name = name;
    }

    public void execute(ItemList itemList, Ui ui) {
        if (name.isPresent()) {
            ui.showMessages("Here is a list of current borrowings for " + name.get() + ": ");
        } else {
            ui.showMessages("Here is a list of current borrowings: ");
        }

        for (int i = 0; i < itemList.getSize(); i++) {
            Item borrowedItem = itemList.getItem(i);
            // Return a list of records filtered for name (optional) and status
            List<String> filteredRecords = borrowedItem.filterRecords(name, BorrowStatus.PRESENT);

            for (String record : filteredRecords) {
                ui.showMessages(record);
            }
        }
    }
}
