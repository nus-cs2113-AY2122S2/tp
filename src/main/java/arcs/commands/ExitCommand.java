package arcs.commands;

import arcs.ui.MainUi;

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
    public CommandResult execute() {
        setIsExit();

        ui.printExitMessage();
        return null;
    }
}
