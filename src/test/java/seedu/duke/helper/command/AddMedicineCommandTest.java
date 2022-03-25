package seedu.duke.helper.command;

import org.junit.jupiter.api.Test;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.status.Status.ADD_DOCTOR_SUCCESS;
import static seedu.duke.status.Status.ADD_MEDICINE_SUCCESS;

class AddMedicineCommandTest {

    @Test
    void executeTest() {
        String[] parameterArray = {"S123","Paracetamol","500","2024-01-01","Headaches","10"};
        MedicineList medicines = new MedicineList();
        AddMedicineCommand command = new AddMedicineCommand(parameterArray);
        try {
            assertEquals(ADD_MEDICINE_SUCCESS,command.execute(medicines));
        } catch (DuplicateEntryException e) {
            fail("Should give success status, not throw exception");
        }
    }

    @Test
    void executeTestDuplicate() {
        String[] parameterArray = {"S123","Paracetamol","500","2024-01-01","Headaches","10"};
        MedicineList medicines = new MedicineList();
        AddMedicineCommand command = new AddMedicineCommand(parameterArray);
        try {
            command.execute(medicines);
            command.execute(medicines);
            fail("Should be throwing Duplicate Entry Exception");
        } catch (Exception e) {
            assert true;
        }
    }

}