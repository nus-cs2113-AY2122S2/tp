package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

public abstract class Command {

    /**
     * Executes the command. In this case, the execute method of the Command class
     * will not be executed as it is an abstract method, and will require other subclasses
     * to override this existing method for it to work.
     *
     * @param tasks Array representation of tasks. Not used.
     * @param ui User Interface. Not used.
     * @param storage Stores changes made in a save file. Not used.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
