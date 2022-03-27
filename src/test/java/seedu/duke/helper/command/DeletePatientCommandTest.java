package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.fail;


class DeletePatientCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S1234567A","John","23","M","Singapore","1999-01-01","2020-01-01"};
        String[] parameterArray2 = {"S1234567A"};
        PatientList patients = new PatientList();
        AddPatientCommand command = new AddPatientCommand(parameterArray);
        DeletePatientCommand commandInTest = new DeletePatientCommand(parameterArray2);
        try {
            command.execute(patients);
            commandInTest.execute(patients);
            assert true;
        } catch (DuplicateEntryException e) {
            fail("AddPatientCommand is not working correctly!");
        } catch (NotFoundException n) {
            fail("Shouldn't be throwing NotFoundException as patient exists!");
        }
    }

    @Test
    void executeTestNotFound() {
        String[] parameterArray2 = {"S1234567A"};
        PatientList patients = new PatientList();
        DeletePatientCommand commandInTest = new DeletePatientCommand(parameterArray2);
        try {
            commandInTest.execute(patients);
            fail("Should be throwing NotFoundException!");
        } catch (NotFoundException n) {
            assert true;
        }
    }
}