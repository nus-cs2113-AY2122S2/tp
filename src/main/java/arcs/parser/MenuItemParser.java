package arcs.parser;

import arcs.commands.Command;
import arcs.commands.UndefinedCommand;
import arcs.commands.menuitem.AddMenuItemCommand;
import arcs.commands.menuitem.DeleteMenuItemCommand;
import arcs.data.exception.ArcsException;

public class MenuItemParser {

    private static final String MENU_ITEM_TYPE_COMMAND_WORD = "type";
    private static final String MENU_ITEM_NAME_COMMAND_WORD = "name";
    private static final String MENU_ITEM_PRICE_COMMAND_WORD = "price";
    private static final String INVALID_PARAMETER_MESSAGE = "Spaces for menu item name and type"
            + " must be separated by '_'";

    /**
     * Parses the User Input into a proper format for the Add Menu Item Command.
     * @param argumentLine Raw User Input.
     * @return Add Menu Item Command.
     * @throws ArcsException If user does not use "_" to separate its Menu Item name and type.
     */
    public static Command prepareAddMenuItem(String argumentLine) throws ArcsException {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new AddMenuItemCommand(null,null,null);
        }
        String[] args = argumentLine.split(" ");
        String menuItemName = null;
        String menuItemType = null;
        String menuItemPrice = null;
        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String[] argSplit = arg.split("/", 2);
            if (argSplit.length < 2) {
                throw new ArcsException(INVALID_PARAMETER_MESSAGE);
            }
            String field = argSplit[0].trim();
            String value = argSplit[1].trim();
            switch (field) {
            case MENU_ITEM_NAME_COMMAND_WORD:
                menuItemName = value;
                //replace underscore separator with space
                menuItemName = menuItemName.replace("_", " ");
                break;
            case MENU_ITEM_TYPE_COMMAND_WORD:
                menuItemType = value;
                break;
            case MENU_ITEM_PRICE_COMMAND_WORD:
                menuItemPrice = value;
                break;
            default:
                break;
            }
        }
        return new AddMenuItemCommand(menuItemName,menuItemType,menuItemPrice);
    }

    /**
     * Parses the user input into a correct format for Delete Menu Item Command.
     * @param argumentLine Raw User Input.
     * @return Delete Menu Item Command.
     */
    public static Command prepareDeleteMenuItemCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new UndefinedCommand("Index is not specified");
        }
        Command result;
        try {
            int index = Integer.parseInt(argumentLine);
            result = new DeleteMenuItemCommand(index);
        } catch (NumberFormatException e) {
            result = new UndefinedCommand("Index should be an integer.");
        }
        return result;
    }

}
