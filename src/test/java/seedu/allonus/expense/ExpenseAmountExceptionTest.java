package seedu.allonus.expense;

import org.junit.jupiter.api.Test;
import seedu.allonus.expense.exceptions.ExpenseAmountException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseAmountExceptionTest {
    @Test
    void testExpenseAmountException() {
        Exception e = new ExpenseAmountException("Test Expense Amount Exception");
        assertEquals("Test Expense Amount Exception", e.getMessage());
    }
}