package seedu.duke.helper;

import seedu.duke.assets.List;

public class ViewMedicineCommand extends Command{

    ViewMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List medicineList) {
        if (parameterArray == null) {
            medicineList.view();
        } else {
            medicineList.view(parameterArray[0]);
        }
    }
}
