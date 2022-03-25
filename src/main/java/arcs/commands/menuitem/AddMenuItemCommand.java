package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;
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

    private static final String SUCCESS_MESSAGE = "OK! This menu item has been added: ";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified: ";
    private static final String INCORRECT_FIELD_MESSAGE = "These fields are incorrect";

    public AddMenuItemCommand(String name, String type, String price) throws IllegalArgumentException {
        System.out.println("reach here -1");
        checkEmptyField(name, type, price);
        checkFieldValidity(type,price);
        System.out.println("reached here 0");
        try {
            this.toAdd = new MenuItem(name, type, price);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type");
        } catch (NullPointerException e) {
            System.out.println("Invalid command");
        }
        System.out.println("reach here");
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
        System.out.println(toAdd.toString());
        menuItemManager.addMenuItem(toAdd);
        System.out.println("here22222");
        CommandResult result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                + toAdd.getMenuItemInfo());
        return result;
    }
}
