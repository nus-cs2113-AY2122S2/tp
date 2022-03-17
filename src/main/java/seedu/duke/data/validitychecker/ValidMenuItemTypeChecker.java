package seedu.duke.data.validitychecker;

import seedu.duke.data.menu.MenuItemType;

import java.util.Arrays;

public class ValidMenuItemTypeChecker implements RawInputChecker {
    /**
     * Check input validity.
     *
     * @param menuItemType menu item inputted type check
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String menuItemType) {
        return Arrays.stream(MenuItemType.values()).map(MenuItemType::name).anyMatch(menuItemType::equalsIgnoreCase);
    }
}
