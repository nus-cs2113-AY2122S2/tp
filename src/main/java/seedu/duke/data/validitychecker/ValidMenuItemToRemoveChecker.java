package seedu.duke.data.validitychecker;

import seedu.duke.data.menu.MenuItemType;

import java.util.Arrays;

public class ValidMenuItemToRemoveChecker implements RawInputChecker {
    /**
     * Check input validity, ensure its a digit greater than zero.
     *
     * @param menuItemIndex raw index of menu item
     * @return If input is valid.
     */
    @Override
    public boolean isValid(String menuItemIndex) {
        try {
            int numericIndex = Integer.parseInt(menuItemIndex);
            if (!(numericIndex > 0)) {
                return false;
            }
            else {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
