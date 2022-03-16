package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;
import seedu.duke.common.Messages;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_NAME = "Help";
    public static final String USAGE_MESSAGE = "Displays all functions of inventory manager";
    public static final String COMMAND_FORMAT = COMMAND_WORD;
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    @Override
    public void execute(ItemList itemList, Ui ui) {
        System.out.println(Messages.COMMAND_MENU_MESSAGE +
                Messages.DIVIDER +
                ExitCommand.HELP_MESSAGE + Messages.DIVIDER +
                HelpCommand.HELP_MESSAGE + Messages.DIVIDER);
    }
}
