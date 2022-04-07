//@@author yanjie1017

package seedu.meetingjio.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.meetingjio.timetables.MasterTimetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_USER;

class AddUserCommandTest {
    MasterTimetable masterTimetable;
    Command addUserJohn;

    @BeforeEach
    public void setUp() {
        masterTimetable = new MasterTimetable();
        addUserJohn = new AddUserCommand("john");
    }

    @Test
    public void addUserCommand_duplicateUser_throwException() {
        addUserJohn.execute(masterTimetable);
        assertEquals(ERROR_DUPLICATE_USER, addUserJohn.execute(masterTimetable));
    }

}