package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_INDEX;
import static seedu.meetingjio.common.ErrorMessages.ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;


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
        AddUserCommand addUserTwo = new AddUserCommand("peter");
        addUserOne.execute(masterTimetable);
        addUserTwo.execute(masterTimetable);
        AddLessonCommand addLessonCommandOne = new AddLessonCommand(
                "John", "cs2113", "monday",
                1200, 1300, "online"
        );
        AddLessonCommand addLessonCommandTwo = new AddLessonCommand(
                "John", "cs2113", "friday",
                1300, 1400, "online"
        );
        addLessonCommandOne.execute(masterTimetable);
        addLessonCommandTwo.execute(masterTimetable);

        AddLessonCommand addLessonCommandThree = new AddLessonCommand(
                "Peter", "cs2102", "saturday",
                1200, 1300, "online"
        );
        AddLessonCommand addLessonCommandFour = new AddLessonCommand(
                "Peter", "cs2102", "wednesday",
                1200, 1300, "online"
        );
        addLessonCommandThree.execute(masterTimetable);
        addLessonCommandFour.execute(masterTimetable);

        AddMeetingCommand addMeetingCommandOne = new AddMeetingCommand("meeting", "thursday",
                1230, 1330, "online"
        );
        addMeetingCommandOne.execute(masterTimetable);

        String listAllOutput = "john\n"
               + "1.[L] TITLE: cs2113\t\tDAY: monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n"
               + "2.[M] TITLE: meeting\t\tDAY: thursday\t\tSTART: 1230\t\tEND: 1330\t\tMODE: online\n"
               + "3.[L] TITLE: cs2113\t\tDAY: friday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online\n"
               + "peter\n"
               + "1.[L] TITLE: cs2102\t\tDAY: wednesday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n"
               + "2.[M] TITLE: meeting\t\tDAY: thursday\t\tSTART: 1230\t\tEND: 1330\t\tMODE: online\n"
               + "3.[L] TITLE: cs2102\t\tDAY: saturday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";
        ListCommand listCommand = new ListCommand("all",0);
        assertEquals(listAllOutput,listCommand.execute(masterTimetable));
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
        assertEquals(ERROR_INVALID_INDEX, deleteCommand.execute(masterTimetable));
        assertEquals(ERROR_INVALID_INDEX, deleteCommandOutOfRange.execute(masterTimetable));
    }

    /**
     * Test method to see if code catches instance of index out of bounds and if it throws exception.
     *
     */
    @Test
    public void deleteCommand_ValidUserInvalidIndex_throwException() {
        //delete index that is not found in list
        DeleteCommand deleteCommand = new DeleteCommand("john",100);
        deleteCommand.execute(masterTimetable);
        assertEquals(ERROR_INVALID_INDEX, deleteCommand.execute(masterTimetable));
    }


    /*
    @Test
    public void deleteCommand_deleteMeetingFromEveryone_throwException() {
        //delete meeting which is id 2 from john, should delete from everyone
        String listAllOutput = "john\n" +
                "1.[L] TITLE: cs2113\t\tDAY: monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n" +
                "2.[M] TITLE: meeting\t\tDAY: thursday\t\tSTART: 1230\t\tEND: 1330\t\tMODE: online\n" +
                "3.[L] TITLE: cs2113\t\tDAY: friday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online\n" +
                "peter\n" +
                "1.[L] TITLE: cs2102\t\tDAY: wednesday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n" +
                "2.[M] TITLE: meeting\t\tDAY: thursday\t\tSTART: 1230\t\tEND: 1330\t\tMODE: online\n" +
                "3.[L] TITLE: cs2102\t\tDAY: saturday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";
        ListCommand listCommand = new ListCommand("all",0);
        assertEquals(listAllOutput,listCommand.execute(masterTimetable));

        DeleteCommand deleteCommand = new DeleteCommand("john",2);
        deleteCommand.execute(masterTimetable);
        String deleteConfirmation = "The following meeting event has been deleted from everyone's timetable:\n" +
                "[M] TITLE: meeting\t\tDAY: thursday\t\tSTART: 1230\t\tEND: 1330\t\tMODE: online";
        assertEquals(deleteConfirmation, deleteCommand.execute(masterTimetable));

        String listAllPostDelete = "john\n" +
                "1.[L] TITLE: cs2113\t\tDAY: monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n" +
                "2.[L] TITLE: cs2113\t\tDAY: friday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online\n" +
                "peter\n" +
                "1.[L] TITLE: cs2102\t\tDAY: wednesday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n" +
                "2.[L] TITLE: cs2102\t\tDAY: saturday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";

        ListCommand listCommandOutput = new ListCommand("all",0);
        assertEquals(listAllPostDelete, listCommandOutput.execute(masterTimetable));

    }
    */
}
