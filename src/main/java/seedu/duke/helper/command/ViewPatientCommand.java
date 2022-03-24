package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.status.Status;

public class ViewPatientCommand extends Command{

    public ViewPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List patientList) {
        if (parameterArray == null) {
            patientList.view();
        } else {
            patientList.view(parameterArray[0]);
        }
        return Status.VIEW_SUCCESS;
    }
}
