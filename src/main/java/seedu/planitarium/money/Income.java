package seedu.planitarium.money;

import java.time.LocalDate;

public class Income extends Money {
    /**
     * Initialise a new income object.
     * @param description - Income's description
     * @param amount - Income's amount
     */
    public Income(String description, double amount, boolean isPermanent) {
        super(description, amount, isPermanent);
    }

    public String saveString() {
        return "i " + description + " - " + amount + " - " + isPermanent;
    }
}
