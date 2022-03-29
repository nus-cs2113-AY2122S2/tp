//@@author tjiarong

package seedu.planitarium.money;

public class Income extends Money {

    /**
     * Initialise a new income object.
     *
     * @param description Income's description
     * @param amount      Income's amount
     * @param isPermanent Income's recurrence
     */
    public Income(String description, double amount, boolean isPermanent) {
        super(description, amount, isPermanent);
    }

    /**
     * Return object's string representation for storage purposes.
     *
     * @return Object attributes as string
     */
    public String saveString() {
        return "i " + description + " - " + amount + " - " + isPermanent;
    }
}
