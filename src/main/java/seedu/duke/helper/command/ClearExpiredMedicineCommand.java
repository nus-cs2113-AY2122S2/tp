package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class ClearExpiredMedicineCommand extends Command {
    public ClearExpiredMedicineCommand() {
        super(null);
    }

    @Override
    public Status execute(List medicineList) throws DuplicateEntryException, NotFoundException {
        if (medicineList instanceof MedicineList) {
            ((MedicineList) medicineList).clearStock();
            return Status.CLEAR_EXPIRED_MEDICINE_SUCCESS;
        }
        assert false;
        throw new NotFoundException("Error in code, approach developer!");
    }
}
