package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mindmymoney.constants.Indexes.LIST_INDEX_CORRECTION;

class AddCommandTest {
    /**
     * Asserts if user is able to add an input.
     */
    @Test
    void addCommand_oneInput_expectListUpdated() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        String inputString = "expenditure 12345";
        new AddCommand(inputString, expenditureTestList).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("expenditure", null, 12345));
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
    }

    /**
     * Asserts if user is able to add am empty input.
     */
    @Test
    void addCommand_missingInput_expectOriginalList() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        String inputString = "";
        ArrayList<Expenditure> testList = new ArrayList<>();
        new AddCommand(inputString, expenditureTestList).executeCommand();
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Asserts if user is able to add a non integer amount.
     */
    @Test
    void addCommand_nonIntAmount_expectOriginalList() {
        ExpenditureList expenditureTestList = new ExpenditureList();
        String inputString = "expenditure deadbeef";
        ArrayList<Expenditure> testList = new ArrayList<>();
        new AddCommand(inputString, expenditureTestList).executeCommand();
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(expenditureTestList.expenditureListArray);
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Forms a string from the description and the amount of the last element of the list.
     *
     * @param list is the Arraylist to form the string from
     * @return description + amount if list is not empty, else it returns an empty string
     */
    public String getOutput(ArrayList<Expenditure> list) {
        if (!list.isEmpty()) {
            return list.get(list.size() + LIST_INDEX_CORRECTION).getDescription()
                    + list.get(list.size() + LIST_INDEX_CORRECTION).getAmount();
        }
        return "";
    }
}
