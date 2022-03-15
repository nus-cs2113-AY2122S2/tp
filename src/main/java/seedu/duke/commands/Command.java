package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, ItemList itemList);
}
