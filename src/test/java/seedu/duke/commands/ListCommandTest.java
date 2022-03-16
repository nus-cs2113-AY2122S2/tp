package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.common.ErrorMessages.ERROR_EMPTY_LIST;

public class ListCommandTest {

    Timetable timetable = new Timetable();

    /**
     * Test method to ensure that the program informs user and continues to run as intended
     * when the timetable is empty
     */
    @Test
    public void listCommand_emptyList_returnEmptyListNotification() {
        ListCommand listCommand = new ListCommand();
        assertEquals(ERROR_EMPTY_LIST, listCommand.execute(timetable));
    }

}
