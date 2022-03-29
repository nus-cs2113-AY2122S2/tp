package seedu.allonus.expense;

import org.junit.jupiter.api.Test;
import seedu.allonus.expense.exceptions.ExpenseMissingFieldException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseMissingFieldExceptionTest {
    @Test
    void testExpenseEmptyFieldException() {
        Exception e = new ExpenseMissingFieldException("Test Expense Missing Field Exception");
        assertEquals("Test Expense Missing Field Exception", e.getMessage());
    }
}