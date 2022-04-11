package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.DoctorList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.fail;


class DeleteDoctorCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S1234567A","John","23","M","Singapore","1999-01-01","Endocrinology"};
        String[] parameterArray2 = {"S1234567A"};
        DoctorList doctors = new DoctorList();
        AddDoctorCommand command = new AddDoctorCommand(parameterArray);
        DeleteDoctorCommand commandInTest = new DeleteDoctorCommand(parameterArray2);
        try {
            command.execute(doctors);
            commandInTest.execute(doctors);
            assert true;
        } catch (DuplicateEntryException e) {
            fail("AddDoctorCommand is not working correctly!");
        } catch (NotFoundException n) {
            fail("Shouldn't be throwing NotFoundException as doctor exists!");
        }
    }

    @Test
    void executeTestNotFound() {
        String[] parameterArray2 = {"S1234567A"};
        DoctorList doctors = new DoctorList();
        DeleteDoctorCommand commandInTest = new DeleteDoctorCommand(parameterArray2);
        try {
            commandInTest.execute(doctors);
            fail("Should be throwing NotFoundException!");
        } catch (NotFoundException n) {
            assert true;
        }
    }

}