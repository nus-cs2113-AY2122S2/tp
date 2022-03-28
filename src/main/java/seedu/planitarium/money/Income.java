//@@author tjiarong

package seedu.planitarium.money;

public class Income extends Money {

    /**
     * Initialise a new income object.
     * @param description - Income's description
     * @param amount - Income's amount
     * @param isPermanent - Income's recurrence
     */
    public Income(String description, double amount, boolean isPermanent) {
        super(description, amount, isPermanent);
    }

    public String saveString() {
        return "i " + description + " - " + amount + " - " + isPermanent;
    }
}
