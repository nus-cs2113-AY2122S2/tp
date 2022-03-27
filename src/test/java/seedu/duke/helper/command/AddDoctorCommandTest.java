package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.DoctorList;
import seedu.duke.exception.DuplicateEntryException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.status.Status.ADD_DOCTOR_SUCCESS;

class AddDoctorCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S1234567A","John","23","M","Singapore","1999-01-01","Endocrinology"};
        DoctorList doctors = new DoctorList();
        AddDoctorCommand command = new AddDoctorCommand(parameterArray);
        try {
            assertEquals(ADD_DOCTOR_SUCCESS,command.execute(doctors));
        } catch (DuplicateEntryException e) {
            fail("Should give success status, not throw exception");
        }
    }

    @Test
    void executeTestDuplicate() {
        String[] parameterArray = {"S1234567A","John","23","M","Singapore","1999-01-01","Endocrinology"};
        DoctorList doctors = new DoctorList();
        AddDoctorCommand command = new AddDoctorCommand(parameterArray);
        try {
            command.execute(doctors);
            command.execute(doctors);
            fail("Should throw Duplicate Entry exception");
        } catch (Exception e) {
            assert true;
        }
    }
}