package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.HalpmiException;
import seedu.duke.status.Status;

public class FindMedicineCommand extends Command {

    public FindMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List medicineList) throws HalpmiException {
        switch (parameterArray[0]) {
        case "id":
            medicineList.findByNric(parameterArray);
            break;
        case "name":
            medicineList.findByName(parameterArray);
            break;
        case "dosage":
            medicineList.findByAge(parameterArray);
            break;
        case "expiry":
            medicineList.findByGender(parameterArray);
            break;
        case "sideeffects":
            medicineList.findByAddress(parameterArray);
            break;
        case "quantity":
            medicineList.findByDob(parameterArray);
            break;
        default:
            throw new HalpmiException("Input must be an attribute of Medicine");
        }
        return Status.FIND_MEDICINE_SUCCESS;
    }
}
