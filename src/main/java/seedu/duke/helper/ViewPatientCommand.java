package seedu.duke.helper;

import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;

public class ViewPatientCommand extends Command{

    ViewPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List patientList) {
        if (parameterArray == null) {
            patientList.view();
        } else {
            patientList.view(parameterArray[0]);
        }
    }
}
