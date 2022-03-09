package seedu.planitarium;

public class Income {
    protected String description;
    protected double amount;

    public Income(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String toString() {
        return  "Income: " + description + "     Amount: $" + String.format("%.2f", amount);
    }
}
