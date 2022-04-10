package seedu.duke.commands;

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
        List<String> futureRecords =
                items.stream()
                     // for every item, get the list of borrowrecords in the form of strings
                     .map(item -> item.filterRecords(name, BorrowStatus.FUTURE))
                     // combine all the arraylists into 1 single stream of borrowrecords (regardless of item)
                     .flatMap(Collection::stream)
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

        for (String record: futureRecords) {
            ui.showMessages(record);
        }
    }

}