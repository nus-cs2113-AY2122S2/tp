package seedu.planitarium.income;

import org.junit.jupiter.api.Test;
import seedu.planitarium.expenditure.ExpenditureList;

import static org.junit.jupiter.api.Assertions.*;

class IncomeListTest {

    IncomeList test = new IncomeList();

    @Test
    void addIncome() {
    }

    @Test
    void getIncomeValue_expectIncome_success() {
        double inputValue = 1000.00;
        test.addIncome("Salary", inputValue);
        double getInputValue = test.getIncomeValue(1);
        assertEquals(getInputValue, inputValue);
    }

    @Test
    void remove() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void getNumberOfIncomes() {
    }

    @Test
    void getTotalIncome() {
    }

    @Test
    void printIncomeList() {
    }
}