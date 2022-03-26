package seedu.planitarium.money;

import java.time.LocalDate;

public abstract class Money {

    protected String description;
    protected double amount;
    protected boolean isPermanent;
    protected LocalDate initDate;

    /**
     * Initialise a new money object.
     * @param description - Money's description
     * @param amount - The amount of money
     */
    public Money(String description, double amount, boolean isPermanent) {
        assert (description != null);
        assert (amount >= 0);
        this.description = description;
        this.amount = amount;
        this.isPermanent = isPermanent;
        this.initDate = LocalDate.now();
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

    public boolean isPermanent() {
        return isPermanent;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    /**
     * Returns the money object's description and its amount in two decimal place.
     * @return The string of the description and the amount of the money
     */
    @Override
    public String toString() {
        return  description + ": $" + String.format("%.2f", amount) + " - Recurring: " + isPermanent;
    }
}
