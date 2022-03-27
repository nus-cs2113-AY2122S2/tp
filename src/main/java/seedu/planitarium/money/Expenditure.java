package seedu.planitarium.money;

import java.time.LocalDate;

public class Expenditure extends Money {
    /**
     * Initialise a new Expenditure object.
     *
     * @param description - Expenditure's description
     * @param amount      - Expenditure's amount
     */
    public Expenditure(String description, double amount, boolean isPermanent) {
        super(description, amount, isPermanent);
    }

    public String saveString() {
        return "e " + description + " - " + amount + " - " + isPermanent;
    }
}
