package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class DeleteMedicineCommand extends Command{

    public DeleteMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List medicineList) throws NotFoundException {
        medicineList.remove(parameterArray[0]);
        return Status.DELETE_MEDICINE_SUCCESS;
    }
}
