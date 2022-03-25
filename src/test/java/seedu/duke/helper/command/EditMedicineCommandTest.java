package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.fail;


class EditMedicineCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S123","Paracetamol","500","2024-01-01","Headaches","10"};
        String[] parameterArray2 = {"S123","Aspirin","500","2024-01-01","Headaches","10"};
        MedicineList medicines = new MedicineList();
        AddMedicineCommand command = new AddMedicineCommand(parameterArray);
        EditMedicineCommand commandInTest = new EditMedicineCommand(parameterArray2);
        try {
            command.execute(medicines);
            commandInTest.execute(medicines);
            assert true;
        } catch (DuplicateEntryException d) {
            fail("AddMedicineCommand is not working properly!");
        } catch (NotFoundException n) {
            fail("There exists a Medicine with the stated Batch ID. Should not be throwing exception!");
        }
    }

    @Test
    void executeTestNotFound() {
        String[] parameterArray2 = {"S123","Aspirin","500","2024-01-01","Headaches","10"};
        MedicineList medicines = new MedicineList();
        EditMedicineCommand commandInTest = new EditMedicineCommand(parameterArray2);
        try {
            commandInTest.execute(medicines);
            fail("Should be throwing Not Found Exception");
        } catch (NotFoundException n) {
            assert true;
        }
    }

}