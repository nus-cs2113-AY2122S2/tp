package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.ErrorMessages.ERROR_EMPTY_LIST;

public class ListCommandTest {

    Timetable timetable = new Timetable();

    @Test
    public void listCommand_emptyList_returnEmptyListNotification() {
        ListCommand listCommand = new ListCommand();
        assertEquals(ERROR_EMPTY_LIST, listCommand.execute(timetable));
    }

}
