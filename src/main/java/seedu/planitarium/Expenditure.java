package seedu.planitarium;


public class Expenditure {

    protected String description;
    protected double amount;

    public Expenditure(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String toString() {
        return  "Item: " + description + "     Amount: $" + String.format("%.2f", amount);
    }

}
