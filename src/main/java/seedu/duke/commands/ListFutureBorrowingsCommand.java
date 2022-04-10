package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.List;
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
        } else {
            ui.showMessages("Here is a list of future borrowings: ");
        }

        for (int i = 0; i < itemList.getSize(); i++) {
            Item borrowedItem = itemList.getItem(i);

            List<BorrowRecord> filteredRecords = borrowedItem.filterRecords(name, BorrowStatus.FUTURE);

            for (BorrowRecord record : filteredRecords) {
                ui.showMessages("Name of Item: " + borrowedItem.getName(),
                        record.toString());
            }
        }
    }

}