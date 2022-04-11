package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.PropertyList;

/**
 * Represents the income entry.
 */
public class Income implements MindMyMoneySerializable {
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
        String incomeInfo = "Amount: $" + amount + "\n"
                + "   Category: " + category + "\n";
        return incomeInfo;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Income)) {
            return false;
        }
        Income income = (Income) object;
        return (amount == income.amount) && (category.equals(income.category));
    }

    /**
     * Returns a String representation of this income source, in a machine-readable format.
     * @return The serialized Income.
     */
    public String serialize() {
        PropertyList plist = new PropertyList();
        plist.addProperty("category", category);
        plist.addProperty("amount", Integer.toString(amount));
        return plist.serialize();
    }

    /**
     * Converts the output of Income#serialize back into an Income.
     * @param serialized The serialized Income.
     * @return An Income.
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static Income deserialize(String serialized) throws MindMyMoneyException {
        PropertyList plist = PropertyList.deserialize(serialized);
        try {
            return new Income(Integer.parseInt(plist.getValue("amount")),
                    plist.getValue("category"));
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Invalid number for amount during deserialization of " + serialized);
        } catch (MindMyMoneyException e) {
            String missingProperty = e.getMessage();
            throw new MindMyMoneyException("Line [" + serialized + "] does not contain required value "
                    + missingProperty);
        }
    }
}
