package seedu.planitarium.expenditure;


public class Expenditure {

    protected String description;
    protected double amount;

    /**
     * Creates a new Expenditure object.
     *
     * @param description The description of an expenditure
     * @param amount The cost of that expenditure
     */
    public Expenditure(String description, double amount) {
        assert (description != null);
        this.description = description;
        this.amount = amount;
    }

    /**
     * Returns the description of a particular expenditure.
     *
     * @return The description of an expenditure
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the amount of a particular expenditure.
     *
     * @return The amount of an expenditure
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the whole string including
     * the description of the expenditure and its amount in two decimal place.
     *
     * @return The string of the description and the amount of the expenditure
     */
    public String toString() {
        return  description + ": $" + String.format("%.2f", amount);
    }

}
