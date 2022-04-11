//@@author ibrahimisramos

package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.meetingjio.common.ErrorMessages.ERROR_UNSPECIFIED_LIST_CLEAR;
import static seedu.meetingjio.common.ErrorMessages.ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;


public class ClearCommandTest {

    private MasterTimetable masterTimetable;
    private String listSuccessPostClearJohn;
    private String successClearJohn;
    private String successClearAll;
    private String listSuccessPostClearAll;
    private Command addCommand;
    private Command addCommandSameUser;
    private Command addCommandDifferentUser;

    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();

        addCommand = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );

        addCommandSameUser = new AddLessonCommand(
                "John", "CS2102", "Monday",
                1300, 1400, "online"
        );

        addCommandDifferentUser = new AddLessonCommand(
                "Peter", "CS2113", "Monday",
                1200, 1300, "online"
        );
        successClearAll = "All records of everyone's timetable has been cleared";
        listSuccessPostClearAll = "The Master Timetable has no populated timetables!";
        successClearJohn = "john's timetable has been cleared";
        listSuccessPostClearJohn = "Peter\n"
                + "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";
    }

    @Test
    public void clearCommand_noValue_throwException() {
        ClearCommand clearCommand = new ClearCommand("");
        assertEquals(ERROR_UNSPECIFIED_LIST_CLEAR, clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommand_clearAllEmpty_throwException() {
        ClearCommand clearCommand = new ClearCommand("all");
        assertEquals(successClearAll,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommand_userNotSpecified_throwException() {
        ClearCommand clearCommand = new ClearCommand("clear");
        assertEquals(ERROR_TIMETABLE_NOT_FOUND_TO_DELETE,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommand_unknownUser_throwException() {
        ClearCommand clearCommand = new ClearCommand("ibrahim");
        assertEquals(ERROR_TIMETABLE_NOT_FOUND_TO_DELETE,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommand_clearAllWorksProperlyAndList() {
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);
        ClearCommand clearCommand = new ClearCommand("all");
        assertEquals(successClearAll,  clearCommand.execute(masterTimetable));
        ListCommand listCommand = new ListCommand("all", 0);
        assertEquals(listSuccessPostClearAll, listCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommand_testNullPointer_throwException() {
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);
        String test = null;
        ClearCommand clearCommand = new ClearCommand(test);
        assertThrows(NullPointerException.class,() -> {
            clearCommand.execute(masterTimetable);
        });
    }
}
