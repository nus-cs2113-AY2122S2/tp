package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        return description.equals(expenditure.description) && (amount == expenditure.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount);
    }


    /**
     * Returns the input for an add command that recreates this Expenditure.
     *
     * @return A serialized expenditure
     */
    public String getAddCommand() throws MindMyMoneyException {
        SimpleDateFormat parser = new SimpleDateFormat("MMM yyyy");
        Date date;
        try {
            date = parser.parse(time);
        } catch (ParseException pe) {
            throw new MindMyMoneyException("Error occurred when serializing date " + time);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String formattedDate = formatter.format(date);
        return String.format("add /pm %s /c %s /d %s /a %f /t %s\n",
                paymentMethod, category, description, amount, formattedDate);
    }
}
