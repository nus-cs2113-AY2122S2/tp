package ARCS.commands;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "Exiting ARCS system ...";

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }
}
