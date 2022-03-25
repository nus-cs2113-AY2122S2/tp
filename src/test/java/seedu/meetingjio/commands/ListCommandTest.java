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
    private Command addCommand;
    private Command addCommandSameUser;
    private Command addCommandDifferentUser;

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

    }

    /**
     * Test method to ensure that the programme continues running as expected when the user inputs
     * list all when the Master Timetable is empty.
     */
    @Test
    public void listCommand_emptyMasterTimetable() {
        ListCommand listCommand = new ListCommand("all");
        assertEquals(ERROR_EMPTY_MASTER_TIMETABLE, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme continues running as expected when the user inputs
     * list without any input.
     */
    @Test
    public void listCommand_noValue() {
        ListCommand listCommand = new ListCommand("");
        assertEquals(ERROR_UNSPECIFIED_LIST, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the programme continues running as expected when the user wishes
     * to list the timetable of a user that does not exist.
     */
    @Test
    public void listCommand_noUser() {
        ListCommand listCommand = new ListCommand("John");
        assertEquals(ERROR_INVALID_USER, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program only lists the timetable of a specific user
     * instead of listing everyone's timetables.
     */
    @Test
    public void listCommand_specificUser() {
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);

        ListCommand listCommand1 = new ListCommand("John");
        assertEquals(answerJohn, listCommand1.execute(masterTimetable));

        ListCommand listCommand2 = new ListCommand("Peter");
        assertEquals(answerPeter, listCommand2.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program lists out everyone's timetable when the user
     * inputs 'list all'.
     */
    @Test
    public void listCommand_allUsers() {
        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);

        ListCommand listCommand = new ListCommand("all");
        assertEquals(answerAll, listCommand.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program lists the user's timetable such that events in the timetable are sorted
     * based on each event's day and time.
     */
    @Test
    public void listCommand_checkSort() {
        addCommandSameUser.execute(masterTimetable);
        addCommand.execute(masterTimetable);

        ListCommand listCommand = new ListCommand("John");
        assertEquals(answerJohn, listCommand.execute(masterTimetable));
    }

}
