package seedu.duke.commands;

import seedu.duke.ItemList;
import seedu.duke.Ui;

public class HiCommand extends Command {
    public void execute(Ui ui, ItemList itemList) {
        ui.showOutput("hi!");
    }
}
