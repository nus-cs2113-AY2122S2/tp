package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

import java.util.Arrays;

public class DispenseMedicineCommand extends Command {

    public DispenseMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List list) throws DuplicateEntryException, NotFoundException, HalpmiException {
        String patientNric = parameterArray[0];
        String[] medicineArray = Arrays.copyOfRange(parameterArray, 1, parameterArray.length);
        if (list instanceof AppointmentList) {
            ((AppointmentList) list).dispenseMedicine(patientNric,medicineArray);
            return Status.DISPENSE_SUCCESS;
        }
        if (list instanceof MedicineList) {
            ((MedicineList) list).dispenseMedicine(medicineArray);
            return Status.DISPENSE_SUCCESS;
        }
        assert false;
        throw new NotFoundException("Error in code, approach developer!");
    }
}
