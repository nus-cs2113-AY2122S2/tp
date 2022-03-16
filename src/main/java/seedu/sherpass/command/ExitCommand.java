package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = "Bye: Exits the program.";

    /**
     * Returns a boolean value denoting if the given Command object
     * is of the same instance as an ExitCommand.
     *
     * @param c Command object
     * @return Returns true if c is an instance of ExitCommand. False otherwise.
     */
    public static boolean isExit(Command c) {
        return c instanceof ExitCommand;
    }

    /**
     * Executes exit command. Prints a goodbye message.
     *
     * @param taskList Task array.
     * @param ui Ui for printing messages.
     * @param storage Storage for appending or overwriting saved data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }
}
