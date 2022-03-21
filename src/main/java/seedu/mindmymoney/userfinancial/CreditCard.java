package seedu.mindmymoney.userfinancial;

/**
 * Represents a user credit card
 */
public class CreditCard {
    private float monthlyCardLimit;
    private double cashback;
    private String nameOfCard;
    private float balance;

    public CreditCard (String nameOfCard, double cashback, float monthlyCardLimit, float balance) {
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
        return "Credit Card of name: " + getNameOfCard() + " has $" + getBalance()
                + " left. This card has a monthly limit of " + getMonthlyCardLimit() + " and a cashback at a rate of "
                + getCashback() + "%.";
    }
}
