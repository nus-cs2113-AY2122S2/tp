package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class UpdateMedicineInventoryCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public UpdateMedicineInventoryCommand() {
        super(null);
    }

    /*
     * Method that takes in  MedicineList that needs to be acted on.
     * Uses parameterArray as inputs to find expired and run out Medicines in the List.
     * @ param list a List object - should be an instance of MedicineList
     * @ return Status.UPDATE_MEDICINE_INVENTORY_SUCCESS if changes made to list was successful
     * @ throws HalpMiException if none of the existing medicines have run out or expired.
     */
    @Override
    public Status execute(List medicineList) throws DuplicateEntryException, NotFoundException,
            UserInputErrorException {
        if (medicineList instanceof MedicineList) {
            ((MedicineList) medicineList).updateStock();
            return Status.UPDATE_MEDICINE_INVENTORY_SUCCESS;
        }
        assert false;
        throw new NotFoundException("Error in code, approach developer!");
    }
}
