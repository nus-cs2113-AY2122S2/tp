package ARCS.commands;

public class UndefinedCommand extends Command {
    private static final String UNDEFINED_MESSAGE = "Sorry! The system cannot read this command.";

    @Override
    public CommandResult execute() {
        return new CommandResult(UNDEFINED_MESSAGE);
    }
}
