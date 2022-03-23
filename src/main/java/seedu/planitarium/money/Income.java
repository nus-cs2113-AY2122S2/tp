package seedu.planitarium.money;

public class Income {
    protected String description;
    protected double amount;

    /**
     * Initialise a new income object.
     * @param description - Income's description
     * @param amount - Income's amount
     */
    public Income(String description, double amount) {
        assert (description != null);
        assert (amount >= 0);
        this.description = description;
        this.amount = amount;
    }

    /**
     * Returns the amount of the income object.
     * @return The amount of income
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the description of the income object.
     * @return The description of income
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the income object's description and its amount in two decimal place.
     * @return The string of the description and the amount of the income
     */
    public String toString() {
        return  description + ": $" + String.format("%.2f", amount);
    }
}
