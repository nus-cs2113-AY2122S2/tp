package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.PropertyList;
import seedu.mindmymoney.helper.ValidatorFunctions;


import java.util.Objects;

/**
 * Represents the expenditure entry.
 */
public class Expenditure implements MindMyMoneySerializable {
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


    /**
     * Returns a String representation of this expenditure, in a machine-readable format.
     * @return The serialized Expenditure.
     */
    public String serialize() {
        PropertyList plist = new PropertyList();
        plist.addProperty("description", description);
        plist.addProperty("category", category);
        plist.addProperty("paymentMethod", paymentMethod);
        plist.addProperty("time", time);
        plist.addProperty("amount", Float.toString(amount));
        return plist.serialize();
    }

    /**
     * Converts the output of Expenditure#serialize back into an Expenditure.
     * @param serialized The serialized Expenditure
     * @return An Expenditure.
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static Expenditure deserialize(String serialized) throws MindMyMoneyException {
        PropertyList plist = PropertyList.deserialize(serialized);
        try {
            String category = plist.getValue("category");
            ValidatorFunctions.validateExpenditureCategory(category);
            float amount = Float.parseFloat(plist.getValue("amount"));
            ValidatorFunctions.validateInRange(amount, 0, Float.POSITIVE_INFINITY, "amount");
            String time = plist.getValue("time");
            ValidatorFunctions.validateDate(time);
            return new Expenditure(plist.getValue("paymentMethod"),
                    category,
                    plist.getValue("description"),
                    amount,
                    time);
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Invalid number for amount during deserialization of " + serialized);
        } catch (ValidationException e) {
            throw e;
        } catch (MindMyMoneyException e) {
            String missingProperty = e.getMessage();
            throw new MindMyMoneyException("Line [" + serialized + "] does not contain required value "
                    + missingProperty);
        }
    }
}
