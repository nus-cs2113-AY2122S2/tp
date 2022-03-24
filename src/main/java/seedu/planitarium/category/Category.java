package seedu.planitarium.category;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    OTHERS("Others"),
    FOOD_AND_DRINKS("Food and Drinks"),
    HOME_AND_UTIL("Home and Utilities"),
    HEALTH_AND_MEDICAL("Health and Medical"),
    TRANSPORT("Transportation"),
    ENTERTAINMENT("Fun and Entertainment");

    private static final ArrayList<String> LABELS = new ArrayList<>(Stream
            .of(Category.values())
            .map(Category::getLabel)
            .collect(Collectors.toList())
    );
    private static final int NumOfCategories = LABELS.size();
    private final String label;

    /**
     * Constructor for obtaining the enum category's string label.
     *
     * @param label The string label.
     */
    Category(String label) {
        this.label = label;
    }

    /**
     * Returns the string label for an enum category.
     *
     * @return A string label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns all the string labels for every enum category.
     *
     * @return An array list of all string labels.
     */
    public static ArrayList<String> getAllLabels() {
        return LABELS;
    }

    /**
     * Returns a string label for an enum category that is associated with the index based on its position.
     *
     * @param index The index that is associated with the enum category.
     * @return The string label for this enum category.
     */
    public static String getLabelForIndex(int index) {
        return LABELS.get(index);
    }

    /**
     * Returns the number of categories present in the enum.
     *
     * @return The number of categories.
     */
    public static int getNumOfCategories() {
        return NumOfCategories;
    }
}
