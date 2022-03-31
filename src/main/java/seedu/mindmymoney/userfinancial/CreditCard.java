package seedu.mindmymoney.userfinancial;


import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.helper.PropertyList;

import static seedu.mindmymoney.constants.Calculations.FLOAT_TO_PERCENTAGE;
import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;


/**
 * Represents the credit card entry.
 */
public class CreditCard implements MindMyMoneySerializable {
    private float monthlyCardLimit;
    private double cashback;
    private String nameOfCard;
    private float balance;
    private float totalExpenditure = 0;

    public CreditCard(String nameOfCard, double cashback, float monthlyCardLimit, float balance) {
        setNameOfCard(nameOfCard);
        setCashback(cashback);
        setMonthlyCardLimit(monthlyCardLimit);
        setBalance(balance);
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

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
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
        return "Name: " + getNameOfCard() + " [Cashback: " + getCashback() +  "%] [Cashback gained: $"
                + getTotalCashback() + "] [Card limit: $" + getMonthlyCardLimit() + "] [Card balance: $" + getBalance()
                + "]\n";
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
        plist.addProperty("balance", Float.toString(balance));
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
            CreditCard cc = new CreditCard(plist.getValue("nameOfCard"),
                    Double.parseDouble(plist.getValue("cashback")),
                    Float.parseFloat(plist.getValue("monthlyCardLimit")),
                    Float.parseFloat(plist.getValue("balance")));
            cc.totalExpenditure = Float.parseFloat(plist.getValue("totalExpenditure"));
            return cc;
        } catch (NumberFormatException e) {
            throw new MindMyMoneyException("Invalid number during deserialization of " + serialized);
        }
    }
}
