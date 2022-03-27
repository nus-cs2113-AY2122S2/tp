package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

import static junit.framework.TestCase.assertEquals;

public class ViewAppointmentCommandTest {
    @Test
    void testExecute() {
        List appointmentList = new AppointmentList();
        String [] testParameterArray = null;
        ViewAppointmentCommand testCommand = new ViewAppointmentCommand(testParameterArray);
        Status returnStatus = null;
        try {
            returnStatus = testCommand.execute(appointmentList);
        } catch (NotFoundException e) {
            e.toString();
        }
        Status expectedStatus = Status.FIND_APPOINTMENT_SUCCESS;
        assertEquals(expectedStatus,returnStatus);
    }
}
