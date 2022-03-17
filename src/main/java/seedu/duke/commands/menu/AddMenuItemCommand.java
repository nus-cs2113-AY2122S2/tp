package seedu.duke.commands.menu;

import seedu.duke.commands.Command;
import seedu.duke.data.exception.ArcsException;
import seedu.duke.data.menu.MenuItem;
import seedu.duke.data.menu.MenuItemList;
import seedu.duke.ui.MenuUi;

public class AddMenuItemCommand extends MenuItemCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "1";

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
    public AddMenuItemCommand(MenuUi ui, MenuItemList menuItems) {
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
        ui.displayAddMenuItemOption();
        String menuItemName = ui.getMenuItemName();
        String menuItemPrice = ui.getMenuItemPrice();
        String menuItemType = ui.getMenuItemType();
        MenuItem menuItem = new MenuItem(menuItemName,menuItemType,menuItemPrice);
        menuItems.addMenuItem(menuItem);
        ui.printAddMessage(menuItem, menuItems.getSize());
    }
}


