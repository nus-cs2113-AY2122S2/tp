package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class HiCommand extends Command {
    public void execute(Ui ui, ItemList itemList) {
        ui.showOutput("hi!");
    }
}
