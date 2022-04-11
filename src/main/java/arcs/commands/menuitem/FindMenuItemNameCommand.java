package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;

import java.util.ArrayList;

public class FindMenuItemNameCommand extends Command {

    public static final String COMMAND_WORD = "findMenuItemName";

    private String name;
    private ArrayList<String> emptyFields = new ArrayList<>();

    private static final String FOUND_MESSAGE = "The following menu items has been found:";
    private static final String EMPTY_MESSAGE = "No menu item was found.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";

    public FindMenuItemNameCommand(String name) {
        this.name = name;
        checkEmptyField();
    }

    /**
     * Stores empty field in an array.
     *
     */
    private void checkEmptyField() {
        if (name == null || name.isEmpty()) {
            emptyFields.add("Menu Item Name");
        }
    }

    /**
     * Executes the command to find menu item by name.
     *
     * @return CommandResult result of the executed command.
     */
    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }

        ArrayList<MenuItem> result;
        ArrayList<String> menuItemsInfo = new ArrayList<>();
        result = menuItemManager.findMenuItem(name);

        for (MenuItem menuItem: result) {
            menuItemsInfo.add(menuItem.getMenuItemInfo());
        }

        CommandResult commandResult;
        if (menuItemsInfo.isEmpty()) {
            commandResult = new CommandResult(EMPTY_MESSAGE);
        } else {
            commandResult = new CommandResult(FOUND_MESSAGE, menuItemsInfo);
        }

        return commandResult;
    }
}
