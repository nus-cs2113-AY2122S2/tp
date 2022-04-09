package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class UpdateMedicineInventoryCommand extends Command {

    public UpdateMedicineInventoryCommand() {
        super(null);
    }

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
