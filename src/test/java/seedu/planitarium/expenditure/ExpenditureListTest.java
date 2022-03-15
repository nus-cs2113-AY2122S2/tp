package seedu.planitarium.expenditure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenditureListTest {

    @Test
    void getDescription_index_expectDescription() {
        ExpenditureList personOne = new ExpenditureList();
        String inputString = "Food";
        personOne.addExpenditure(inputString, 23);
        String getDescriptionString = personOne.getDescription(1);
        assertEquals(getDescriptionString, inputString);

    }
}