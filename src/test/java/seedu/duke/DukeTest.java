package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DukeTest {

    @Test
    public void testCommandChecks() {
        // test for bye command
        assertEquals(true, Duke.isExitCommand("exit"));
        assertEquals(false, Duke.isExitCommand("wrong"));
        assertEquals(false, Duke.isExitCommand(""));

        // test for navigation to Contacts Manager
        assertEquals(true, Duke.isContactsManagerCommand("goto m/Contacts_Manager"));
        assertEquals(false, Duke.isContactsManagerCommand("wrong"));
        assertEquals(false, Duke.isContactsManagerCommand(""));

        // test for navigation to Study Manager
        assertEquals(true, Duke.isStudyManagerCommand("goto m/Study_Manager"));
        assertEquals(false, Duke.isStudyManagerCommand("wrong"));
        assertEquals(false, Duke.isStudyManagerCommand(""));

        // test for navigation to Expense Tracker
        assertEquals(true, Duke.isExpenseTrackerCommand("goto m/Expense_Tracker"));
        assertEquals(false, Duke.isExpenseTrackerCommand("wrong"));
        assertEquals(false, Duke.isExpenseTrackerCommand(""));

        // test for help command
        assertEquals(true, Duke.isHelpCommand("help"));
        assertEquals(false, Duke.isHelpCommand("wrong"));
        assertEquals(false, Duke.isHelpCommand(""));

        // test for empty command
        assertEquals(true, Duke.isNotEmpty("test"));
        assertEquals(false, Duke.isNotEmpty(""));
    }
}
