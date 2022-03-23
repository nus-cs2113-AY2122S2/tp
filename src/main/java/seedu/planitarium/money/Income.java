package seedu.planitarium.money;

public class Income extends Money {

    /**
     * Initialise a new income object.
     * @param description - Income's description
     * @param amount - Income's amount
     */
    public Income(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String toString() {
        return "i " + description + " - " + amount;
    }
}
