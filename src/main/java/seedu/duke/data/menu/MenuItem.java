package seedu.duke.data.menu;

import seedu.duke.data.menu.MenuItemType;

public class MenuItem {
    private int menuItemID;
    private String menuItemName;
    private MenuItemType menuItemType;
    private String menuItemPrice;
    private static int runningCount=1; //used to track the number to Menu items.

    public MenuItem(String name, String itemType, String price) {
        this.menuItemName=name;
        this.menuItemType=MenuItemType.valueOf(itemType.toUpperCase());
        this.menuItemPrice=price;
        this.menuItemID=runningCount;
        runningCount++;
    }

    public String getMenuItemName() {
        return this.menuItemName;
    }

    public String getMenuItemType() {
        return this.menuItemType.name();
    }

    public String getMenuItemPrice() {
        return this.menuItemPrice;
    }

    public void setMenuItemName(String name) {
        this.menuItemName = name;
    }

    public void setMenuItemType(String menuItemType) {
        this.menuItemType = MenuItemType.valueOf(menuItemType);
    }

    /**
     * Getter for the menu item object
     *
     * @return string representation of the menu item
     */
    public String getMenuItem() {
        //return this.menuItemName + ": " + this.menuItemPrice;
        return "[" + this.menuItemType.name() + "] " + this.menuItemName + ": " + this.menuItemPrice;
    }
}
