package seedu.meetingjio.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meetingjio.timetables.MasterTimetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    private MasterTimetable masterTimetable;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private Command addCommand;
    private Command addCommandSameUser;
    private Command addCommandDifferentUser;

    /**
     * Set up a Master Timetable object and multiple Add Commands to populate the timetables in the
     * MasterTimetable with different users.
     */
    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();

        answer_1 = "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online\n"
                + "2.[L] TITLE: CS2102\t\tDAY: Monday\t\tSTART: 1300\t\tEND: 1400\t\tMODE: online";

        answer_2 = "1.[L] TITLE: CS2113\t\tDAY: Monday\t\tSTART: 1200\t\tEND: 1300\t\tMODE: online";

        answer_3 = "John\n"
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

        addCommand.execute(masterTimetable);
        addCommandSameUser.execute(masterTimetable);
        addCommandDifferentUser.execute(masterTimetable);

    }


    /**
     * Test method to ensure that the program only lists the timetable of a specific user
     * instead of listing everyone's timetables.
     */
    @Test
    public void listCommand_specificUser() {
        ListCommand listCommand1 = new ListCommand("John");
        assertEquals(answer_1, listCommand1.execute(masterTimetable));

        ListCommand listCommand2 = new ListCommand("Peter");
        assertEquals(answer_2, listCommand2.execute(masterTimetable));
    }

    /**
     * Test method to ensure that the program lists out everyone's timetable when the user
     * inputs 'list all'.
     */
    @Test
    public void listCommand_allUsers() {
        ListCommand listCommand3 = new ListCommand("all");
        assertEquals(answer_3, listCommand3.execute(masterTimetable));
    }

}
