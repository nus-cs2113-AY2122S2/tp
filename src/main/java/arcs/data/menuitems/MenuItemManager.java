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

    public void addMenuItem(MenuItem newMenuItem) {
        menuItems.add(newMenuItem);
    }

    public ArrayList<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    public MenuItem deleteMenuItem(int index) throws ArcsException {
        if (index <= minimumIndex || index > menuItems.size())  {
            throw new ArcsException("Index out of bound.");
        }
        MenuItem deleted = menuItems.get(index - difference);
        menuItems.remove(index - difference);
        return deleted;
    }

    public ArrayList<MenuItem> findMenuItem(String name) {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getMenuItemName().contains(name)) {
                result.add(menuItem);
            }
        }
        return result;
    }

    public ArrayList<MenuItem> findMenuItemByCategory(String category) {
        ArrayList<MenuItem> result = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getMenuItemType().equals(category.toUpperCase())) {
                result.add(menuItem);
            }
        }
        return result;
    }
    //check validity of a reserved item, return true if valid, if not false
    public boolean menuItemChecker(MenuItem reservedItem) {
        for (MenuItem menuItem: menuItems) {
            if (reservedItem.getMenuItemName().equalsIgnoreCase(menuItem.getMenuItemName())) {
                if (reservedItem.getMenuItemType().equalsIgnoreCase(menuItem.getMenuItemType())) {
                    return true;
                }
            }
        }
        return false;
    }

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


}
