package arcs.data.menu;

import java.util.ArrayList;

public class MenuItemList {
    /**
     * Represents the controller for ArrayList of menu items in ARCS
     */
    private ArrayList<MenuItem> menuItemList;
    /**
     * Constructs the class with the menu items arraylist
     *
     * @param menuItemList
     */
    public MenuItemList(ArrayList<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    /**
     * Getter for a specific menu item
     *
     * @param index Index of menu item
     *            Index of task
     * @return Object of a menu item
     */
    public MenuItem getMenuItem(int index) {
        return menuItemList.get(index);
    }

    /**
     * Getter for menu item list
     *
     * @return ArrayList of menu items
     */
    public ArrayList<MenuItem> getMenuItemList() {
        return this.menuItemList;
    }

    /**
     * Getter for menu item list size
     *
     * @return Size of arraylist of menu items
     */
    public int getSize() {
        return menuItemList.size();
    }

    /**
     * Removing of menu items from the list
     *
     * @param index of Menu Item object to remove.
     */
    public void removeMenuItem(int index) {
        menuItemList.remove(index);
    }

    /**
     *  Adding a menu item to the list of menu items
     *
     * @param menuItem
     */
    public void addMenuItem(MenuItem menuItem) {
        menuItemList.add(menuItem);
    }
}
