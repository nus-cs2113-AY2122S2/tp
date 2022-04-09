package commands;

/**
 * This class is instantiated when the user types 'exit', though no actions will be taken by this class
 * as the exiting of the program is done by the WerkIt class.
 */
public class ExitCommand extends Command {
    public static final String KEYWORD_BASE = "exit";

    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        // No action taken by this class
    }

    @Override
    public String getUserAction() {
        return null;
    }
}
