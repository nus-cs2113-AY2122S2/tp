package seedu.duke.itemlists;

import seedu.duke.exceptions.InvalidItemNameException;

/**
 * Represents an item that is stored within the inventory. An item object consists of the item's name as well as its
 * pax.
 */

public class Item {
    private String name;
    private int pax;
    private String updatedName;

    public Item(String name, int pax) throws InvalidItemNameException {
        boolean isItemNameFormatValid = checkIfItemNameFormatValid(name);
        if (isItemNameFormatValid == false) {
            throw new InvalidItemNameException();
        }
        setName(name);
        setPax(pax);
    }

    public Item(String name, String updatedName) throws InvalidItemNameException {
        boolean isItemNameFormatValid = checkIfItemNameFormatValid(name);
        if (isItemNameFormatValid == false) {
            throw new InvalidItemNameException();
        }

        boolean isUpdatedItemNameFormatValid = checkIfItemNameFormatValid(updatedName);
        if (isUpdatedItemNameFormatValid == false) {
            throw new InvalidItemNameException();
        }
        setName(name);
        setUpdatedName(updatedName);
    }

    public Item(String name) throws InvalidItemNameException {
        boolean isItemNameFormatValid = checkIfItemNameFormatValid(name);
        if (isItemNameFormatValid == false) {
            throw new InvalidItemNameException();
        }
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * Checks if the itemName contains only alphabetical characters, digits, whitespaces or apostrophe in accordance to
     * the restrictions mentioned in the user guide.
     *
     * @param itemName The itemName within the command inputted by the user.
     * @return true if the itemName did not violate any one of the restrictions mentioned above.false if the item name
     *     violates any one of the restrictions.
     */
    private boolean checkIfItemNameFormatValid(String itemName) {
        boolean isItemNameFormatValid = false;
        if (itemName.matches("^([a-z]|[A-Z]|[0-9]|\\s|')+$")) {
            isItemNameFormatValid = true;
        }
        return isItemNameFormatValid;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }
}
