package seedu.duke.commands;

import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;
import seedu.duke.common.Messages;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_NAME = "Help";
    public static final String USAGE_MESSAGE = "Displays all functions of inventory manager";
    public static final String COMMAND_FORMAT = COMMAND_WORD;
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n"
            + "[Function] "
            + USAGE_MESSAGE
            + ":\n"
            + "[Command Format] "
            + COMMAND_FORMAT;

    @Override
    public void execute(ItemList itemList, Ui ui) {
        ui.showMessages(Messages.COMMAND_MENU_MESSAGE + Messages.DIVIDER,
                AddCommand.HELP_MESSAGE + Messages.DIVIDER,
                DeleteCommand.HELP_MESSAGE + Messages.DIVIDER,
                EditCommand.HELP_MESSAGE + Messages.DIVIDER,
                DescCommand.HELP_MESSAGE + Messages.DIVIDER,
                LostCommand.HELP_MESSAGE + Messages.DIVIDER,
                BorrowCommand.HELP_MESSAGE + Messages.DIVIDER,
                ReturnCommand.HELP_MESSAGE + Messages.DIVIDER,
                SearchCommand.HELP_MESSAGE + Messages.DIVIDER,
                ListCommand.HELP_MESSAGE + Messages.DIVIDER,
                ListAvailableBorrowingsCommand.HELP_MESSAGE + Messages.DIVIDER,
                ListCurrentBorrowingsCommand.HELP_MESSAGE + Messages.DIVIDER,
                ListFutureBorrowingsCommand.HELP_MESSAGE + Messages.DIVIDER,
                ListOverdueBorrowingsCommand.HELP_MESSAGE + Messages.DIVIDER,
                CancelFutureBorrowingsCommand.HELP_MESSAGE + Messages.DIVIDER,
                ExitCommand.HELP_MESSAGE + Messages.DIVIDER,
                HelpCommand.HELP_MESSAGE);
    }
}
