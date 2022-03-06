package seedu.sherpass.command;

import seedu.sherpass.Storage;
import seedu.sherpass.TaskList;
import seedu.sherpass.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
