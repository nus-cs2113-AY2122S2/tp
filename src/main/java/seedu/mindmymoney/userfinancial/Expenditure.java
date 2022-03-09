package seedu.mindmymoney.userfinancial;

/**
 * Represents user expenditure
 */
public class Expenditure {
    private String description;
    private int amount;

    public Expenditure(String description, int amount) {
        setDescription(description);
        setAmount(amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription() + " of $" + getAmount();
    }
}
