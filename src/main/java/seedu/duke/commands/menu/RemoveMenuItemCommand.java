package seedu.duke.commands.menu;

import seedu.duke.data.exception.ArcsException;
import seedu.duke.data.menu.MenuItem;
import seedu.duke.data.menu.MenuItemList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.MenuUi;

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
     * Prompts user for menu items to add
     *
     * @throws ArcsException If user input is valid
     */
    @Override
    public void execute() throws ArcsException {
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
            } catch (IndexOutOfBoundsException e){
                ui.printError(Parser.INVALID_MENU_ITEM_ERROR);
            }
        }
    }
}