package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListFutureBorrowingsCommand extends Command {
    public static final String COMMAND_WORD = "listfb";

    private final Optional<String> name;

    public ListFutureBorrowingsCommand(Optional<String> name) {
        this.name = name;
    }

    /**
     * This method first checks if there are any borrow records at all (for the person if it is specified in input).
     * If there are, the method will loop through the items to print out the items and their respective borrow records.
     * @param itemList ItemList containing all items in the current inventory
     * @param ui User Interface for reading inputs and/or printing outputs
     */
    public void execute(ItemList itemList, Ui ui) {
        List<Item> items = itemList.getItemArrayList();
        List<BorrowRecord> futureRecords =
                items.stream()
                        // for every item, get the arraylist of borrowrecords
                        .map(Item::getBorrowRecords)
                        // combine all the arraylists into 1 single stream of borrowrecords (regardless of item)
                        .flatMap(Collection::stream)
                        // filter records based on borrowstatus
                        .filter(record -> record.getBorrowStatus() == BorrowStatus.FUTURE)
                        // filter by borrower name if there is any during command input
                        // if there is no borrower specified in command input, compare the borrowername with itself
                        // (sure to return)
                        .filter(record -> record.getBorrowerName().equals(name.orElse(record.getBorrowerName())))
                        // convert this stream into a list (that is futureRecords)
                        .collect(Collectors.toList());

        if (futureRecords.size() == 0) {
            if (name.isPresent()) {
                ui.showMessages("There are no future borrowings for " + name.get() + ".");
            } else {
                ui.showMessages("There are no future borrowings.");
            }
            return;
        }

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