package seedu.mindmymoney.userfinancial;

import static seedu.mindmymoney.helper.GeneralFunctions.formatFloat;

/**
 * Represents the credit card entry.
 */
public class CreditCard {
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
        return formatFloat((float)(totalExpenditure * (cashback/100))) ;
    }

    @Override
    public String toString() {
        return "Name: " + getNameOfCard() + " [Cashback: " + getCashback() +  "%] [Cashback gained: $"
                + getTotalCashback() + "] [Card limit: $" + getMonthlyCardLimit() + "] [Card balance: $" + getBalance()
                + "]\n";
    }
}
