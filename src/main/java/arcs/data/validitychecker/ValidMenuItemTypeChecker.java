package arcs.data.validitychecker;

import arcs.data.menuitems.MenuItemType;

import java.util.Arrays;

public class ValidMenuItemTypeChecker implements RawInputChecker {

    /**
     * Checks whether the Menu Item type entered is valid.
     * @param menuItemType MenuItemType to check.
     * @return True if it is a valid MenuItemType.
     */
    @Override
    public boolean isValid(String menuItemType) {
        if (menuItemType == null) {
            return false;
        }
        return Arrays.stream(MenuItemType.values()).map(MenuItemType::name).anyMatch(menuItemType::equalsIgnoreCase);
    }
}
