package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;
import arcs.data.validitychecker.ValidMenuItemPriceChecker;
import arcs.data.validitychecker.ValidMenuItemTypeChecker;

import java.util.ArrayList;

public class AddMenuItemCommand extends Command {

    public static final String COMMAND_WORD = "addMenuItem";

    private ValidMenuItemTypeChecker validMenuItemTypeChecker;
    private ValidMenuItemPriceChecker validMenuItemPriceChecker;
    private MenuItem toAdd;

    private ArrayList<String> emptyFields = new ArrayList<>();
    private ArrayList<String> incorrectFields = new ArrayList<>();

    /**
     * Messages to output to user.
     */
    private static final String SUCCESS_MESSAGE = "OK! This menu item has been added: ";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified: ";
    private static final String INCORRECT_FIELD_MESSAGE = "These fields are incorrect";
    private static final String DUPLICATE_ITEM_MESSAGE = "This item name and type is already in the menu ";

    /**
     * Checks whether Menu Item to be added is valid.
     *
     * @param name Name of menu item to add.
     * @param type Name of menu item type to add.
     * @param price Price of menu item to add.
     */
    public AddMenuItemCommand(String name, String type, String price) {
        checkEmptyField(name, type, price);
        checkFieldValidity(type, price);
        try {
            this.toAdd = new MenuItem(name, type, price);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type");
        } catch (NullPointerException e) {
            System.out.println("Invalid command");
        }
    }

    /**
     * Stores invalid field names in an array.
     *
     * @param type Type of Menu Item.
     * @param price Price of Menu Item
     */
    private void checkFieldValidity(String type, String price) {
        validMenuItemTypeChecker = new ValidMenuItemTypeChecker();
        validMenuItemPriceChecker = new ValidMenuItemPriceChecker();
        if (!validMenuItemTypeChecker(type)) {
            incorrectFields.add("MenuItem type");
        }
        if (!validMenuItemPriceChecker(price)) {
            incorrectFields.add("MenuItem price");
        }
    }

    /**
     * Checks validity of menu item type.
     *
     * @param type Menu item type.
     * @return true if menu item type is valid.
     */
    private boolean validMenuItemTypeChecker(String type) {
        return validMenuItemTypeChecker.isValid(type);
    }

    /**
     * Checks validity of menu item price.
     *
     * @param price Menu item price.
     * @return true if menu item price is valid.
     */
    private boolean validMenuItemPriceChecker(String price) {
        return validMenuItemPriceChecker.isValid(price);
    }

    /**
     * Checks whether any field needed to create a menu item is missing.
     *
     * @param type Menu item type.
     * @param name Menu Item name.
     * @param price Menu Item price.
     */
    private void checkEmptyField(String name, String type, String price) {
        if (name == null || name.isEmpty()) {
            emptyFields.add("MenuItem name");
        }
        if (type == null || type.isEmpty()) {
            emptyFields.add("MenuItem type");
        }
        if (price == null || price.isEmpty()) {
            emptyFields.add("MenuItem price");
        }
    }

    /**
     * Executes command and returns the result.
     *
     * @return result The result of the command executed.
     */
    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }
        if (!incorrectFields.isEmpty()) {
            return new CommandResult(INCORRECT_FIELD_MESSAGE, incorrectFields);
        }
        if (menuItemManager.checkExistingMenuItem(toAdd.getMenuItemName(),toAdd.getMenuItemType())) {
            return new CommandResult(DUPLICATE_ITEM_MESSAGE);
        }
        menuItemManager.addMenuItem(toAdd);
        return new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + toAdd.getMenuItemInfo());
    }
}
