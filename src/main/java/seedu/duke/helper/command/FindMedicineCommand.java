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
            medicineList.findById(parameterArray);
            break;
        case "name":
            medicineList.findByName(parameterArray);
            break;
        case "dosage":
            medicineList.findByDosage(parameterArray);
            break;
        case "expiry":
            medicineList.findByExpiry(parameterArray);
            break;
        case "sideeffects":
            medicineList.findBySideEffects(parameterArray);
            break;
        case "quantity":
            medicineList.findByQuantity(parameterArray);
            break;
        default:
            throw new HalpmiException("Input must be an attribute of Medicine");
        }
        return Status.FIND_MEDICINE_SUCCESS;
    }
}
