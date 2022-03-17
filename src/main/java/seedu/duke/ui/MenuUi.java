package seedu.duke.ui;


import seedu.duke.data.menu.MenuItem;
import seedu.duke.data.menu.MenuItemList;
import seedu.duke.data.validitychecker.*;
import seedu.duke.parser.Parser;

/**
 * Represents the class that interfaces with the user for menu functions
 */

public class MenuUi extends MainUi {

    private static final String lineDivider = ("==================================================");

    /**
     * Initializes Ui with parser.
     *
     * @param parser Parser object.
     */
    public MenuUi(Parser parser) {
        super(parser);
    }

    /**
     * Empty constructor.
     */
    public MenuUi() {

    }

    /**
     * Prints menu UI options
     */
    //NOTE: To allow add, remove, edit and print, in final version.
    public void displaySubMenu() {
        printLineDivider();
        System.out.println(" Welcome, you are now managing menu details! ");
        printLineDivider();
        System.out.println("(1) Add Menu Item\t(2) Remove Menu Item");
        System.out.println("(3) Print Menu Items");
        System.out.println("Enter an option from 1 to 4: ");
    }

    public String readCommand() {
        displaySubMenu();
        String fullCommand = scannerIn.nextLine();
        return fullCommand;
    }

    /**
     * Prints add menu item UI option
     */
    public void displayAddMenuItemOption() {
        printLineDivider();
        System.out.println(" You are adding a new menu item: ");
    }

    /**
     * Prints remove menu item UI option
     */
    public String displayRemoveMenuItemOption() {
        return lineDivider + "\n" + " Enter the index of menu item you want to remove. ";
    }

    /**
     * Prints line divider
     */
    public void printLineDivider() {
        System.out.println(lineDivider);
    }

    /**
     * Prints exit message when user exits menu interface
     */
    public void exitMenuMessage() {
        System.out.println("Exiting menu interface.");
    }

    /**
     * Prints error message
     *
     * @param message
     *            String representation of error message
     */
    public void printError(String message) {
        System.out.println(message);
    }

    /**
     * Prints string representation of menu item
     *
     * @param menuItem menu item to be printed
     */
    public void printMenuItem(MenuItem menuItem) {
        System.out.println(menuItem.getMenuItem());
    }

    /**
     * Prints the size of the menu item list
     *
     * @param size of menu item list
     */
    public void printSize(int size) {
        System.out.println("The menu has " + size + " items.");
    }

    /**
     * Prints the message when user adds a new menu item
     *
     * @param menuItem the menu item to be added
     * @param size The size of the menu item list
     */
    public void printAddMessage(MenuItem menuItem, int size) {
        System.out.println("Menu item has been added!");
        printMenuItem(menuItem);
        printSize(size);
    }

    /**
     * Prints the message when user removes a task
     *
     * @param menuItem menu item to be removed from the list
     * @param size The size of the menu item list
     */
    public void printRemoveMessage(MenuItem menuItem, int size) {
        System.out.println("The menu item has been removed!");
        printMenuItem(menuItem);
        printSize(size);
    }

    /**
     * Prints menu items in the menu item arraylist
     *
     * @param menuItems
     */
    public void printMenuItems(MenuItemList menuItems) {
        System.out.println("Your current in-flight menu is:");
        for (int i = 0; i < menuItems.getSize(); i++) {
            System.out.println(i + 1 + ". " + menuItems.getMenuItem(i).getMenuItem());
        }
    }

    public String getMenuItemName() {
        return getUserInput("Enter name of menu item: ", new MinimumInputLengthChecker());
    }

    public String getMenuItemPrice() {
        return getUserInput("Enter price of menu item (in $): ", new ValidPriceChecker());
    }

    public String getMenuItemType() {
        return getUserInput("Enter the type of menu item: ", new ValidMenuItemTypeChecker());
    }

    public String getIndexOfMenuItemToRemove() {
        return getUserInput(displayRemoveMenuItemOption(), new ValidMenuItemToRemoveChecker());
    }
}
