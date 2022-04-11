//@@author angyongming

package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_MASTER_TIMETABLE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_UNSPECIFIED_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;

public class ListCommandTest {

    private MasterTimetable masterTimetable;
    private String answerJohn;
    private String answerPeter;
    private String answerAll;
    private String answerAllLesson;
    private String answerAllMeeting;
    private Command addCommand;
    private Command addCommandSameUser;
    private Command addCommandDifferentUser;
    private Command addMeetingCommand;
    private AddUserCommand addUserCommandJohn;
    private AddUserCommand addUserCommandPeter;

    /**
     * Set up a Master Timetable object and multiple Add Commands.
     * Prepare the expected output when list command is used after each Add Command.
     */
    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();

        answerJohn = "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n"
                + "2.[L] TITLE: CS2102\t\tDAY: Monday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online";

        answerPeter = "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";

        answerAll = "John\n"
                + "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n"
                + "2.[L] TITLE: CS2102\t\tDAY: Monday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online\n"
                + "Peter\n"
                + "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";

        answerAllLesson = "John\n"
                + "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n"
                + "2.[L] TITLE: CS2102\t\tDAY: Monday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online\n"
                + "Peter\n"
                + "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";

        answerAllMeeting = "John\n"
                + "3.[M] TITLE: Lunch\t\tDAY: Tuesday\t\tSTART: 1200\t\tEND: 1400\t\tMODE: physical\n"
                + "Peter\n"
                + "2.[M] TITLE: Lunch\t\tDAY: Tuesday\t\tSTART: 1200\t\tEND: 1400\t\tMODE: physical";

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

        addMeetingCommand = new AddMeetingCommand(
                "Lunch", "Tuesday",
                1200, 1400, "physical"
        );

        addUserCommandJohn = new AddUserCommand("John");
        addUserCommandPeter = new AddUserCommand("Peter");

    }

    /**
     * Test method to ensure that the programme continues running as expected when the user inputs
     * list all when the Master Timetable is empty.
     */
    @Test
    public void listCommand_emptyMasterTimetable_throwException() {
        ListCommand listCommand = new ListCommand("all", 0);
        assertEquals(ERROR_EMPTY_MASTER_TIMETABLE, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme continues running as expected when the user inputs
     * list without any input.
     */
    @Test
    public void listCommand_noValue_throwException() {
        ListCommand listCommand = new ListCommand("", 0);
        assertEquals(ERROR_UNSPECIFIED_LIST, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme continues running as expected when the user wishes
     * to list the timetable of a user that does not exist.
     */
    @Test
    public void listCommand_noUser_throwException() {
        ListCommand listCommand = new ListCommand("John", 0);
        assertEquals(ERROR_INVALID_USER, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program only lists the timetable of a specific user
     * instead of listing everyone's timetables.
     */
    @Test
    public void listCommand_specificUser() {
        addUserCommandJohn.execute(masterTimetable);
        addUserCommandPeter.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);

        ListCommand listCommand1 = new ListCommand("John", 0);
        assertEquals(answerJohn, listCommand1.execute(masterTimetable));

        ListCommand listCommand2 = new ListCommand("Peter",0);
        assertEquals(answerPeter, listCommand2.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program lists out everyone's timetable when the user
     * inputs 'list all'.
     */
    @Test
    public void listCommand_allUsers() {
        addUserCommandJohn.execute(masterTimetable);
        addUserCommandPeter.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);

        ListCommand listCommand = new ListCommand("all", 0);
        assertEquals(answerAll, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program lists the user's timetable such that events in the timetable are sorted
     * based on each event's day and time.
     */
    @Test
    public void listCommand_checkSort() {
        addUserCommandJohn.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommand.execute(masterTimetable);

        ListCommand listCommand = new ListCommand("John", 0);
        assertEquals(answerJohn, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program only lists lessons or meetings or both, depending on the user's input.
     */
    @Test
    public void listSpecificEvent() {
        addUserCommandJohn.execute(masterTimetable);
        addUserCommandPeter.execute(masterTimetable);
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);
        addMeetingCommand.execute(masterTimetable);

        ListCommand listCommand1 = new ListCommand("all", 1);
        assertEquals(answerAllLesson, listCommand1.execute(masterTimetable));

        ListCommand listCommand2 = new ListCommand("all",2);
        assertEquals(answerAllMeeting, listCommand2.execute(masterTimetable));
    }

}

