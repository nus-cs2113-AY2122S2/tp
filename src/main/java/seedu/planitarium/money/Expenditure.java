//@@author tjiarong

package seedu.planitarium.money;

import seedu.planitarium.category.Category;

public class Expenditure extends Money {

    private String category;

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
        this.category = Category.getLabelForIndex(category);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category =  Category.getLabelForIndex(category);
    }

    public String saveString() {
        return "e " + description + " - "
                + amount + " - "
                + isPermanent + " - "
                + category;
    }
}
