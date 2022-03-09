package seedu.sherpass.command;

import seedu.sherpass.utills.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.utills.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
