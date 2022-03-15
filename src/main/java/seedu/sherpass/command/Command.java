package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
