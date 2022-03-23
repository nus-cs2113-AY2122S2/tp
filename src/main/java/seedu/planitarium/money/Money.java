package seedu.planitarium.money;

public abstract class Money {
    protected String description;
    protected double amount;

    /**
     * Initialise a new money object.
     * @param description - Money's description
     * @param amount - The amount of money
     */
    public Money(String description, double amount) {
        assert (description != null);
        assert (amount >= 0);
        this.description = description;
        this.amount = amount;
    }

    /**
     * Returns the amount of the money object.
     * @return The amount of money
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the description of the money object.
     * @return The description of money
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the money object's description and its amount in two decimal place.
     * @return The string of the description and the amount of the money
     */

    public String toString() {
        return  description + ": $" + String.format("%.2f", amount);
    }
}
