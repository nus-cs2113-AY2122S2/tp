package seedu.duke.helper;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.NotFoundException;

public class DeleteMedicineCommand extends Command{

    DeleteMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List medicineList) throws NotFoundException {
        medicineList.remove(parameterArray[0]);
    }
}
