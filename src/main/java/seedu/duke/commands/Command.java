package seedu.duke.commands;

import seedu.duke.ItemList;
import seedu.duke.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, ItemList itemList);
}
