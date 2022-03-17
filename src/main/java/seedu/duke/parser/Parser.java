package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.menu.AddMenuItemCommand;
import seedu.duke.commands.menu.MenuItemCommand;
import seedu.duke.commands.menu.PrintMenuItemsCommand;
import seedu.duke.commands.menu.RemoveMenuItemCommand;
import seedu.duke.data.exception.ArcsException;
import seedu.duke.data.menu.MenuItemList;
import seedu.duke.ui.MainUi;
import seedu.duke.ui.MenuUi;

import java.awt.*;

public class Parser {
    public static final String INVALID_MENU_ITEM_ERROR = "Please enter a valid index";

    private MenuUi menuUi;
    private MenuItemList menuItemList;
    public Command parseMainMenuInput(String fullCommand, MainUi mainUi, MenuItemList menuItems) throws ArcsException {
        Command command;
        switch (fullCommand.trim()) {
        case MenuItemCommand.COMMAND_WORD:
            menuUi = new MenuUi();
            command = parseUserMenuInput(menuUi.readCommand(), menuItems, menuUi);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand(mainUi);
            break;
        default:
            throw new ArcsException(Command.COMMAND_ERROR);
        }
        return command;
    }

    public Command parseUserMenuInput(String fullCommand, MenuItemList menuItems, MenuUi ui) throws ArcsException {
        Command command;
        switch (fullCommand.trim()) {
        case AddMenuItemCommand.COMMAND_WORD:
            command = new AddMenuItemCommand(ui,menuItems);
            break;
        case PrintMenuItemsCommand.COMMAND_WORD:
            command = new PrintMenuItemsCommand(ui,menuItems);
            break;
        case RemoveMenuItemCommand.COMMAND_WORD:
            command = new RemoveMenuItemCommand(ui,menuItems);
            break;
        default:
            throw new ArcsException(Command.COMMAND_ERROR);
        }
        return command;
    }

    /**
     * Returns if user input is yes.
     *
     * @param choice User input.
     * @return True if yes, false otherwise.
     */
    public boolean isYes(String choice) {
        if (choice.equalsIgnoreCase("YES")) {
            return true;
        }
        else {
            return false;
        }
    }
}
