package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class ByeCommand extends Command {
    public void execute(Ui ui, ItemList itemList) {
        System.out.println("bye");
    }
}
