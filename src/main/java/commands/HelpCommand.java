package commands;

import werkit.UI;

public class HelpCommand extends Command {
    public static final String BASE_KEYWORD = "help";

    private final UI ui = new UI();

    public HelpCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        ui.printHelpMessage();
    }
}
