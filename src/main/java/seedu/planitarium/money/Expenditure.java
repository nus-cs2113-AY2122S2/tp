//@@author tjiarong

package seedu.planitarium.money;

import seedu.planitarium.category.Category;

public class Expenditure extends Money {

    private Integer category;

    /**
     * Initialise a new Expenditure object.
     *
     * @param description Expenditure's description
     * @param amount      Expenditure's amount
     * @param isPermanent Boolean var to indicate if expenditure is recurring
     * @param category    Expenditure's category
     */
    public Expenditure(String description, Double amount, Integer category, Boolean isPermanent) {
        super(description, amount, isPermanent);
        this.category = category;
    }

    /**
     * Return the string label of the category from its integer attribute.
     *
     * @return Category label
     */
    public String getCategory() {
        return Category.getLabelForIndex(category);
    }

    /**
     * Set category attribute of expenditure object.
     *
     * @param category integer of the category label
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * Return object's string representation for storage purposes.
     *
     * @return Object attributes as string
     */
    public String getSaveString() {
        return "e " + description + " /d "
                + amount + " /d "
                + isPermanent + " /d "
                + category + " /d "
                + initDate;
    }

    /**
     * Returns the expenditure object string representation, which includes its
     * description, its amount in two decimal place, it's recurring status and its category.
     *
     * @return The object's string representation.
     */
    @Override
    public String toString() {
        return description + ": $" + String.format("%.2f", amount)
                + " - Recurring: " + isPermanent
                + " - Category: " + getCategory();
    }
}
