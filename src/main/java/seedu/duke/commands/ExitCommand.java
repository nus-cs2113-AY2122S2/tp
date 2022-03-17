package seedu.duke.commands;

import seedu.duke.ui.MainUi;

public class ExitCommand extends Command {
    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "3";
    final MainUi ui;
    /**
     * Initializes command for execution.
     *
     * @param ui Ui object.
     */
    public ExitCommand(MainUi ui) {
        this.ui = ui;
    }

    /**
     * Says goodbye to user and exits MySTARS.
     */
    @Override
    public void execute() {
        setIsDoneUsingApp();

        ui.printExitMessage();
    }
}
