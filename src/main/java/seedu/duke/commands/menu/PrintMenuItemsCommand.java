package seedu.duke.commands.menu;

import seedu.duke.data.exception.ArcsException;
import seedu.duke.data.menu.MenuItem;
import seedu.duke.data.menu.MenuItemList;
import seedu.duke.ui.MenuUi;

public class PrintMenuItemsCommand extends MenuItemCommand {
    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "3";

    /**
     * List of Menu Items.
     */
    private final MenuItemList menuItems;

    /**
     * Initializes command for execution.
     *
     * @param ui      Ui object.
     * @param menuItems a list of menu items available
     */
    public PrintMenuItemsCommand(MenuUi ui, MenuItemList menuItems) {
        super(ui);
        this.menuItems = menuItems;
    }

    /**
     * Prompts user for menu items to add
     *
     * @throws ArcsException If user input is valid
     */
    @Override
    public void execute() throws ArcsException {
        ui.printMenuItems(menuItems);
    }
}
