package seedu.mindmymoney.userfinancial;

/**
 * Represents user's expenditure.
 */
public class Expenditure {
    private String description;
    private int amount;
    private String category;
    public Expenditure(String description, String category, int amount) {
        setDescription(description);
        setAmount(amount);
        setCategory(category);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
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
        System.out.println(category);
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        if (getCategory() == null){
            return getDescription() + " of $" + getAmount();
        }
        return getDescription() + " of $" + getAmount() + " from " + getCategory();
    }
}
