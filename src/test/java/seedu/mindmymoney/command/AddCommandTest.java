package seedu.mindmymoney.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.Indexes;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(capturedOut));
    }

    /**
     * Asserts if user is able to add an input.
     */
    @Test
    void addCommand_oneInput_expectListUpdated() {
        String inputString = "expenditure 12345";
        new AddCommand(inputString).executeCommand();
        ArrayList<Expenditure> testList = new ArrayList<>();
        testList.add(new Expenditure("expenditure", 12345));
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(Lists.expenditures);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
        Lists.expenditures.clear();
    }

    /**
     * Asserts if user is able to add am empty input.
     */
    @Test
    void addCommand_missingInput_expectOriginalList() {
        String inputString = "";
        ArrayList<Expenditure> testList = new ArrayList<>();
        new AddCommand(inputString).executeCommand();
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(Lists.expenditures);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
        Lists.expenditures.clear();
    }

    /**
     * Asserts if user is able to add a non integer amount.
     */
    @Test
    void addCommand_nonIntAmount_expectOriginalList() {
        String inputString = "expenditure deadbeef";
        ArrayList<Expenditure> testList = new ArrayList<>();
        new AddCommand(inputString).executeCommand();
        String expectedOutput = getOutput(testList);
        String actualOutput = getOutput(Lists.expenditures);
        assertEquals(expectedOutput, actualOutput);
        testList.clear();
        Lists.expenditures.clear();
    }

    /**
     * Forms a string from the description and the amount of the last element of the list.
     *
     * @param list is the Arraylist to form the string from
     * @return description + amount if list is not empty, else it returns an empty string
     */
    public String getOutput(ArrayList<Expenditure> list) {
        if (!list.isEmpty()) {
            return list.get(list.size() + Indexes.LIST_INDEX_CORRECTION).getDescription()
                    + list.get(list.size() + Indexes.LIST_INDEX_CORRECTION).getAmount();
        }
        return "";
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdout);
    }
}
