package seedu.duke.commands;

import static java.util.Objects.requireNonNull;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(ItemList itemList, Ui ui) {
        ui.showMessages("Here are the tasks in your list:");
        for (int i = 0; i < itemList.getSize(); i++) {
            ui.showMessages(String.valueOf(i + 1) + "." + itemList.getItem(i));
        }
    }
}
