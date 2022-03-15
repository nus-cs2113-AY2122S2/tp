package seedu.planitarium.income;

public class Income {
    protected String description;
    protected double amount;

    /**
     * Initialise a new income object
     * @param description - Income's description
     * @param amount - Income's amount
     */
    public Income(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

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
