//@@author tjiarong

package seedu.planitarium.money;

import java.time.LocalDate;

public abstract class Money {

    protected String description;
    protected double amount;
    protected boolean isPermanent;
    protected LocalDate initDate;

    /**
     * Initialise a new money object.
     * @param description Money's description
     * @param amount The amount of money
     * @param isPermanent Money's recurrence
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

    /**
     * Returns the boolean recurring status of the money object.
     * @return The boolean status of the object
     */
    public boolean isPermanent() {
        return isPermanent;
    }

    /**
     * Returns the initialisation date of the money object.
     * @return The initialisation date of money
     */
    public LocalDate getInitDate() {
        return initDate;
    }

    /**
     * Sets the description of the money object.
     * @param description String description of the money object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the amount of the money object.
     * @param amount Amount of the money object
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the recurring status of the money object.
     * @param permanent Recurring status of the money object
     */
    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    /**
     * Returns the money object string representation, which includes its
     * description, its amount in two decimal place, and it's recurring status.
     * @return The object's string representation.
     */
    @Override
    public String toString() {
        return  description + ": $" + String.format("%.2f", amount) + " - Recurring: " + isPermanent;
    }
}
