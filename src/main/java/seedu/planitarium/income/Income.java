package seedu.planitarium.income;

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

    public String getDescription() {
        return description;
    }

    public String toString() {
        return  description + ": $" + String.format("%.2f", amount);
    }
}
