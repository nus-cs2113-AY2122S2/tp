package commands;

import werkit.UI;

public class HelpCommand extends Command {
    public static final String KEYWORD_BASE = "help";

    private final UI ui = new UI();

    public HelpCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String getUserAction() {
        return null;
    }

    @Override
    public void execute() {
        ui.printHelpMessage();
    }
}
