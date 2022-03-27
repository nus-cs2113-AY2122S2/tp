package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.DuplicateEntryException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.status.Status.ADD_MEDICINE_SUCCESS;
import static seedu.duke.status.Status.ADD_PATIENT_SUCCESS;

class AddPatientCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S1234567A","John","23","M","Singapore","1999-01-01","2022-02-02"};
        PatientList patients = new PatientList();
        AddPatientCommand command = new AddPatientCommand(parameterArray);
        try {
            assertEquals(ADD_PATIENT_SUCCESS,command.execute(patients));
        } catch (DuplicateEntryException e) {
            fail("Should give success status, not throw exception");
        }
    }

    @Test
    void executeTestDuplicate() {
        String[] parameterArray = {"S1234567A","John","23","M","Singapore","1999-01-01","2022-02-02"};
        PatientList patients = new PatientList();
        AddPatientCommand command = new AddPatientCommand(parameterArray);
        try {
            command.execute(patients);
            command.execute(patients);
            fail("Should be throwing Duplicate Entry Exception");
        } catch (Exception e) {
            assert true;
        }
    }
}