package arcs.data.validitychecker;

import arcs.data.menuitems.MenuItemType;

import java.util.Arrays;

public class ValidMenuItemTypeChecker implements RawInputChecker {
    @Override
    public boolean isValid(String menuItemType) {
        System.out.println("inside valid menu item");
        if (menuItemType == null) {
            return false;
        }
        return Arrays.stream(MenuItemType.values()).map(MenuItemType::name).anyMatch(menuItemType::equalsIgnoreCase);
    }
}
