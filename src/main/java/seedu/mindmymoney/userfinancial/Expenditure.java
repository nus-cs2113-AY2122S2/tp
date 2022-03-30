package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;

import java.util.Objects;

/**
 * Represents the expenditure entry.
 */
public class Expenditure {
    private String description;
    private float amount;
    private String category;
    private String paymentMethod;
    private String time;

    public Expenditure(String paymentMethod, String category, String description, float amount, String time) {
        setDescription(description);
        setAmount(amount);
        setCategory(category);
        setPaymentMethod(paymentMethod);
        setTime(time);
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPaymentMethod(String expenditure) {
        this.paymentMethod = expenditure;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "$" + getAmount() + " on " + getDescription() + ". Paid using "
                + getPaymentMethod() + " [" + getCategory() + "]" + " [" + getTime() + "]";

    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Expenditure)) {
            return false;
        }
        Expenditure expenditure = (Expenditure) object;
        return description.equals(expenditure.description) && (amount == expenditure.amount)
                && category.equals(expenditure.category) && paymentMethod.equals(expenditure.paymentMethod)
                && time.equals(expenditure.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, category, paymentMethod, time);
    }

    private static final String CONTROL_SEQUENCE_BEGIN = "<%<";
    private static final String CONTROL_SEQUENCE_END = ">%>";
    private static final String CONTROL_DELIMITER = CONTROL_SEQUENCE_BEGIN + "of amount" + CONTROL_SEQUENCE_END;

    /**
     * Replaces all % with %%s. This is so the control sequences above function correctly.
     *
     * @param s A string to escape.
     * @return The escaped string.
     */
    public static String escapeDataString(String s) {
        return s.replaceAll("%", "%%");
    }

    /**
     * Replaces all %%s with %. This undoes escapeDataString.
     *
     * @param s The string to unescape.
     * @return The unescaped string.
     */
    public static String unescapeDataString(String s) {
        return s.replaceAll("%%", "%");
    }

    /**
     * Returns a String representation of the expenditure meant for automated parsing.
     *
     * @return A serialized expenditure
     */
    public String serialize() {
        return escapeDataString(description)
                + CONTROL_DELIMITER
                + escapeDataString(Float.toString(amount));
    }

    /**
     * Converts the output of Expenditure.serialize into an Expenditure. This method will
     * not work properly if the description contains the CONTROL_DELIMITER used internally
     * by the function, and such cases may be considered incorrect formatting.
     *
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
            return new Expenditure(null, null, escapedDescription, amount, null);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Invalid format for expenditure amount");
        }
    }
}
