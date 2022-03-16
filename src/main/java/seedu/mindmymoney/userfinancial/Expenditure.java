package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;

import java.util.Objects;

/**
 * Represents user's expenditure.
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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Expenditure)) {
            return false;
        }
        Expenditure expenditure = (Expenditure) object;
        return description.equals(expenditure.description) && (amount == expenditure.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount);
    }

    private static final String CONTROL_SEQUENCE_BEGIN = "<%<";
    private static final String CONTROL_SEQUENCE_END = ">%>";
    private static final String CONTROL_DELIMITER = CONTROL_SEQUENCE_BEGIN + "of amount" + CONTROL_SEQUENCE_END;

    /**
     * Replaces all % with %%s. This is so the control sequences above function correctly.
     * @param s A string to escape.
     * @return The escaped string.
     */
    public static String escapeDataString(String s) {
        return s.replaceAll("%", "%%");
    }

    /**
     * Replaces all %%s with %. This undoes escapeDataString.
     * @param s The string to unescape.
     * @return The unescaped string.
     */
    public static String unescapeDataString(String s) {
        return s.replaceAll("%%", "%");
    }

    /**
     * Returns a String representation of the expenditure meant for automated parsing.
     * @return A serialized expenditure
     */
    public String serialize() {
        return escapeDataString(description)
                + CONTROL_DELIMITER
                + escapeDataString(Integer.toString(amount));
    }

    /**
     * Converts the output of Expenditure.serialize into an Expenditure. This method will
     * not work properly if the description contains the CONTROL_DELIMITER used internally
     * by the function, and such cases may be considered incorrect formatting.
     * @param serialized The serialized expenditure.
     * @return An Expenditure.
     * @throws MindMyMoneyException if formatting is incorrect.
     */
    public static Expenditure deserialize(String serialized) throws MindMyMoneyException {
        String[] parts = serialized.split(CONTROL_DELIMITER);
        if (parts.length != 2) {
            throw new MindMyMoneyException("Invalid format for saved list item");
        }
        try {
            int amount = Integer.parseInt(parts[1]);
            String escapedDescription = unescapeDataString(parts[0].strip());
            return new Expenditure(escapedDescription, amount);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Invalid format for expenditure amount");
        }
    }
}
