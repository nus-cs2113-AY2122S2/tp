package arcs.commands;


public class ExitCommand extends Command {
    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "Exiting the system ...";


    /**
     * Initializes command for execution.
     */
    public ExitCommand() {
        setIsExit();
    }

    /**
     * Says goodbye to user and exits MySTARS.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }
}
