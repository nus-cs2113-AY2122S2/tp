package arcs.commands.menu;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.exception.ArcsException;
import arcs.data.menu.MenuItem;
import arcs.data.menu.MenuItemList;
import arcs.parser.Parser;
import arcs.ui.MenuUi;

public class RemoveMenuItemCommand extends MenuItemCommand {

    /**
     * Command word to trigger this command.
     */
    public static final String COMMAND_WORD = "2";

    private static final int RAW_AND_ACTUAL_INDEX_DIFFERENCE = 1;

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
    public RemoveMenuItemCommand(MenuUi ui, MenuItemList menuItems) {
        super(ui);
        this.menuItems = menuItems;
    }

    /**
     * Prompts user for menu items to remove.
     *
     * @throws ArcsException If user input is valid
     */
    @Override
    public CommandResult execute() throws ArcsException {
        boolean isItemRemoved = false;
        ui.printMenuItems(menuItems);
        //ui.displayRemoveMenuItemOption();
        while (!isItemRemoved) {
            try {
                String rawStringIndex = ui.getIndexOfMenuItemToRemove();
                int indexToRemove = Integer.parseInt(rawStringIndex) - RAW_AND_ACTUAL_INDEX_DIFFERENCE;
                MenuItem menuItem = menuItems.getMenuItem(indexToRemove);
                menuItems.removeMenuItem(indexToRemove);
                ui.printRemoveMessage(menuItem, menuItems.getSize());
                isItemRemoved = true;
            } catch (IndexOutOfBoundsException e) {
                ui.printError(Parser.INVALID_MENU_ITEM_ERROR);
            }
        }
        return null;
    }
}