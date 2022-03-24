package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;

public class ViewDoctorCommand extends Command{

    ViewDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List doctorList) {
        if (parameterArray == null) {
            doctorList.view();
        } else {
            doctorList.view(parameterArray[0]);
        }
    }
}
