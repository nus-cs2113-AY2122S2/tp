//@@author OzairHasan

package seedu.allonus;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AllOnUsTest {

    @Test
    public void testCommandChecks() {
        // test for bye command
        assertEquals(true, AllOnUs.isExitCommand("exit"));
        assertEquals(false, AllOnUs.isExitCommand("wrong"));
        assertEquals(false, AllOnUs.isExitCommand(""));

        // test for navigation to Contacts Manager
        assertEquals(true, AllOnUs.isContactsManagerCommand("goto m/Contacts_Manager"));
        assertEquals(false, AllOnUs.isContactsManagerCommand("wrong"));
        assertEquals(false, AllOnUs.isContactsManagerCommand(""));

        // test for navigation to Study Manager
        assertEquals(true, AllOnUs.isStudyManagerCommand("goto m/Study_Manager"));
        assertEquals(false, AllOnUs.isStudyManagerCommand("wrong"));
        assertEquals(false, AllOnUs.isStudyManagerCommand(""));

        // test for navigation to Expense Tracker
        assertEquals(true, AllOnUs.isExpenseTrackerCommand("goto m/Expense_Tracker"));
        assertEquals(false, AllOnUs.isExpenseTrackerCommand("wrong"));
        assertEquals(false, AllOnUs.isExpenseTrackerCommand(""));

        // test for help command
        assertEquals(true, AllOnUs.isHelpCommand("help"));
        assertEquals(false, AllOnUs.isHelpCommand("wrong"));
        assertEquals(false, AllOnUs.isHelpCommand(""));

        // test for empty command
        assertEquals(true, AllOnUs.isNotEmpty("test"));
        assertEquals(false, AllOnUs.isNotEmpty(""));
    }
}
//@@author
