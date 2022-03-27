package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.meetingjio.common.ErrorMessages.*;

public class ClearCommandTest {

    private MasterTimetable masterTimetable;
    private String ListSuccessPostClearJohn;
    private String successClearJohn;
    private String successClearAll;
    private String ListSuccessPostClearAll;
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
        ListSuccessPostClearAll = "John\n" +
                "There are no lessons in your timetable yet!\n" +
                "Peter\n" +
                "There are no lessons in your timetable yet!";
        successClearJohn = "john's timetable has been cleared";
        ListSuccessPostClearJohn = "John\n" +
                "There are no lessons in your timetable yet!\n" +
                "Peter\n" +
                "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";
    }

    @Test
    public void clearCommandNoValue() {
        ClearCommand clearCommand = new ClearCommand("");
        assertEquals(ERROR_UNSPECIFIED_LIST_CLEAR,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommandClearAllEmpty() {
        ClearCommand clearCommand = new ClearCommand("all");
        assertEquals(ERROR_UNSPECIFIED_LIST_CLEAR,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommandUserNotSpecified() {
        ClearCommand clearCommand = new ClearCommand("clear");
        assertEquals(ERROR_TIMETABLE_NOT_FOUND_TO_DELETE,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommandUnknownUser() {
        ClearCommand clearCommand = new ClearCommand("clear ibrahim");
        assertEquals(ERROR_TIMETABLE_NOT_FOUND_TO_DELETE,  clearCommand.execute(masterTimetable));
    }

    @Test
    public void clearCommandOnUserWorksProperlyAndList() {
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);
        ClearCommand clearCommand = new ClearCommand("john");
        assertEquals(successClearJohn,  clearCommand.execute(masterTimetable));
        ListCommand listCommand = new ListCommand("all");
        assertEquals(ListSuccessPostClearJohn, listCommand.execute(masterTimetable));

    }

    @Test
    public void clearCommandClearAllWorksProperlyAndList() {
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);
        ClearCommand clearCommand = new ClearCommand("all");
        assertEquals(successClearAll,  clearCommand.execute(masterTimetable));
        ListCommand listCommand = new ListCommand("all");
        assertEquals(ListSuccessPostClearAll, listCommand.execute(masterTimetable));
    }
}
