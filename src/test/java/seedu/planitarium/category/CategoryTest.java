package seedu.planitarium.category;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    private static final String LABEL_OTHERS = "Others";
    private static final String LABEL_FOOD_AND_DRINKS = "Food and Drinks";
    private static final String LABEL_HOME_AND_UTIL = "Home and Utilities";
    private static final String LABEL_HEALTH_AND_MEDICAL = "Health and Medical";
    private static final String LABEL_TRANSPORT = "Transportation";
    private static final String LABEL_ENTERTAINMENT = "Fun and Entertainment";

    @Test
    void getLabel_enumLabelExists_success() {
        final String output1 = Category.OTHERS.getLabel();
        final String output2 = Category.FOOD_AND_DRINKS.getLabel();
        final String output3 = Category.HOME_AND_UTIL.getLabel();
        final String output4 = Category.HEALTH_AND_MEDICAL.getLabel();
        final String output5 = Category.TRANSPORT.getLabel();
        final String output6 = Category.ENTERTAINMENT.getLabel();

        assertEquals(LABEL_OTHERS, output1);
        assertEquals(LABEL_FOOD_AND_DRINKS, output2);
        assertEquals(LABEL_HOME_AND_UTIL, output3);
        assertEquals(LABEL_HEALTH_AND_MEDICAL, output4);
        assertEquals(LABEL_TRANSPORT, output5);
        assertEquals(LABEL_ENTERTAINMENT, output6);
    }

    @Test
    void getAllLabels_isInSameOrder_success() {
        ArrayList<String> expectedLabelList = new ArrayList<>();
        expectedLabelList.add(LABEL_OTHERS);
        expectedLabelList.add(LABEL_FOOD_AND_DRINKS);
        expectedLabelList.add(LABEL_HOME_AND_UTIL);
        expectedLabelList.add(LABEL_HEALTH_AND_MEDICAL);
        expectedLabelList.add(LABEL_TRANSPORT);
        expectedLabelList.add(LABEL_ENTERTAINMENT);

        ArrayList<String> allLabels = Category.getAllLabels();
        assertEquals(expectedLabelList.size(), allLabels.size());
        for (int i = 0; i < allLabels.size(); i++) {
            assertEquals(expectedLabelList.get(i), allLabels.get(i));
        }
    }

    @Test
    void getLabelForIndex_isExpectedLabel_success() {
        final String output1 = Category.getLabelForIndex(0);
        final String output2 = Category.getLabelForIndex(1);
        final String output3 = Category.getLabelForIndex(2);
        final String output4 = Category.getLabelForIndex(3);
        final String output5 = Category.getLabelForIndex(4);
        final String output6 = Category.getLabelForIndex(5);

        assertEquals(LABEL_OTHERS, output1);
        assertEquals(LABEL_FOOD_AND_DRINKS, output2);
        assertEquals(LABEL_HOME_AND_UTIL, output3);
        assertEquals(LABEL_HEALTH_AND_MEDICAL, output4);
        assertEquals(LABEL_TRANSPORT, output5);
        assertEquals(LABEL_ENTERTAINMENT, output6);
    }

    @Test
    void values_isExpectedEnumList_success() {
        Category[] allEnums = Category.values();

        assertEquals("OTHERS", allEnums[0].name());
        assertEquals("FOOD_AND_DRINKS", allEnums[1].name());
        assertEquals("HOME_AND_UTIL", allEnums[2].name());
        assertEquals("HEALTH_AND_MEDICAL", allEnums[3].name());
        assertEquals("TRANSPORT", allEnums[4].name());
        assertEquals("ENTERTAINMENT", allEnums[5].name());
    }

    @Test
    void valueOf_isExpectedEnumName_success() {
        assertEquals(Category.OTHERS, Category.valueOf("OTHERS"));
        assertEquals(Category.FOOD_AND_DRINKS, Category.valueOf("FOOD_AND_DRINKS"));
        assertEquals(Category.HOME_AND_UTIL, Category.valueOf("HOME_AND_UTIL"));
        assertEquals(Category.HEALTH_AND_MEDICAL, Category.valueOf("HEALTH_AND_MEDICAL"));
        assertEquals(Category.TRANSPORT, Category.valueOf("TRANSPORT"));
        assertEquals(Category.ENTERTAINMENT, Category.valueOf("ENTERTAINMENT"));
    }
}