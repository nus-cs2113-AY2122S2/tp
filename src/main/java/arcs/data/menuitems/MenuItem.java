package arcs.data.menuitems;

public class MenuItem {
    private String menuItemName;
    private MenuItemType menuItemType;
    private String menuItemPrice;

    public MenuItem(String name, String itemType, String price) {
        this.menuItemName = name;
        this.menuItemType = MenuItemType.valueOf(itemType.toUpperCase());
        this.menuItemPrice = price;
    }

    /**
     * Gets the Menu Item name.
     * @return String of the name of menu item.
     */
    public String getMenuItemName() {
        return this.menuItemName;
    }

    /**
     * Gets the Menu Item type.
     * @return String of the type of menu item.
     */
    public String getMenuItemType() {
        return this.menuItemType.name();
    }

    /**
     * Gets the price of the menu item.
     * @return String of the price of menu item.
     */
    public String getMenuItemPrice() {
        return this.menuItemPrice;
    }

    /**
     * Gets the Menu item info to print.
     * @return String of menu item info for printing.
     */
    public String getMenuItemInfo() {
        String menuItemInfo = "[" + this.menuItemType.name() + "]" + " "
                + this.menuItemName + " " + this.menuItemPrice;
        return menuItemInfo;
    }

    /**
     * Gets the Menu item info to store.
     * @return String of menu item info for storage.
     */
    @Override
    public String toString() {
        String str = this.menuItemType.name() + "/"
                + this.menuItemName + "/" + this.menuItemPrice;
        return str;
    }
}
