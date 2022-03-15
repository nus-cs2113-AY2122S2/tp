package seedu.duke.commands;

import seedu.duke.ItemList;
import seedu.duke.Ui;

public class ByeCommand extends Command {
    public void execute(Ui ui, ItemList itemList) {
        ui.showOutput("bye");
    }
}
