package seedu.planitarium.money;


public class Expenditure extends Money {

    /**
     * Initialise a new Expenditure object.
     *
     * @param description - Expenditure's description
     * @param amount      - Expenditure's amount
     */
    public Expenditure(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String toString() {
        return "e " + description + " - " + amount;
    }
}
