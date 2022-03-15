package seedu.allonus.expense;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.allonus.expense.ExpenseParser.parseDeleteExpense;
import static seedu.allonus.expense.ExpenseParser.parseKeywordExpense;

class ExpenseParserTest {

    @Test
    void testParseKeyword() {
        String testInput = "d/13/3/2022 a/18.00 c/Movie r/This is a remark";
        String date = parseKeywordExpense(testInput, "d/", "[dacr]/");
        assertEquals(date, "13/3/2022");
        String amount = parseKeywordExpense(testInput, "a/", "[dacr]/");
        assertEquals(amount, "18.00");
        String category = parseKeywordExpense(testInput, "c/", "[dacr]/");
        assertEquals(category, "Movie");
        String remarks = parseKeywordExpense(testInput, "r/", "[dacr]/");
        assertEquals(remarks, "This is a remark");
    }

    @Test
    void testParseDelete() {
        String testInput = "rm 1";
        assertEquals(parseDeleteExpense(testInput), 1);
    }
}