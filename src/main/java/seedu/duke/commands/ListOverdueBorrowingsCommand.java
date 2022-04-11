package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListOverdueBorrowingsCommand extends Command {
    public static final String COMMAND_WORD = "listob";

    private final Optional<String> name;

    public ListOverdueBorrowingsCommand(Optional<String> name) {
        this.name = name;
    }

    public void execute(ItemList itemList, Ui ui) {
        List<Item> items = itemList.getItemArrayList();
        List<String> overdueRecords =
                items.stream()
                        // for every item, get the list of overdue borrowrecords in the form of strings
                        .map(item -> item.filterOverdueRecords(name))
                        // combine all the arraylists into 1 single stream of borrowrecords (regardless of item)
                        .flatMap(Collection::stream)
                        // convert this stream into a list (that is futureRecords)
                        .collect(Collectors.toList());

        if (overdueRecords.size() == 0) {
            if (name.isPresent()) {
                ui.showMessages("There are no overdue borrowings for " + name.get() + ".");
            } else {
                ui.showMessages("There are no overdue borrowings.");
            }
            return;
        }

        if (name.isPresent()) {
            ui.showMessages("Here is a list of overdue borrowings for " + name.get() + ": ");
        } else {
            ui.showMessages("Here is a list of overdue borrowings: ");
        }

        for (String record: overdueRecords) {
            ui.showMessages(record);
        }
    }

}
