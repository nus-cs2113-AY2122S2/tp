package seedu.mindmymoney.userfinancial;


import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.PropertyList;
import seedu.mindmymoney.helper.ValidatorFunctions;

import static seedu.mindmymoney.constants.CalculationConversion.FLOAT_TO_PERCENTAGE;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;


/**
 * Represents the credit card entry.
 */
public class CreditCard implements MindMyMoneySerializable {
    private float monthlyCardLimit;
    private double cashback;
    private String nameOfCard;
    private float totalExpenditure = 0;

    public CreditCard(String nameOfCard, double cashback, float monthlyCardLimit) {
        setNameOfCard(nameOfCard);
        setCashback(cashback);
        setMonthlyCardLimit(monthlyCardLimit);
    }

    public void setNameOfCard(String nameOfCard) {
        this.nameOfCard = nameOfCard;
    }

    public String getNameOfCard() {
        return nameOfCard;
    }

    public void setCashback(double cashback) {
        this.cashback = cashback;
    }

    public double getCashback() {
        return cashback;
    }

    public void setMonthlyCardLimit(float monthlyCardLimit) {
        this.monthlyCardLimit = monthlyCardLimit;
    }

    public float getMonthlyCardLimit() {
        return monthlyCardLimit;
    }

    public float getTotalExpenditure() {
        return totalExpenditure;
    }

    public float getBalanceLeft() {
        return monthlyCardLimit - totalExpenditure;
    }

    public void addExpenditure(float amount) {
        this.totalExpenditure += amount;
    }

    public void deductExpenditure(float amount) {
        this.totalExpenditure -= amount;
    }

    public float getTotalCashback() {
        return formatFloat((float)(totalExpenditure * (cashback * FLOAT_TO_PERCENTAGE)));
    }

    @Override
    public String toString() {
        return "Name: " + getNameOfCard() + " [Cashback: " + String.format("%.2f", getCashback())
                + "%] [Cashback gained: $" + String.format("%.2f", getTotalCashback())
                + "] [Card limit: $" + String.format("%.2f", getMonthlyCardLimit())
                + "] [Balance left: $" + String.format("%.2f", getBalanceLeft()) + "]\n";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard creditCard = (CreditCard) object;
        return nameOfCard.equals(creditCard.nameOfCard) && (cashback == creditCard.cashback)
                && (monthlyCardLimit == creditCard.monthlyCardLimit);
    }

    /**
     * Returns a String representation of this credit card, in a machine-readable format.
     * @return The serialized CreditCard.
     */
    public String serialize() {
        PropertyList plist = new PropertyList();
        plist.addProperty("monthlyCardLimit", Float.toString(monthlyCardLimit));
        plist.addProperty("cashback", Double.toString(cashback));
        plist.addProperty("nameOfCard", nameOfCard);
        plist.addProperty("totalExpenditure", Float.toString(totalExpenditure));
        return plist.serialize();
    }

    /**
     * Converts the output of CreditCard#serialize back into a CreditCard.
     * @param serialized The serialized CreditCard
     * @return A CreditCard.
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static CreditCard deserialize(String serialized) throws MindMyMoneyException {
        PropertyList plist = PropertyList.deserialize(serialized);
        try {
            double cashback = Double.parseDouble(plist.getValue("cashback"));
            double monthlyCardLimit = Double.parseDouble(plist.getValue("monthlyCardLimit"));
            double totalExpenditure = Double.parseDouble(plist.getValue("totalExpenditure"));
            ValidatorFunctions.validateInRange(cashback, 0, 100, "cashback");
            ValidatorFunctions.validateInRange(monthlyCardLimit, 0, 40000, "monthly limit");
            ValidatorFunctions.validateInRange(totalExpenditure, 0, monthlyCardLimit, "total expenditures");
            CreditCard cc = new CreditCard(plist.getValue("nameOfCard"),
                    cashback,
                    (float) monthlyCardLimit);
            cc.totalExpenditure = (float) totalExpenditure;
            return cc;
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Invalid number during deserialization of " + serialized);
        } catch (ValidationException e) {
            throw e;
        } catch (MindMyMoneyException e) {
            String missingProperty = e.getMessage();
            throw new MindMyMoneyException("Line [" + serialized + "] does not contain required value "
                + missingProperty);
        }
    }
}
