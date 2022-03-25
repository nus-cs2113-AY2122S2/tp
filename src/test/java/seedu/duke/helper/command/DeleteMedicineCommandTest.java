package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.fail;


class DeleteMedicineCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S123","Paracetamol","500","2024-01-01","Headaches","10"};
        String[] parameterArray2 = {"S123"};
        MedicineList medicines = new MedicineList();
        AddMedicineCommand command = new AddMedicineCommand(parameterArray);
        DeleteMedicineCommand commandInTest = new DeleteMedicineCommand(parameterArray2);
        try {
            command.execute(medicines);
            commandInTest.execute(medicines);
            assert true;
        } catch (DuplicateEntryException e) {
            fail("AddMedicineCommand is not working correctly!");
        } catch (NotFoundException n) {
            fail("Shouldn't be throwing NotFoundException as medicine exists!");
        }
    }

    @Test
    void executeTestNotFound() {
        String[] parameterArray2 = {"S123"};
        MedicineList medicines = new MedicineList();
        DeleteMedicineCommand commandInTest = new DeleteMedicineCommand(parameterArray2);
        try {
            commandInTest.execute(medicines);
            fail("Should be throwing NotFoundException!");
        } catch (NotFoundException n) {
            assert true;
        }
    }
}