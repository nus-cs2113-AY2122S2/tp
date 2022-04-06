package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;
import arcs.data.menuitems.MenuItemManager;
import arcs.data.validitychecker.ValidMenuItemPriceChecker;
import arcs.data.validitychecker.ValidMenuItemTypeChecker;

import java.util.ArrayList;

public class AddMenuItemCommand extends Command {

    private ValidMenuItemTypeChecker validMenuItemTypeChecker;
    private ValidMenuItemPriceChecker validMenuItemPriceChecker;

    public static final String COMMAND_WORD = "addMenuItem";
    private MenuItem toAdd;
    private ArrayList<String> emptyFields = new ArrayList<>();
    private ArrayList<String> incorrectFields = new ArrayList<>();
    private ArrayList<String> duplicateFields = new ArrayList<>();

    private static final String SUCCESS_MESSAGE = "OK! This menu item has been added: ";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified: ";
    private static final String INCORRECT_FIELD_MESSAGE = "These fields are incorrect";
    private static final String DUPLICATE_ITEM_MESSAGE = "This item name and type is already in the menu ";

    public AddMenuItemCommand(String name, String type, String price) throws IllegalArgumentException {
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

    private boolean validMenuItemTypeChecker(String type) {
        return validMenuItemTypeChecker.isValid(type);
    }

    private boolean validMenuItemPriceChecker(String price) {
        return validMenuItemPriceChecker.isValid(price);
    }

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
        CommandResult result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + toAdd.getMenuItemInfo());
        return result;
    }
}
