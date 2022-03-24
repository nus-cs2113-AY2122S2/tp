package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.status.Status;

public class AddMedicineCommand extends Command {

    public AddMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List medicineList) throws DuplicateEntryException {
        medicineList.add(parameterArray);
        return Status.ADD_MEDICINE_SUCCESS;
    }
}
