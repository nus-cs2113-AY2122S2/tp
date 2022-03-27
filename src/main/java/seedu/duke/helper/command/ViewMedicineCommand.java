package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.HalpmiException;
import seedu.duke.status.Status;

public class ViewMedicineCommand extends Command {

    public ViewMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List medicineList) throws HalpmiException {
        if (parameterArray == null) {
            medicineList.view();
        } else {
            medicineList.view(parameterArray[0]);
        }
        return Status.VIEW_SUCCESS;
    }
}
