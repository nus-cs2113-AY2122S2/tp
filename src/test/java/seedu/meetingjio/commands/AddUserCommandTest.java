//@@author yanjie1017

package seedu.meetingjio.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_USER;

class AddUserCommandTest {
    private MasterTimetable masterTimetable;
    private static Command addUserJohn = new AddUserCommand("john");

    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();
    }

    @Test
    public void addUserCommand_newUser_throwException() {
        addUserJohn.execute(masterTimetable);
        assertEquals(ERROR_DUPLICATE_USER, addUserJohn.execute(masterTimetable));
    }

}