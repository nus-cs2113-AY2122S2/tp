package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;
import arcs.data.validitychecker.ValidMenuItemTypeChecker;

import java.util.ArrayList;

public class FindMenuItemTypeCommand extends Command {

    public static final String COMMAND_WORD  = "findMenuItemType";

    private String type;

    private ArrayList<String> emptyFields = new ArrayList<>();

    private ArrayList<String> incorrectFields = new ArrayList<>();

    private static final String FOUND_MESSAGE = "The following menu items has been found:";
    private static final String EMPTY_MESSAGE = "No menu item was found.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";
    private static final String INCORRECT_FIELD_MESSAGE = "The type you entered is incorrect";

    public FindMenuItemTypeCommand(String type) {
        this.type = type;
        checkEmptyField();
        checkValidType();
    }

    /**
     * Stores empty field in an array for empty fields.
     *
     */
    private void checkEmptyField() {
        if (type == null || type.isEmpty()) {
            emptyFields.add("Menu Item Type");
        }
    }

    /**
     * Stores invalid field types in an array for incorrect fields.
     *
     */
    private void checkValidType() {
        ValidMenuItemTypeChecker validMenuItemTypeChecker = new ValidMenuItemTypeChecker();
        if (!validMenuItemTypeChecker.isValid(type)) {
            incorrectFields.add("Menu Item Type");
        }
    }

    /**
     * Executes the command to find menu item by type.
     *
     * @return CommandResult result of the executed command.
     */
    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }

        if (!incorrectFields.isEmpty()) {
            return new CommandResult(INCORRECT_FIELD_MESSAGE, incorrectFields);
        }

        ArrayList<MenuItem> result;
        ArrayList<String> menuItemsInfo = new ArrayList<>();
        result = menuItemManager.findMenuItemByCategory(type);

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
