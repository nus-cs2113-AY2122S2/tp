package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class EditMedicineCommand extends Command{

    public EditMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List medicineList) throws NotFoundException {
        medicineList.edit(parameterArray);
        return Status.EDIT_MEDICINE_SUCCESS;
    }
}
