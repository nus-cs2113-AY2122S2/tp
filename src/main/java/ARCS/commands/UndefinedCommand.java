package ARCS.commands;

public class UndefinedCommand extends Command {
    private static final String UNDEFINED_MESSAGE = "Sorry! The system cannot read this command.";
    private String message;

    public UndefinedCommand() {
        this.message = UNDEFINED_MESSAGE;
    }

    public UndefinedCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(message);
    }
}
