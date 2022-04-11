package arcs.data.menuitems;

import arcs.data.exception.ArcsException;


import java.util.ArrayList;

public class MenuItemManager {

    private ArrayList<MenuItem> menuItems;

    private static final int minimumIndex = 0;
    private static final int difference = 1;

    public MenuItemManager() {
        menuItems = new ArrayList<>();
    }

    public MenuItemManager(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Adds a menu item to the list of menu items.
     * @param newMenuItem the menu item to be added.
     */
    public void addMenuItem(MenuItem newMenuItem) {
        menuItems.add(newMenuItem);
    }

    /**
     * Gets all the Menu Items in the list of menu items.
     * @return list of Menu items.
     */
    public ArrayList<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    /**
     * Delete the menu item specified by the user.
     * @param index raw index that the user sees on the menu.
     * @return The Menu Item deleted.
     * @throws ArcsException when the Menu Item does not exist.
     */
    public MenuItem deleteMenuItem(int index) throws ArcsException {
        if (index <= minimumIndex || index > menuItems.size())  {
            throw new ArcsException("Index out of bound.");
        }
        MenuItem deleted = menuItems.get(index - difference);
        menuItems.remove(index - difference);
        return deleted;
    }

    /**
     * Finds the Menu Item that corresponds to the name given.
     * @param name name of the menu item to search for.
     * @return list of menu items that consist of the name.
     */
    public ArrayList<MenuItem> findMenuItem(String name) {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getMenuItemName().contains(name)) {
                result.add(menuItem);
            }
        }
        return result;
    }

    /**
     * Finds the Menu Item that corresponds to a given category.
     * @param category of the Menu Items to find.
     * @return List of Menu Items that is in the category given.
     */
    public ArrayList<MenuItem> findMenuItemByCategory(String category) {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getMenuItemType().equals(category.toUpperCase())) {
                result.add(menuItem);
            }
        }
        return result;
    }


    /**
     * Gets the Menu Item to be reserved by its name and type.
     * @param menuItemType Type of the Menu Item to reserve.
     * @param menuItemName Name of the Menu Item to reserve.
     * @return
     */
    public MenuItem getMenuItemByNameAndType(String menuItemType, String menuItemName) {
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getMenuItemName().equals(menuItemName)) {
                if (menuItem.getMenuItemType().equalsIgnoreCase(menuItemType)) {
                    return menuItem;
                }
            }
        }
        return null;
    }

    /**
     * Checks whether a menu item already exist.
     * @param name Name of a menu item to check.
     * @param type Type of the menu item to check.
     * @return True if the Menu Item already exists in the Menu.
     */
    public boolean checkExistingMenuItem(String name, String type) {
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getMenuItemName().equals(name) && menuItem.getMenuItemType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

}
