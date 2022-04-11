package seedu.allonus.expense;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExpenseTest {

    Expense test1 = new Expense("2022-03-24", "18.00", "Movie", "This is a movie");
    Expense test2 = new Expense("2022-03-25", "0", "Food", "This is a food");
    Expense test3 = new Expense("2022-03-26", "1", "Others", "This is an others");
    Expense test4 = new Expense("2022-03-27", "2", "School", "This is a school");

    @Test
    void getDate() {
        assertEquals("2022-03-24", test1.getDate());
        assertEquals("2022-03-25", test2.getDate());
        assertEquals("2022-03-26", test3.getDate());
        assertEquals("2022-03-27", test4.getDate());

        assertNotEquals("2022-03-25", test1.getDate());
        assertNotEquals("2022-03-26", test2.getDate());
        assertNotEquals("2022-03-27", test3.getDate());
        assertNotEquals("2022-03-24", test4.getDate());
    }

    @Test
    void getAmount() {
        assertEquals("18.00", test1.getAmount());
        assertEquals("0", test2.getAmount());
        assertEquals("1", test3.getAmount());
        assertEquals("2", test4.getAmount());

        assertNotEquals("0", test1.getAmount());
        assertNotEquals("1", test2.getAmount());
        assertNotEquals("2", test3.getAmount());
        assertNotEquals("18.00", test4.getAmount());
    }

    @Test
    void getCategory() {
        assertEquals("Movie", test1.getCategory());
        assertEquals("Food", test2.getCategory());
        assertEquals("Others", test3.getCategory());
        assertEquals("School", test4.getCategory());

        assertNotEquals("Food", test1.getCategory());
        assertNotEquals("Others", test2.getCategory());
        assertNotEquals("School", test3.getCategory());
        assertNotEquals("Movie", test4.getCategory());
    }

    @Test
    void getRemark() {
        assertEquals("This is a movie", test1.getRemark());
        assertEquals("This is a food", test2.getRemark());
        assertEquals("This is an others", test3.getRemark());
        assertEquals("This is a school", test4.getRemark());

        assertNotEquals("This is a school", test1.getRemark());
        assertNotEquals("This is a movie", test2.getRemark());
        assertNotEquals("This is a food", test3.getRemark());
        assertNotEquals("This is an others", test4.getRemark());
    }

    @Test
    void setDate() {
        test1.setDate("1/1/2022");
        assertEquals("1/1/2022", test1.getDate());
    }

    @Test
    void setAmount() {
        test2.setAmount("500");
        assertEquals("500", test2.getAmount());
    }

    @Test
    void setCategory() {
        test3.setCategory("TestCategory");
        assertEquals("TestCategory", test3.getCategory());
    }

    @Test
    void setRemark() {
        test4.setRemark("Test Remark");
        assertEquals("Test Remark", test4.getRemark());
    }

    @Test
    void testToString() {
        
        assertEquals("2022-03-24 | $18.00 | Movie | This is a movie",
                test1.toString());
    }

    @Test
    void setNoOfItems() {
        Expense.setNoOfItems(1);
        assertEquals(1, Expense.getNoOfItems());
    }

    @Test
    void getNoOfItems() {
        Expense.setNoOfItems(1);
        assertEquals(1, Expense.getNoOfItems());
    }
}