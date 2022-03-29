//@@author tjiarong

package seedu.planitarium.money;

import seedu.planitarium.category.Category;

public class Expenditure extends Money {

    private int category;

    /**
     * Initialise a new Expenditure object.
     *
     * @param description - Expenditure's description
     * @param amount      - Expenditure's amount
     * @param isPermanent - Boolean var to indicate if expenditure is recurring
     * @param category - Expenditure's category
     */
    public Expenditure(String description, double amount, int category, boolean isPermanent) {
        super(description, amount, isPermanent);
        this.category = category;
    }
    
    public String getCategory() {
        return Category.getLabelForIndex(category);
    }

    public void setCategory(int category) {
        this.category =  category;
    }

    public String saveString() {
        return "e " + description + " - "
                + amount + " - "
                + isPermanent + " - "
                + category;
    }

    @Override
    public String toString() {
        return  description + ": $" + String.format("%.2f", amount)
                + " - Recurring: " + isPermanent
                + " - Category: " + getCategory();
    }
}
