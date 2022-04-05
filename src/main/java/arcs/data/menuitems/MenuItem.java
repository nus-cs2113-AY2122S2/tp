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

    public String getMenuItemInfo() {
        String menuItemInfo = "[" + this.menuItemType.name() + "]" + " "
                + this.menuItemName + " " + this.menuItemPrice;
        return menuItemInfo;
    }

    @Override
    public String toString() {
        String str = this.menuItemType.name() + "/"
                + this.menuItemName + "/" + this.menuItemPrice;
        return str;
    }
}
