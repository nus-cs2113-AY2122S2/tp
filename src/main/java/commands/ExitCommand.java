package commands;

public class ExitCommand extends Command {
    public static final String BASE_KEYWORD = "exit";

    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        // Will do nothing, since it's an exit command
    }
}
