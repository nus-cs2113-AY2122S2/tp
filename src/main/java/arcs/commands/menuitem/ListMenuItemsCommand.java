package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;

import java.util.ArrayList;

public class ListMenuItemsCommand extends Command {

    public static final String COMMAND_WORD = "listMenuItems";
    private static final String FEEDBACK = "Existing Menu Items: ";

    /**
     * Executes the command to list of menu items.
     *
     * @return CommandResult result of the executed command.
     */
    @Override
    public CommandResult execute() {
        ArrayList<MenuItem> menuItems = menuItemManager.getAllMenuItems();
        ArrayList<String> menuItemsInfo = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            menuItemsInfo.add(menuItem.getMenuItemInfo());
        }
        return new CommandResult(FEEDBACK, menuItemsInfo);
    }
}
