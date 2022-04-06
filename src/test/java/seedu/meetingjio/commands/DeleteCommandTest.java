package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.meetingjio.common.ErrorMessages.*;

public class DeleteCommandTest {

    MasterTimetable masterTimetable = new MasterTimetable();

    /**
     * Set up a timetable object to set up and test commands.
     *
     */
    @BeforeEach
    void setUp() {
        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddUserCommand addUserOne = new AddUserCommand("john");
        addUserOne.execute(masterTimetable);
        AddLessonCommand addLessonCommandOne = new AddLessonCommand(
                "John", "CS2113", "Monday",
                1200, 1300, "online"
        );
        AddLessonCommand addLessonCommandTwo = new AddLessonCommand(
                "John", "CS2113", "Friday",
                1200, 1300, "online"
        );
        AddMeetingCommand addMeetingCommandOne = new AddMeetingCommand("meeting", "Thursday",
                1230, 1330, "online"
        );
        AddMeetingCommand addMeetingCommandTwo = new AddMeetingCommand("meeting", "Thursday",
                1230, 1330, "online"
        );
    }

    /**
     * Test method to see if code catches instance of index out of bounds and if it throws exception.
     *
     */
    @Test
    public void deleteCommand_InvalidUserToDeleteFrom_throwException() {
        DeleteCommand deleteCommand = new DeleteCommand("ibrahim",0);
        deleteCommand.execute(masterTimetable);
        assertEquals(ERROR_TIMETABLE_NOT_FOUND_TO_DELETE, deleteCommand.execute(masterTimetable));
    }

    /**
     * Test method to see if code catches instance of index out of bounds and if it throws exception.
     *
     */
    @Test
    public void deleteCommand_ValidUserIndexOutOfBoundsSize_throwException() {
        //delete index that is not found in list
        DeleteCommand deleteCommand = new DeleteCommand("john",-1);
        deleteCommand.execute(masterTimetable);
        DeleteCommand deleteCommandOutOfRange = new DeleteCommand("john",100);
        deleteCommandOutOfRange.execute(masterTimetable);
        assertEquals(ERROR_INDEX_OUT_OF_BOUND, deleteCommand.execute(masterTimetable));
        assertEquals(ERROR_INDEX_OUT_OF_BOUND, deleteCommandOutOfRange.execute(masterTimetable));
    }

    /**
     * Test method to see if code catches instance of index out of bounds and if it throws exception.
     *
     */
    @Test
    public void deleteCommand_ValidUserInvalidIndex_throwException() {
        //delete index that is not found in list
        ClearCommand clearCommand = new ClearCommand("all");
        clearCommand.execute(masterTimetable);
        AddUserCommand addUserOne = new AddUserCommand("john");
        addUserOne.execute(masterTimetable);
        DeleteCommand deleteCommand = new DeleteCommand("john",3);
        deleteCommand.execute(masterTimetable);
        assertEquals(ERROR_INDEX_OUT_OF_BOUND, deleteCommand.execute(masterTimetable));
    }

}
