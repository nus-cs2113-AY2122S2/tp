package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class FindMedicineCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public FindMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List medicineList) {
        if (medicineList instanceof MedicineList) {
            switch (parameterArray[0].toLowerCase()) {
            case "id":
                ((MedicineList) medicineList).findById(parameterArray);
                break;
            case "name":
                ((MedicineList) medicineList).findByName(parameterArray);
                break;
            case "dosage":
                ((MedicineList) medicineList).findByDosage(parameterArray);
                break;
            case "expiry":
                ((MedicineList) medicineList).findByExpiry(parameterArray);
                break;
            case "sideeffects":
                ((MedicineList) medicineList).findBySideEffects(parameterArray);
                break;
            case "quantity":
                ((MedicineList) medicineList).findByQuantity(parameterArray);
                break;
            default:
                break;
            }
        }
        return Status.FIND_MEDICINE_SUCCESS;
    }
}
