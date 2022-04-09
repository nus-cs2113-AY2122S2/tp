package commands;

public class ExitCommand extends Command {
    public static final String KEYWORD_BASE = "exit";

    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        // Will do nothing, since it's an exit command
    }

    @Override
    public String getUserAction() {
        return null;
    }
}
