package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;

/**
 * Represents the income entry.
 */
public class Income {
    private int amount;
    private String category;

    public Income(int amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Returns the income entry as a string format.
     *
     * @return String format.
     */
    @Override
    public String toString() {
        String incomeInfo = "Amount: " + amount + "\n"
                + "   Category: " + category + "\n";
        return incomeInfo;
    }

    /**
     * Returns the input for an add command that recreates this Income.
     *
     * @return A serialized CreditCard
     */
    public String getAddCommand() throws MindMyMoneyException {
        return String.format("add /i /a %f /c %s\n", amount, category);
    }
}
