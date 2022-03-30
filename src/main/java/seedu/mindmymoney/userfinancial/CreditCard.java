package seedu.mindmymoney.userfinancial;

import seedu.mindmymoney.MindMyMoneyException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the credit card entry.
 */
public class CreditCard {
    private float monthlyCardLimit;
    private double cashback;
    private String nameOfCard;
    private float balance;

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

    @Override
    public String toString() {
        return "Name: " + getNameOfCard() + " [Cashback: " + getCashback()
                +  "%] [Card limit: $" + getMonthlyCardLimit() + "] [Card balance: $" + getBalance() + "]\n";
    }

    /**
     * Returns the input for an add command that recreates this CreditCard.
     *
     * @return A serialized CreditCard
     */
    public String getAddCommand() throws MindMyMoneyException {
        return String.format("add /cc /n %s /cb %f /cl %f "
                + "/bal %f\n", nameOfCard, cashback, monthlyCardLimit, balance);
    }
}
