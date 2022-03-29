package seedu.allonus.expense;

import org.junit.jupiter.api.Test;
import seedu.allonus.expense.exceptions.ExpenseEmptyFieldException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseEmptyFieldExceptionTest {
    @Test
    void testExpenseEmptyFieldException() {
        Exception e = new ExpenseEmptyFieldException("Test Expense Empty Field Exception");
        assertEquals("Test Expense Empty Field Exception", e.getMessage());
    }

}