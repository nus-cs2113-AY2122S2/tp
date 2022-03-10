package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.data.Lists;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void addCommand_oneInput_expectListUpdated() {
        String inputString = "expenditure 12345";
        ArrayList<Expenditure> testList = Lists.expenditures;
        new AddCommand(inputString);
        testList.add(new Expenditure("expenditure", 12345));
        assertEquals(Lists.expenditures, testList);
    }

    @Test
    void addCommand_missingInput_expectOriginalList() {
        String inputString = "";
        ArrayList<Expenditure> testList = Lists.expenditures;
        new AddCommand(inputString);
        assertEquals(Lists.expenditures, testList);
    }

    @Test
    void addCommand_nonIntAmount_expectOriginalList() {
        String inputString = "expenditure deadbeef";
        ArrayList<Expenditure> testList = Lists.expenditures;
        new AddCommand(inputString);
        assertEquals(Lists.expenditures, testList);
    }

}