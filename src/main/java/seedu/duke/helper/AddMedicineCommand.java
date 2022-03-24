package seedu.duke.helper;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;

public class AddMedicineCommand extends Command{

    AddMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List medicineList) throws DuplicateEntryException {
        medicineList.add(parameterArray);
    }
}
